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
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DesafioDois {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @BeforeEach
    public void inicializar(){

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        driver.manage().window().maximize();
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @AfterEach
    public void fecharBrowser(){
        driver.quit();
    }

    @Test
    public void semNome(){
        page.cadastrar();
        assertEquals("Nome eh obrigatorio",dsl.alertaObterTextoEAceita());

    }

    @Test
    public void semSobrenome(){

        page.setNome("Jundurian");
        page.cadastrar();

        assertEquals("Sobrenome eh obrigatorio",dsl.alertaObterTextoEAceita());
    }

    @Test
    public void semSexo(){
        page.setNome("Jundurian");
        page.setSobrenome("Jundurian");
        page.cadastrar();

        assertEquals("Sexo eh obrigatorio",dsl.alertaObterTextoEAceita());
    }

    @Test
    public void carneEVegetariano(){
        page.setNome("Jundurian");
        page.setSobrenome("Jundurian");
        page.setSexoMasculino();

        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();

        assertEquals("Tem certeza que voce eh vegetariano?",dsl.alertaObterTextoEAceita());
    }
}
