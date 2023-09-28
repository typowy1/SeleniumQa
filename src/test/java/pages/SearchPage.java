package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".lighter")
    WebElement searchResult;

    @FindBy(css = ".product_list .product-name")
    List<WebElement> searchedProductNames;

    @FindBy(css = ".right-block span.price.product-price")
    List<WebElement> productPrice;

    public void isSearchedProductCorrect(String searchResult) {
        assertThat(searchResult.toUpperCase()).isEqualTo(getTextFormSearchResult().substring(1, 7));
    }

    public String getTextFormSearchResult() {
        return searchResult.getText().trim();
    }

    public void isSearchPageVisible() {
        wait.until(ExpectedConditions.visibilityOf(searchResult));
    }

    public List<String> getProductNames (){
        return searchedProductNames.stream()
                .map(el -> el.getText().trim())
                .collect(Collectors.toList());
    }

    public List<Integer> getProductPrices(){
        return productPrice.stream()
                .map(el -> Integer.valueOf(el.getText().substring(1)))
                .collect(Collectors.toList());
    }
}
