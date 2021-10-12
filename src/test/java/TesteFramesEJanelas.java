import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteFramesEJanelas {

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
    public void deveInteragirComFrames() {

        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        assertEquals("Frame OK!", msg);

        dsl.sairFrame();
        dsl.escrever("elementosForm:nome", msg);
    }

    @Test
    public void deveInteragirComJanelas(){
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        driver.close();
        dsl.trocarJanela("");
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo(){
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComFrameEscondido(){
        WebElement frame2 = driver.findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0,arguments[0])",frame2.getLocation().y);

        dsl.entrarFrame("frame2");

        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        assertEquals("Frame OK!", msg);


    }
}
