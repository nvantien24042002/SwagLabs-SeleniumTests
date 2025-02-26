package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;


public class HomeTest extends BaseTest {
    @Test
    public void testHomePageTitle() throws InterruptedException {
        login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        String title = homePage.getTitle();

        Assert.assertEquals(title, "Swag Labs", "Tiêu đề trang chủ sai!");
    }
    @Test
    public void testProductCount() throws InterruptedException {
        login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        int countProduct = homePage.getProductCount();

        System.out.println("Tổng số lượng sản phẩm: " + countProduct);
        Assert.assertTrue(countProduct > 0, "Không có sản phẩm nào!");
    }
}
