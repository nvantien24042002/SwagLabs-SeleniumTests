package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private By cartItemNames = By.className("inventory_item_name");
    private By cartItemPrices = By.className("inventory_item_price");
    private By checkoutButton = By.id("checkout");
    private By cartItems = By.className("cart_item");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public List<String> getCartProductNames() {
        List<WebElement> elements = driver.findElements(cartItemNames);
        List<String> productNames = new ArrayList<>();
        for (WebElement element : elements) {
            productNames.add(element.getText());
        }
        return productNames;
    }

    public List<String> getCartProductPrices() {
        List<WebElement> elements = driver.findElements(cartItemPrices);
        List<String> productPrices = new ArrayList<>();
        for (WebElement element : elements) {
            productPrices.add(element.getText());
        }
        return productPrices;
    }
    public int getCartProductCount() {
        return driver.findElements(cartItems).size();
    }
    public void clickCheckout() throws InterruptedException {
        click(checkoutButton);
        Thread.sleep(3000);
        System.out.println("Đã check out thành công");
    }
}
