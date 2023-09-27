package pages;

import model.Message;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContactUsFormPage extends BasePage {


    public ContactUsFormPage(WebDriver driver) {
        super(driver); //odwołujemy się do konstruktora z klasy matki
    }

    @FindBy(css = "#submitMessage") //*[@id='submitMessage']
    WebElement sendButton;

    @FindBy(className = "alert-danger") //*[@class='alert alert-danger']
    WebElement redAlertBox;

    @FindBy(className = "alert-success") //*[@class='alert alert-success']
    WebElement greenAlertBox;

    @FindBy(id = "email") //#email, //*[@id='email']
    WebElement emailInput;

    @FindBy(id = "id_contact") //*[@id='id_contact']
    WebElement subjectSelect;

    @FindBy(id = "id_order") //*[@id='id_orde']
    WebElement orderReferenceInput;

    @FindBy(id = "message")
    WebElement messageTextArea;


    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getRedAlertBox() {
        return redAlertBox;
    }

    public void clickOnSendButton() {
        sendButton.click();
    }

    public boolean isRedAlertBoxDisplayed() {
        return isAlertBoxDisplayed(redAlertBox);
    }

    public boolean isGreenAlertBoxDisplayed() {
        return isAlertBoxDisplayed(greenAlertBox);
    }

    private boolean isAlertBoxDisplayed(WebElement alert) { //średnia ta metoda bo może zwrócić wyjątek NoSuchElementException, zdradliwe jest i dlatego taki kod
        wait.until(ExpectedConditions.visibilityOf(alert)); //poczekamy aż element będzie widoczny
        //        wait.until(d -> redAlertBox.isDisplayed()); //poczekamy aż element będzie widoczny

        boolean isDisplayed = false;
        try {
            isDisplayed = alert.isDisplayed(); //tu zwróci true
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void sendContactUsFor(Message message) {
        Select subject = new Select(subjectSelect); // tak obsługujemy selecty, pola z rozwijana lista
        subject.selectByVisibleText(message.getSubject().getValue()); // wybieramu opcje wyboru z enuma
        emailInput.sendKeys(message.getEmail());
        orderReferenceInput.sendKeys(message.getOrderReference());
        messageTextArea.sendKeys(message.getMessage());
        sendButton.click();
    }
}
