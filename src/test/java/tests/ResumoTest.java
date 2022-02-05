package tests;

import core.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import page.MenuPage;
import page.ResumoPage;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResumoTest extends BaseTest {

    private MenuPage menuPage = new MenuPage();
    private ResumoPage resumoPage = new ResumoPage();

    @Test
    @Order(1)
    public void testExcluirMovimentacao(){

        menuPage.acessarTelaResumo();

        resumoPage.excluirMovimentacao();

        assertEquals("Movimentação removida com sucesso!",resumoPage.opterMensagemSucesso());
    }

    @Test
    @Order(2)
    public void testResumoMensal(){
        menuPage.acessarTelaResumo();

        assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
    }

}
