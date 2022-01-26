package tests;

import core.DSL;
import core.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteAjax {

    private DSL dsl;

    @BeforeEach
    public void inicializar(){

        getDriver().manage().window().maximize();
        dsl = new DSL();
    }


    @AfterEach
    public void fecharBrowser(){
        killDriver();
    }

    @Test
    public void testAjax(){
        dsl.escrever("j_idt85:name", "Teste");
        dsl.clicarBotao("j_idt85:j_idt88");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt85:display"), "Teste"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
        Assertions.assertEquals("Teste", dsl.obterTexto("j_idt85:display"));
    }
}
