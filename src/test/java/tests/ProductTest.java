package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;

import java.util.List;

public class ProductTest extends BaseTest {

    @Test
    public void testProductList() throws InterruptedException {
        login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllNameProduct();
        List<String> productPrices = productPage.getAllProductPrices();

        for (int i = 0; i < productNames.size(); i++) {
            System.out.println(productNames.get(i) + " - " + productPrices.get(i));
        }

        Assert.assertTrue(productNames.contains("Sauce Labs Backpack"));
        Assert.assertTrue(productPrices.contains("$29.99"));
    }
}
