package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;

public class CartTest extends BaseTest {
    @Test
    public void testAddToCart() throws InterruptedException {
        login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(0);
        productPage.addProductToCart(1);
        productPage.addProductToCart(2);
        productPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        int cartCount = cartPage.getCartProductCount();
        Assert.assertEquals(cartCount, 3, "Số lượng sản phẩm trong giỏ hàng không đúng!");
    }
}
