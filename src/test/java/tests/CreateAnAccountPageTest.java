package tests;

import enums.MessageSubject;
import enums.Title;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.PageTitleUtils;

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

        User user = new User();
        user.setTitle(Title.MR);
        user.setFirstName("Tomek");
        user.setLastName("Kowalski");
        user.setPassword("KLKLoo");
        user.setDays("15  ");
        user.setMonths("June ");
        user.setYears("1999  ");
        user.setNewsletterCheckbox("ON");

        createAnAccountPage.createAccount(user);
        String textFromSignOutLink = homePage.getTextFromSignOutLink();
        System.out.println(textFromSignOutLink);
        assertThat(textFromSignOutLink).isEqualTo("Sign out");
    }
}
