package core;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import page.LoginPage;

import java.io.File;
import java.io.IOException;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static java.io.File.separator;

public class BaseTest {
    private LoginPage page = new LoginPage();

    @AfterEach
    public void fecharBrowserAposCadaTeste(TestInfo testInfo) throws IOException {

        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo,new File("target" + separator + "screenshots" + separator +
                testInfo.getDisplayName() + ".jpg"));

        if (Propriedades.FECHAR_BROWSER) {
            killDriver();
        }
    }

//    @AfterAll
//    public static void fecharBrowserDepoisTodosTestes(){
//        killDriver();
//    }

    @BeforeEach
    public void maximizeAndLogin(){
        getDriver().manage().window().maximize();

        page.acessarTelaInicial();
        page.setEmail("gabriel@junduriann");
        page.setSenha("123456");
        page.entrar();


    }
}
