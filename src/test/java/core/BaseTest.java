package core;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent = ExtentReport.getInstance();
    protected ExtentTest test;

    @BeforeMethod
    @Description("Setup browser and launch application")
    @Step("Setup Chrome browser")
    public void setup() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(getOptions());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            test = extent.createTest("Test Case - " + this.getClass().getSimpleName());
            test.log(Status.INFO, "Browser setup completed");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing WebDriver: " + e.getMessage(), e);
        }
    }

    @AfterMethod
    @Description("Close browser and clean up")
    public void teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            test.addScreenCaptureFromPath(takeScreenshotPath());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        }

        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    @AfterSuite
    public void closeReport() {
        extent.flush();
    }

    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
        options.addArguments("--disable-extensions", "--disable-popup-blocking");
        options.addArguments("--disable-web-security", "--allow-running-insecure-content");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-software-rasterizer");
        return options;
    }

    @Step("Login with username {0} and password {1}")
    protected void login(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login(username, password);
        takeScreenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public String takeScreenshotPath() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(screenshot, new File(path));
            return path;
        } catch (IOException e) {
            System.out.println("Screenshot error: " + e.getMessage());
            return "";
        }
    }
}

























//////Hỗ trợ test song song
////
////package core;
////
////import com.aventstack.extentreports.ExtentReports;
////import com.aventstack.extentreports.ExtentTest;
////import com.aventstack.extentreports.Status;
////import io.github.bonigarcia.wdm.WebDriverManager;
////import io.qameta.allure.*;
////import org.openqa.selenium.OutputType;
////import org.openqa.selenium.TakesScreenshot;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.chrome.ChromeDriver;
////import org.openqa.selenium.chrome.ChromeOptions;
////import org.testng.ITestResult;
////import org.testng.annotations.*;
////import pages.LoginPage;
////
////import java.time.Duration;
////
////public class BaseTest {
////    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
////    protected static ExtentReports extent = ExtentReport.getInstance();
////    protected ExtentTest test;
////
////    @BeforeMethod
////    @Description("Setup browser and launch application")
////    @Step("Setup Chrome browser")
////    public void setup() {
////        try {
////            WebDriverManager.chromedriver().setup();
////            driver.set(new ChromeDriver(getOptions()));
////            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////            getDriver().manage().window().maximize();
////
////            // Tạo test mới trong Extent Reports
////            test = extent.createTest("Test Case - " + this.getClass().getSimpleName());
////            test.log(Status.INFO, "Browser setup completed");
////        } catch (Exception e) {
////            System.out.println("Error: " + e.getMessage());
////        }
////    }
////
////    @AfterMethod
////    @Description("Close browser and clean up")
////    public void teardown(ITestResult result) {
////        if (result.getStatus() == ITestResult.FAILURE) {
////            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
////            test.addScreenCaptureFromPath(takeScreenshotPath());
////        } else if (result.getStatus() == ITestResult.SUCCESS) {
////            test.log(Status.PASS, "Test Passed");
////        }
////
////        if (getDriver() != null) {
////            getDriver().quit();
////            driver.remove();
////        }
////    }
////
////    @AfterSuite
////    public void closeReport() {
////        if (extent != null) {
////            extent.flush();
////        }
////    }
////
////    public static WebDriver getDriver() {
////        return driver.get();
////    }
////
////    public ChromeOptions getOptions() {
////        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--start-maximized");
////        options.addArguments("--disable-popup-blocking", "--disable-extensions");
////        options.addArguments("--remote-allow-origins=*"); // Fix lỗi Selenium Grid
////        return options;
////    }
////
////    @Step("Login with username {0} and password {1}")
////    protected void login(String username, String password) throws InterruptedException {
////        LoginPage loginPage = new LoginPage(getDriver());
////        loginPage.navigateTo("https://www.saucedemo.com/");
////        loginPage.login(username, password);
////        takeScreenshot();
////    }
////
////    @Attachment(value = "Screenshot", type = "image/png")
////    public byte[] takeScreenshot() {
////        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
////    }
////
////    public String takeScreenshotPath() {
////        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
////    }
////}




