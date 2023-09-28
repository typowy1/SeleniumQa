package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.*;
import utils.PageTitleUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // testy będa odpalane w jakiejs kolejności
public class CreateAnAccountPageTest extends BaseTest {

    private NavMenuPage navMenuPage;
    private SignInPage signInPage;
    private CreateAnAccountPage createAnAccountPage;
    private HomePage homePage;

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        navMenuPage = new NavMenuPage(driver);
        signInPage = new SignInPage(driver);
        createAnAccountPage = new CreateAnAccountPage(driver);
        homePage= new HomePage(driver);
    }

    @Test
    public void createAnAccountTest() {
        navMenuPage.clickOnSignIn();
        signInPage.typeRandomEmailInEmailAddressInput();
        signInPage.clickOnCreateAnAccountButton();
        createAnAccountPage.checkRadioFromTitle("Mr");
        createAnAccountPage.typeInFirstNameInput("Tomek");
        createAnAccountPage.typeInLastNameInput("Kowalski");
        createAnAccountPage.typeInPasswordInput("KLKLoo");
        createAnAccountPage.selectDays("15  ");
        createAnAccountPage.selectMonths("June ");
        createAnAccountPage.selectYearsSelect("1999  ");
        createAnAccountPage.checkNewsletterCheckbox();
        createAnAccountPage.clickOnSubmitAccountButton();
        String textFromSignOutLink = homePage.getTextFromSignOutLink();
        System.out.println(textFromSignOutLink);
        assertThat(textFromSignOutLink).isEqualTo("Sign out");
    }
}
