package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMenuPage extends BasePage {

    public TopMenuPage(WebDriver driver) {
        super(driver); //odwołujemy się do konstruktora z klasy matki
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "#contact-link")
    WebElement contactUsLink;

    public void clickOnContactUsLink() {
        contactUsLink.click();
    }
}
