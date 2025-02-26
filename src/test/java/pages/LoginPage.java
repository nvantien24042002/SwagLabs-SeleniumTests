package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameInput  = By.id("user-name");
    private By passwordInput  = By.id("password");
    private By loginButton  = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // sendkey
    public void enterUsername(String user){
        sendKeys(usernameInput,user);
    }
    public void enterPassword(String pass){
        sendKeys(passwordInput,pass);
    }
    //click
    public void clickLogin(){
        click(loginButton);
    }
    //login
    public void login(String user,String pass) throws InterruptedException {
        enterUsername(user);
        enterPassword(pass);
        Thread.sleep(6000);
        clickLogin();
        Thread.sleep(6000);
    }

}
