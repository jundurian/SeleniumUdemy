package tests;

import core.BaseTest;
import core.DSL;
import core.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.CampoTreinamentoPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

// CLASSE PARA TESTES PARAMETRICOS
// PELA VERSÃO NOVA DO JUNIT ISSO ESTA DIFERENTE DO VIDEO
// DESCONSIDERAR


public class TesteRegrasCadastro extends BaseTest {

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
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
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
