package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriverWait wait;

    //    Page factory zagwarantuje nam ze elementy nigdy nie będą nulami, zawsze będą zainicjalizowana
    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(250)); //będzie czekał przez 10 sekund, i odpytywał co 250 milissecond

    }
}
