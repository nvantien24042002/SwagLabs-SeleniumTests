package tests;

import core.BaseTest;
import core.ExcelUtils;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "data")
    @Description("Verify that user can login successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login")
    @Step("Login with username: {0} and password: {1}")
    public void testLogin(String username, String password) throws InterruptedException {
        test.info("Starting login test with username: " + username);

        login(username, password);  // Dùng lại login từ BaseTest
        test.info("Entered credentials and clicked login");

        String currentUrl = driver.getCurrentUrl();

        if (currentUrl.contains("inventory.html")) {
            test.pass("Login successful, redirected to inventory.html");
        } else {
            test.fail("Login failed!");
            takeScreenshot();  // Chụp màn hình khi thất bại
        }

        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed!");
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getExcelData(".//TestLogin.xlsx", "TC01");
    }
}
