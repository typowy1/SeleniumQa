package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class WomenPage extends BasePage{

    public WomenPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".right-block span.price.product-price")
    List<WebElement> productPrice;

    @FindBy(css = ".cat-name")
    WebElement categoryName;


    public List<Integer> getProductPrices(){
        return productPrice.stream()
                .map(el -> Integer.valueOf(el.getText().substring(1)))
                .collect(Collectors.toList());
    }

    public void isCategoryWomanVisible(){
        wait.until(ExpectedConditions.visibilityOf(categoryName));
    }
}
