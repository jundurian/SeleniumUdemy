import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CLASSE PARA TESTES PARAMETRICOS
// PELA VERSÃO NOVA DO JUNIT ISSO ESTA DIFERENTE DO VIDEO
// DESCONSIDERAR


public class TesteRegrasCadastro {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    private String nome;
    private String sobrenome;
    private String sexo;
    private List<String> comidas;
    private String[] esportes;
    private String msg;

    @BeforeAll
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @AfterAll
    public void finaliza(){
        driver.quit();
    }


    @ParameterizedTest
    public static Collection<Object[]> getCollection(){
        return Arrays.asList(new Object[][]{
            {},
            {}
        });
    }

    public void deveValidarRegras(){
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if (sexo.equals("Feminino")) {
            page.setSexoFeminino();
        } else {
            page.setSexoMasculino();
        }

        if (comidas.contains("Carne")) page.setComidaCarne();
        if (comidas.contains("Pizza")) page.setComidaPizza();
        if (comidas.contains("Vegetariana")) page.setComidaVegetariano();

        page.setEsporte(esportes);
        page.cadastrar();
        assertEquals(msg, dsl.alertaObterTextoEAceita());
    }
}
