import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DesafioUm {

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
    public void desafioUm(){
        page.setNome("Gabriel");
        page.setSobrenome("Jundurian");
        page.setSexoMasculino();

        page.setEscolaridade("Mestrado");
        page.setEsporte("Corrida");
        page.cadastrar();

        assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
        assertTrue(page.obterNomeCadastro().endsWith("Gabriel"));
        assertTrue(page.obterSobrenomeCadastro().endsWith("Jundurian"));

    }
}
