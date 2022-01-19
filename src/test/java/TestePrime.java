import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrime {

    private WebDriver driver;
    private DSL dsl;

    @BeforeEach
    public void inicializar(){

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=7fc53");
        driver.manage().window().maximize();
        dsl = new DSL(driver);
    }


    @AfterEach
    public void fecharBrowser(){
//        driver.quit();
    }

    @Test
    public void deveInteragirComRadioPrime(){
        dsl.clicarRadio(By.xpath("//input[@id='j_idt312:console:0']/../..//span"));
        Assertions.assertTrue(dsl.isRadioMarcado("j_idt312:console:0"));
        dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
        Assertions.assertTrue(dsl.isRadioMarcado("j_idt312:console:1"));


    }
}
