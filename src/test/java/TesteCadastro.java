import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@BeforeEach
	public void inicializa(){
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@AfterEach
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Wagner");
		page.setSobrenome("Costa");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		assertTrue(page.obterNomeCadastro().endsWith("Wagner"));
		assertEquals("Sobrenome: Costa", page.obterSobrenomeCadastro());
		assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		assertEquals("Comida: Pizza", page.obterComidaCadastro());
		assertEquals("Escolaridade: mestrado", page.obterEscolaridadeCadastro());
		assertEquals("Esportes: Natacao", page.obterEsportesCadastro());
	}
	
	@Test
	public void deveValidarNomeObrigatorio(){
		page.cadastrar();
		assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio(){
		page.setNome("Nome qualquer");
		page.cadastrar();
		assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.cadastrar();
		assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}
}
