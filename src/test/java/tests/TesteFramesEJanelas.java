package tests;

import core.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteFramesEJanelas {

    private DSL dsl;

    @BeforeEach
    public void inicializar(){

        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        getDriver().manage().window().maximize();
        dsl = new DSL();
    }

    @AfterEach
    public void fecharBrowser(){

        killDriver();
    }

    @Test
    public void deveInteragirComFrames() {

        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        assertEquals("Frame OK!", msg);

        dsl.sairFrame();
        dsl.escrever("elementosForm:nome", msg);
    }

    @Test
    public void deveInteragirComJanelas(){
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        getDriver().close();
        dsl.trocarJanela("");
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo(){
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getWindowHandles());
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComFrameEscondido(){
        WebElement frame2 = getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0,arguments[0])",frame2.getLocation().y);

        dsl.entrarFrame("frame2");

        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        assertEquals("Frame OK!", msg);


    }
}