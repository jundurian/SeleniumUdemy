import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	private WebDriver driver;

	@BeforeEach
	public void inicializa(){
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
//		driver.manage().window().setSize(new Dimension(1200, 765));
	}
	
	@AfterEach
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void teste() {
//		System.setProperty("webdriver.gecko.driver", "/Users/wcaquino/Downloads/geckodriver");
//		System.setProperty("webdriver.chrome.driver", "/Users/wcaquino/Downloads/chromedriver");
//		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.google.com");
		Assertions.assertEquals("Google", driver.getTitle());
	}

}
