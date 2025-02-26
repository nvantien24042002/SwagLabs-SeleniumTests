package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
public class HomePage extends BasePage {
    private By menuButton = By.id("react-burger-menu-btn");
    private By cartButton = By.id("shopping_cart_container");
    private By productList = By.className("inventory_item");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return driver.getTitle();
    }
    public void clickMenu() {
        try {
            click(menuButton);
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }

    }
    public void clickCart() {
        click(cartButton);
    }
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productList);
        return products.size(); // Lấy số lượng sp
    }
}
