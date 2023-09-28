package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.fail;

public class CreateAnAccountPage extends BasePage {

    public CreateAnAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#id_gender1")
    WebElement radioMr;

    @FindBy(css = "#id_gender2")
    WebElement radioMrs;

    @FindBy(css = "#customer_firstname")
    WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    WebElement lastNameInput;

    @FindBy(id = "passwd")
    WebElement passwordInput;

    @FindBy(id = "days")
    WebElement daysSelect;

    @FindBy(id = "months")
    WebElement monthsSelect;

    public WebElement getDaysSelect() {
        return daysSelect;
    }

    public WebElement getMonthsSelect() {
        return monthsSelect;
    }

    public WebElement getYearsSelect() {
        return yearsSelect;
    }

    @FindBy(id = "years")
    WebElement yearsSelect;

    @FindBy(id = "newsletter")
    WebElement newsletterCheckbox;

    @FindBy(id = "submitAccount")
    WebElement submitAccountButton;

    public void checkRadioFromTitle(String mrOrMrs) {
        if (mrOrMrs.equals("Mr")) {
            wait.until(ExpectedConditions.visibilityOf(radioMr));
            radioMr.click();
        } else if (mrOrMrs.equals("Mrs")) {
            wait.until(ExpectedConditions.visibilityOf(radioMrs));
            radioMrs.click();
        } else {
            fail("Wrong title");
        }
    }

    public void typeInFirstNameInput(String name) {
        firstNameInput.sendKeys(name);
    }

    public void typeInLastNameInput(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void typeInPasswordInput(String password) {
        if(password.length() > 5){
            passwordInput.sendKeys(password);
        } else {
            throw new IllegalArgumentException("Hasło powinjo mie cwięcej niś 5 znaków");
        }
    }

    public void selectDays(String day) {
        Select select = new Select(daysSelect);
        select.selectByVisibleText(day);
    }

    public void selectMonths(String months) {
        Select select = new Select(monthsSelect);
        select.selectByVisibleText(months);
    }

    public void selectYearsSelect(String years) {
        Select select = new Select(yearsSelect);
        select.selectByVisibleText(years);
    }

    public void checkNewsletterCheckbox() {
        newsletterCheckbox.click();
    }

    public void clickOnSubmitAccountButton(){
        submitAccountButton.click();
    }

}
