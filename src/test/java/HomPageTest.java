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

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HomPageTest {

    private WebDriver driver; //komunikacja z przeglądarką
    private WebElement element; //obsługa elementów na stronie

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
    public void shouldReturnCorrectPageTitle(){
        driver.get("http://www.automationpractice.pl/index.php");

        Assertions.assertThat(driver.getTitle()).isEqualTo("My Shop");
    }

    public void clickOnBestsellersButton() throws InterruptedException {
        driver.findElement(By.cssSelector("#home-page-tabs .blockbestsellers")).click();
    }

    @Test
    public void shouldSeeBestsellersItemsOnHomePage() throws InterruptedException {
        driver.get("http://www.automationpractice.pl/index.php");
        clickOnBestsellersButton();
        List<WebElement> productNamesByCssSelector = driver.findElements(By.cssSelector("#blockbestsellers .product-name"));
//        xpath
//        List<WebElement> productNamesByCssXpath = driver.findElements(By.xpath("//*[@id='blockbestsellers']//*[@class='product-name']"));

        List<WebElement> productWithEmptyName = productNamesByCssSelector.stream()
                .filter(el -> el.getText().isEmpty())
                .collect(Collectors.toList());

        assertThat(productWithEmptyName).isEmpty();
    }
}
