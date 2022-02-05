package core;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import page.LoginPage;

import java.io.File;
import java.io.IOException;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static core.Propriedades.FECHAR_BROWSER_DPS_CADA_TESTE;
import static java.io.File.separator;

public class BaseTest {

    private static LoginPage page = new LoginPage();

    @AfterEach
    public void fecharBrowserAposCadaTeste(TestInfo testInfo) throws IOException {

        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo,new File("target" + separator + "screenshots" + separator +
                testInfo.getDisplayName() + ".jpg"));

        if (FECHAR_BROWSER_DPS_CADA_TESTE) {
            killDriver();
        }
    }

    @AfterAll
    public static void fecharBrowserDepoisTodosTestes(){
        killDriver();
    }

    @BeforeAll
    public static void maximizeAndLogin(){
        getDriver().manage().window().maximize();

        page.acessarTelaInicial();
        page.setEmail("gabriel@junduriann");
        page.setSenha("123456");
        page.entrar();


    }
}
