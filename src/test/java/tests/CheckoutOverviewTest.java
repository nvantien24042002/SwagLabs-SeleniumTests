package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutOverviewTest extends BaseTest {

    @Test
    public void testCheckoutOverview() throws InterruptedException {
        login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(0);
        productPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutDetails("Nguyen", "Tien", "700000");
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"), "Không hoàn tất đơn hàng!");
    }
}
