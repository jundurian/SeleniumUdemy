import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCadastro {

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
//
//    @AfterEach
//    public void fecharBrowser(){
//        driver.quit();
//    }

    @Test
    public void deveRealizarCadastroComSucesso(){

        page.setNome("Wagner");
        page.setSobrenome("Costa");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Mestrado");
        page.setEsporte("Natacao");
        page.cadastrar();

        assertEquals("Cadastrado!",page.obterResultadoCadastro());
        assertEquals("Wagner",page.obterNomeCadastro());
        assertEquals("Costa", page.obterSobrenomeCadastro());
        assertEquals("Masculino", page.obterSexoCadastro());
        assertEquals("Pizza", page.obterComidaCadastro());
        assertEquals("mestrado", page.obterEscolaridadeCadastro());
        assertEquals("Natacao", page.obterEsportesCadastro());
    }

    @Test
    public void deveValidarNomeObrigatorio(){
        page.cadastrar();
        assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio(){
        page.setNome("Nome qualquer");
        page.cadastrar();
        assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        page.setNome("Nome qualquer");
        page.setSobrenome("Sobrenome qualquer");
        page.cadastrar();
        assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana(){
        page.setNome("Nome qualquer");
        page.setSobrenome("Sobrenome qualquer");
        page.setSexoFeminino();
        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();
        assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso(){
        page.setNome("Nome qualquer");
        page.setSobrenome("Sobrenome qualquer");
        page.setSexoFeminino();
        page.setComidaCarne();
        page.setEsporte("Karate", "O que eh esporte?");
        page.cadastrar();
        assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }
}
