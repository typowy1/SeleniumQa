package tests;

import enums.MessageSubject;
import model.Message;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ContactUsFormPage;
import pages.TopMenuPage;
import utils.PageTitleUtils;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // testy będa odpalane w jakiejs kolejności
public class ContactUsTest extends BaseTest {

    private TopMenuPage topMenuPage;
    private ContactUsFormPage contactUsFormPage;

    // przed każdym testem
    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        topMenuPage = new TopMenuPage(driver);
        contactUsFormPage = new ContactUsFormPage(driver);
    }


    @Test
    @Order(1)
    public void shouldNotAllowToSendEmptyContactUsForm() {

//        implicity wait - wait globalnym dla wszyskich elemenrów, nie polecam, działa tylko dla metod find element

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        Explicity wait - na jaki elemen chcemy poczekać, dużo więcej możliwości, czy element jest klikalny, widoczny,
//        nie ustawiamy go globalnie dla wszystkich elementów ale dla elementu któty np wolniej się ładuje

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        Fluent wait - rozszeżenie explicity waita

        topMenuPage.clickOnContactUsLink();
        contactUsFormPage.clickOnSendButton();

        assertThat(contactUsFormPage.isRedAlertBoxDisplayed()).isTrue(); // sprawdzu czy element jest wyświetlony
    }


    @Test
    @Order(2)
    public void shouldNotAllowToSendContactUsFormWithEmailOnly() {
        topMenuPage.clickOnContactUsLink();
        contactUsFormPage.enterEmail("test@test.pl");
        contactUsFormPage.clickOnSendButton();

        assertThat(contactUsFormPage.isRedAlertBoxDisplayed()).isTrue(); // sprawdzu czy element jest wyświetlony
    }

    @Test
    @Order(3)
    public void shouldSendContactUsFormWithValidData(){
        topMenuPage.clickOnContactUsLink();

        Message message = new Message();
        message.setSubject(MessageSubject.WEBMASTER);
        message.setEmail("test@test.pl");
        message.setOrderReference("123");
        message.setMessage("test");

        contactUsFormPage.sendContactUsFor(message);
        assertThat(contactUsFormPage.isGreenAlertBoxDisplayed()).isTrue();
    }
}
