import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteAlert {

    private WebDriver driver;
    private DSL dsl;

    @BeforeEach
    public void inicializar(){

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        driver.manage().window().maximize();
        dsl = new DSL(driver);
    }

    @AfterEach
    public void fecharBrowser(){
        driver.quit();
    }

    @Test
    public void deveInteragirComAlertSimples() {
        dsl.clicar("alert");

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        assertEquals("Alert Simples", textAlert);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(textAlert);
    }

    @Test
    public void deveInteragirComAlertConfirm() throws InterruptedException {
        driver.findElement(By.id("confirm")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept(); //Para clicar no cancelar, usar alert.dismiss
        Thread.sleep(1000);
        String alertText = alert.getText();
        System.out.println(alertText);
        assertEquals("Confirmado",alertText);
        alert.accept();
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(alertText);
    }

    @Test
    public void deveInteragirComAlertPrompt() {

        driver.findElement(By.id("prompt")).click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Jundurian");
        alert.accept();
        assertEquals("Era Jundurian?",alert.getText());
        alert.accept();
        assertEquals(":D",alert.getText());
    }

}
