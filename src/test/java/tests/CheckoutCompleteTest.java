package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutCompleteTest extends BaseTest {

    @Test
    public void testOrderCompletion() throws InterruptedException {
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
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOrderComplete(), "Không hoàn tất đơn hàng!");
        checkoutCompletePage.clickBackHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Không trở về trang chủ!");
    }
}
