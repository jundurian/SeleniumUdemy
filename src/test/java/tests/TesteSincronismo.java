package tests;

import core.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteSincronismo {

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
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo", "Deu certo");
    }

    @Test
    public void deveUtilizarEsperaImplicita(){
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        dsl.clicarBotao("buttonDelay");
        dsl.escrever("novoCampo", "Deu certo?");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @Test
    public void deveUtilizarEsperaExplicita() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escrever("novoCampo", "Deu certo?");
    }
}
