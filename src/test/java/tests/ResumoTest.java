package tests;

import core.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.MenuPage;
import page.ResumoPage;

import java.util.List;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@Execution(ExecutionMode.CONCURRENT)
public class ResumoTest extends BaseTest {

    private MenuPage menuPage = new MenuPage();
    private ResumoPage resumoPage = new ResumoPage();

    @Test
    public void testExcluirMovimentacao(){

        menuPage.acessarTelaResumo();

        resumoPage.excluirMovimentacao();

        assertEquals("Movimentação removida com sucesso!",resumoPage.opterMensagemSucesso());
    }

    @Test
    public void testResumoMensal(){
        menuPage.acessarTelaResumo();

        assertEquals("Seu Barriga - Extrato", getDriver().getTitle());

        resumoPage.selecionarAno("2016");
        resumoPage.buscar();

        List<WebElement> elements = getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));

        assertEquals(0, elements.size());
    }

}
