package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class BestSellersPage extends BasePage {
    public BestSellersPage(WebDriver driver) {
        super(driver); //odwołujemy się do konstruktora z klasy matki
        PageFactory.initElements(driver, this); //this - ta klasa, czyli ta dana strona
    }

    @FindBy(css = "#blockbestsellers .product-name")  //*[@id='blockbestsellers']//*[@class='product-name']
    List<WebElement> productNames;

    @FindBy(css = "#home-page-tabs .blockbestsellers")
    WebElement bestsellersButton;


    public WebElement getBestsellersButton() {
        return bestsellersButton;
    }

    public List<String> getProductNames() {
        return productNames.stream()
                .map(el -> el.getText().trim()) // wyciągamy textm usówamy wszystkie spacje
                .collect(Collectors.toList()); //wrzucamy do listy u ją zwracamy
    }


    public void clickOnBestsellersButton() {
        bestsellersButton.click();
    }

}
