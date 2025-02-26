package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueInput = By.id("continue");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public void enterCheckoutDetails(String firstName, String lastName, String postalCode) {
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(postalCodeInput, postalCode);
    }

    public void clickContinue() throws InterruptedException {
        click(continueInput);
        Thread.sleep(3000);
    }
}
