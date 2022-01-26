package tests;

import core.DSL;
import core.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteAlert {

    private DSL dsl;

    @BeforeEach
    public void inicializar(){

        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        getDriver().manage().window().maximize();
        dsl = new DSL();
    }

    @AfterEach
    public void fecharBrowser(){

        DriverFactory.getDriver();
    }

    @Test
    public void deveInteragirComAlertSimples() {
        dsl.clicarBotao("alert");
        String texto = dsl.alertaObterTextoEAceita();
        assertEquals("Alert Simples", texto);

        dsl.escrever("elementosForm:nome", texto);
    }

    @Test
    public void deveInteragirComAlertConfirm() {
        dsl.clicarBotao("confirm");
        assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
        assertEquals("Confirmado", dsl.alertaObterTextoEAceita());

        dsl.clicarBotao("confirm");
        assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
        assertEquals("Negado", dsl.alertaObterTextoENega());
    }

    @Test
    public void deveInteragirComAlertPrompt() {

        dsl.clicarBotao("prompt");
        assertEquals("Digite um numero", dsl.alertaObterTexto());
        dsl.alertaEscrever("12");
        assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
        assertEquals(":D", dsl.alertaObterTextoEAceita());
    }

}
