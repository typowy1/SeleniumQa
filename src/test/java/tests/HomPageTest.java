package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BestSellersPage;
import pages.HomePage;
import pages.SearchPage;
import utils.PageTitleUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HomPageTest extends BaseTest {

    private BestSellersPage bestSellersPage;
    private HomePage homePage;
    private SearchPage searchPage;

    // przed każdym testem
    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        bestSellersPage = new BestSellersPage(driver);
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
    }


    @Test
    public void shouldSeeBestsellersItemsOnHomePage() throws InterruptedException {
        bestSellersPage.clickOnBestsellersButton();
        List<String> productNames = bestSellersPage.getProductNames();

        List<String> productsWithEmptyNames = productNames.stream()
                .filter(el -> el.isEmpty())
                .collect(Collectors.toList());

        assertThat(productsWithEmptyNames).isEmpty();
    }

    @Test
    public void searchBoxTest(){
        bestSellersPage.clickOnBestsellersButton();
        String firstProductNameFromBestSellers = bestSellersPage.getProductNameByIndex(0);
        System.out.println("firstProductNameFromBestSellers: " + firstProductNameFromBestSellers);
        homePage.typeTextInSearchBoxSubmitSearchButton(firstProductNameFromBestSellers);
        homePage.clickOnSearchBoxSubmitSearchButton();
        searchPage.isSearchPageVisible();
        searchPage.isSearchedProductCorrect(firstProductNameFromBestSellers);
        List<String> productNames = searchPage.getProductNames();
        for (String name : productNames) {
            System.out.println("name: " + name);
            assertThat(name).isEqualTo(firstProductNameFromBestSellers);
        }

        List<Integer> productPrices = searchPage.getProductPrices();

        for (Integer productPrice : productPrices) {
            System.out.println(productPrice);
            assertThat(productPrice).isEqualTo(27);
        }


    }
}
