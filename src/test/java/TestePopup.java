import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePopup {

    WebDriver driver;

    @BeforeEach
    public void inicializar(){

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void fecharBrowser(){
        driver.quit();
    }

    @Test
    public void testePopupEasy() {
        String windowHandle=driver.getWindowHandle(); // para o Firefox
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");

        driver.findElement(By.tagName("textarea")).sendKeys("Teste");
        driver.close(); // fechar o popup

        driver.switchTo().window(windowHandle); // para o Firefox
        driver.findElement(By.id("elementosForm:nome")).sendKeys("teste");


    }

    @Test
    public void testePopupHard() {

        String windowHandle=driver.getWindowHandle();
        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println("window " + driver.getWindowHandle());
        System.out.println("window " + driver.getWindowHandles());
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        driver.findElement(By.tagName("textarea")).sendKeys("Teste");

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        driver.findElement(By.tagName("textarea")).sendKeys("Teste");

    }

}
