package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {
    private By productTitles = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtons = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']");
    private By cartButton = By.id("shopping_cart_container"); // Nút giỏ hàng

    public ProductPage(WebDriver driver) {
        super(driver);
    }
    public List<String> getAllNameProduct() {
        List<WebElement> elements = driver.findElements(productTitles);
        List<String> productNames = new ArrayList<>();
        for (WebElement element : elements) {
            productNames.add(element.getText());
        }
        return productNames;
    }
    public List<String> getAllProductPrices() {
        List<WebElement> elements = driver.findElements(productPrices);
        List<String> productPricesList = new ArrayList<>();
        for (WebElement element : elements) {
            productPricesList.add(element.getText());
        }
        return productPricesList;
    }
    public void addProductToCart(int index) throws InterruptedException {
        List<WebElement> products = driver.findElements(productTitles);
        if (index < products.size()) {
            WebElement product = products.get(index);
            String productName = product.getText();
            // Xác định phần tử cha chứa sản phẩm này
            WebElement parentElement = product.findElement(By.xpath("./ancestor::div[@class='inventory_item']"));
            // Tìm nút "Add to Cart" trong phần tử cha
            WebElement addToCartButton = parentElement.findElement(By.xpath(".//button[contains(text(),'Add to cart')]"));
            addToCartButton.click();
            Thread.sleep(2000);
        } else {
            System.out.println("Không tìm thấy sản phẩm với index: " + index);
        }
    }

    public void goToCart() throws InterruptedException {
        click(cartButton);
        Thread.sleep(5000);
    }



}
