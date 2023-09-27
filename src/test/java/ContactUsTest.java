import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

public class ContactUsTest {
    private WebDriver driver; //komunikacja z przeglądarką

    //    odpali sie przed wszystkimi testami
    @BeforeAll
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    // przed każdym testem
    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    // po każdym teście
    @AfterEach
    void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void shouldNotAllowToSendEmptyContactUsForm(){

//        implicity wait - wait globalnym dla wszyskich elemenrów, nie polecam, działa tylko dla metod find element

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        Explicity wait - na jaki elemen chcemy poczekać, dużo więcej możliwości, czy element jest klikalny, widoczny,
//        nie ustawiamy go globalnie dla wszystkich elementów ale dla elementu któty np wolniej się ładuje

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        Fluent wait - rozszeżenie explicity waita


        driver.get("http://www.automationpractice.pl/index.php");
        WebElement contactUsLink = driver.findElement(By.cssSelector("#contact-link"));// //*[@id='contact-link']
        contactUsLink.click();

        WebElement sendButton = driver.findElement(By.cssSelector("#submitMessage"));// //*[@id='submitMessage']
        sendButton.click();

        WebElement redAlertBox = driver.findElement(By.className("alert-danger")); // //*[@class='alert alert-danger']

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(250)); //będzie czekał przez 10 sekund, i odpytywał co 250 milissecond
        wait.until(ExpectedConditions.visibilityOf(redAlertBox)); //poczekamy aż element będzie widoczny
//        wait.until(d -> redAlertBox.isDisplayed()); //poczekamy aż element będzie widoczny

        assertThat(redAlertBox.isDisplayed()).isTrue(); // sprawdzu czy element jest wyświetlony
    }


    @Test
    public void shouldNotAllowToSendContactUsFormWithEmailOnly(){

        driver.get("http://www.automationpractice.pl/index.php");
        WebElement contactUsLink = driver.findElement(By.cssSelector("#contact-link"));// //*[@id='contact-link']
        contactUsLink.click();

        WebElement emailInput = driver.findElement(By.id("email"));//#email, //*[@id='email']
        emailInput.sendKeys("test@test.com");

        WebElement sendButton = driver.findElement(By.cssSelector("#submitMessage"));// //*[@id='submitMessage']
        sendButton.click();

        WebElement redAlertBox = driver.findElement(By.className("alert-danger")); // //*[@class='alert alert-danger']

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(250));
        wait.until(ExpectedConditions.visibilityOf(redAlertBox));

        assertThat(redAlertBox.isDisplayed()).isTrue(); // sprawdzu czy element jest wyświetlony

    }


}
