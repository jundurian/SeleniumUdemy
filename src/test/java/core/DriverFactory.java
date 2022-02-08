package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

//    private static WebDriver driver;
      private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
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
            switch (Propriedades.browsers){
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
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
