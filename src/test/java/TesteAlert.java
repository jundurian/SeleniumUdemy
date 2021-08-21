import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteAlert {

    @Test
    public void deveInteragirComAlertSimples() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///" +System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("alert")).click();

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        assertEquals("Alert Simples", textAlert);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(textAlert);
        driver.quit();
    }

    @Test
    public void deveInteragirComAlertConfirm() throws InterruptedException {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///" +System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("confirm")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept(); //Para clicar no cancelar, usar alert.dismiss
        Thread.sleep(1000);
        String alertText = alert.getText();
        System.out.println(alertText);
        assertEquals("Confirmado",alertText);
        alert.accept();
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(alertText);

        driver.quit();
    }

    @Test
    public void deveInteragirComAlertPrompt() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///" +System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("prompt")).click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Jundurian");
        alert.accept();
        assertEquals("Era Jundurian?",alert.getText());
        alert.accept();
        assertEquals(":D",alert.getText());

        driver.quit();
    }

}
