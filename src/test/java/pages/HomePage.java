package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search_query_top")
    WebElement searchBoxInput;

    @FindBy(name = "submit_search")
    WebElement searchBoxSubmitSearchButton;

    @FindBy(className = "logout")
    WebElement SignOutLink;


    public void clickOnSearchBoxSubmitSearchButton(){
        searchBoxSubmitSearchButton.click();
    }

    public void typeTextInSearchBoxSubmitSearchButton (String text){
        searchBoxInput.sendKeys(text);
    }

    public String getTextFromSignOutLink() {
        wait.until(ExpectedConditions.visibilityOf(SignOutLink));
       return SignOutLink.getText().trim();
    }
}
