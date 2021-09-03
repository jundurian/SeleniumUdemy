import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteCampoTreinamento {
	
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
	public void testeTextField(){
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDuplo(){
		dsl.escrever("elementosForm:nome", "Wagner");
		assertEquals("Wagner", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Aquino");
		assertEquals("Aquino", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveIntegarirComTextArea(){
		dsl.escrever("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima linha");
		assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveIntegarirComRadioButton(){
		dsl.clicarRadio("elementosForm:sexo:0");
		assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveIntegarirComCheckbox(){
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveIntegarirComCombo(){
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		assertEquals(2, opcoesMarcadas.size());
		assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveinteragirComBotoes(){
		dsl.clicarBotao("buttonSimple");
		assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void deveinteragirComLinks(){
		dsl.clicarLink("Voltar");
		
		assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
//		Assertions.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		assertEquals("Cuidado onde clica, muitas armadilhas...",
				dsl.obterTexto(By.className("facilAchar")));
	}
	
}


