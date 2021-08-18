import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {

    @Test
    public void test(){
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Jundurian\\IdeaProjects\\SeleniumUdemy\\src\\main\\resources\\componentes.html");
        driver.manage().window().maximize();

        driver.quit();
    }
}
