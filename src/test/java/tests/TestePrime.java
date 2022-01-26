package tests;

import core.DSL;
import core.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestePrime {

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
    public void deveInteragirComRadioPrime(){
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=7fc53");

        dsl.clicarRadio(By.xpath("//input[@id='j_idt312:console:0']/../..//span"));
        assertTrue(dsl.isRadioMarcado("j_idt312:console:0"));
        dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
        assertTrue(dsl.isRadioMarcado("j_idt312:console:1"));

    }

    @Test
    public void deveInteragirComSelectPrime(){
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=a837f");

        dsl.clicarRadio(By.xpath("//*[@id='j_idt311:option_input']/../..//span"));
        dsl.clicarRadio(By.xpath("//*[@id='j_idt311:option_items']//li[.='Option1']"));
        Assertions.assertEquals("Option1",dsl.obterTexto("j_idt311:option_label"));
    }
}
