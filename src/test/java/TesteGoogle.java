import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteGoogle {

    @Test
    public void teste() {

        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://www.google.com");
        assertEquals("Google", driver.getTitle());
        driver.quit();

//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//
//        driver.get("http://www.google.com");
//        assertEquals("Google", driver.getTitle());
//        driver.quit();



    }
}
