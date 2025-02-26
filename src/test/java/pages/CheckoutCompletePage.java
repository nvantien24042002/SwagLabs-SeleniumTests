package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private By completeHeader = By.className("complete-header");
    private By backHomeButton = By.id("back-to-products");
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }
    public boolean isOrderComplete(){
        return isElement(completeHeader);
    }
    public void clickBackHome() throws InterruptedException {
        click(backHomeButton);
        Thread.sleep(2000);
    }

}
