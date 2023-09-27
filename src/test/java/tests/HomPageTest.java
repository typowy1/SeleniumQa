package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BestSellersPage;
import utils.PageTitleUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HomPageTest extends BaseTest {

    private BestSellersPage bestSellersPage;

    // przed każdym testem
    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        bestSellersPage = new BestSellersPage(driver);
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
}
