import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteAlert {
	
	private WebDriver driver;
	private DSL dsl;
	
	@BeforeEach
	public void inicializa(){
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@AfterEach
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveInteragirComAlertSimples(){
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceita(); 
		assertEquals("Alert Simples", texto);
		
		dsl.escrever("elementosForm:nome", texto);
	}
	
	@Test
	public void deveInteragirComAlertConfirm(){
		dsl.clicarBotao("confirm");
		assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		dsl.clicarBotao("confirm");
		assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		assertEquals("Negado", dsl.alertaObterTextoENega());
	}
	
	@Test
	public void deveInteragirComAlertPrompt(){
		dsl.clicarBotao("prompt");
		assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		assertEquals(":D", dsl.alertaObterTextoEAceita());
	}
}
