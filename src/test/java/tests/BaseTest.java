package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTest {


    protected static final String BASE_URL = "http://www.automationpractice.pl/index.php";
    protected WebDriver driver; //komunikacja z przeglądarką

    //    odpali sie przed wszystkimi testami
    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    // po każdym teście
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
