import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteCampoTreinamento {

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
    public void testTextField(){

        dsl.escreve("elementosForm:nome","Teste");
        String value = dsl.obterValorCampo("elementosForm:nome");
        assertEquals("Teste",value);
    }

    @Test
    public void deveInteragirComTextArea(){

        dsl.escreve("elementosForm:sugestoes","Jundurian");
        String value = dsl.obterValorCampo("elementosForm:sugestoes");
        assertEquals("Jundurian",value);
    }

    @Test
    public void deveInteragirComRadioButton(){

        dsl.clicar("elementosForm:sexo:0");
        assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckbox(){

        dsl.clicar("elementosForm:comidaFavorita:3");
        assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:3"));
    }

    @Test
    public void deveInteragirComCombo(){

        dsl.selecionarCombo("elementosForm:escolaridade","Mestrado");
        assertEquals("Mestrado",dsl.obterValorCombo("elementosForm:escolaridade"));

    }

    @Test
    public void deveVerificarValoresCombo() {

        WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(escolaridade);

        List<WebElement> options = combo.getOptions();
        assertEquals(8,options.size());

        boolean encontrou = false;

        for (WebElement option: options){
            if (option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        assertTrue(encontrou);

    }

    @Test
    public void deveVerificarValoresComboMultiplaEscolha() {

        dsl.selecionarCombo("elementosForm:esportes","Natacao");
        dsl.selecionarCombo("elementosForm:esportes","Corrida");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");

        WebElement escolaridade = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(escolaridade);

        // opcao para desmarcar: combo.deselectByVisibleText();

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        assertEquals(3,allSelectedOptions.size());

    }

    @Test
    public void deveClicarEmBotao() {

        dsl.clicar("buttonSimple");
        assertEquals("Obrigado!",driver.findElement(By.id("buttonSimple")).getAttribute("value"));
    }

    @Test
    public void deveIntegatirComLinks() {

        dsl.clicarLink("Voltar");
        assertEquals("Voltou!",dsl.obterTexto(By.id("resultado")));
    }


}
