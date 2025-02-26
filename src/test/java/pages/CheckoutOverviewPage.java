package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }
    public void clickFinish() throws InterruptedException {
        click(finishButton);
        Thread.sleep(2000);
    }

    public void clickCancel() throws InterruptedException {
        click(cancelButton);
        Thread.sleep(2000);
    }
}
