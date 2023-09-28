package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.TopMenuPage;
import pages.WomenPage;
import utils.PageTitleUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WomenPageTest extends BaseTest{

    private TopMenuPage topMenuPage;
    private WomenPage womenPage;

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        topMenuPage = new TopMenuPage(driver);
        womenPage = new WomenPage(driver);
    }


    @Test
    public void isThePriceHigherThanZero(){
        topMenuPage.clickOnWomenLink();
        womenPage.isCategoryWomanVisible();
        List<Integer> productPrices = womenPage.getProductPrices();

        for (Integer productPrice : productPrices) {
            System.out.println(productPrice);
            assertThat(productPrice).isGreaterThan(0);
        }
    }



}
