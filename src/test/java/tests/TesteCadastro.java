package tests;

import core.BaseTest;
import core.DSL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CampoTreinamentoPage;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCadastro extends BaseTest {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @BeforeEach
    public void inicializar(){

        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }


    @Test
    public void deveRealizarCadastroComSucesso(){

        page.setNome("Wagner");
        page.setSobrenome("Costa");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Mestrado");
        page.setEsporte("Natacao");
        page.cadastrar();

        Assertions.assertEquals("Cadastrado!",page.obterResultadoCadastro());
        Assertions.assertEquals("Wagner",page.obterNomeCadastro());
        Assertions.assertEquals("Costa", page.obterSobrenomeCadastro());
        Assertions.assertEquals("Masculino", page.obterSexoCadastro());
        Assertions.assertEquals("Pizza", page.obterComidaCadastro());
        Assertions.assertEquals("mestrado", page.obterEscolaridadeCadastro());
        Assertions.assertEquals("Natacao", page.obterEsportesCadastro());
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
