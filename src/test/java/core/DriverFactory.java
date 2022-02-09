package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

//    private static WebDriver driver;
      private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
          @Override
          protected synchronized WebDriver initialValue(){
              return initDriver();
          }
};

    private DriverFactory(){}

    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    public static WebDriver initDriver(){
        WebDriver driver = null;

        if (Propriedades.TIPO_EXECUCAO_VAR == Propriedades.TipoExecucao.LOCAL){
            switch (Propriedades.BROWSERS_VAR){
                case FIREFOX:
//                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
//                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }}

        if (Propriedades.TIPO_EXECUCAO_VAR == Propriedades.TipoExecucao.GRID){

            switch (Propriedades.BROWSERS_VAR){
                case FIREFOX:
//                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options = new FirefoxOptions();
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/"),options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case CHROME:
//                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options2 = new ChromeOptions();
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/"),options2);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }

        return driver;
    }


    public static void killDriver(){
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (threadDriver != null){
            threadDriver.remove();
        }
    }
}
