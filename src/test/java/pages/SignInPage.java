package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email_create")
    WebElement emailAddressInput;

    @FindBy(id = "SubmitCreate")
    WebElement createAnAccountButton;

    public void typeRandomEmailInEmailAddressInput() {
        Random random = new Random();
        String randomEmail = "test@test" + random.nextInt(100000) + ".pl";
        System.out.println(randomEmail);
        emailAddressInput.sendKeys(randomEmail);
    }

    public void clickOnCreateAnAccountButton() {
        createAnAccountButton.click();
    }

}
