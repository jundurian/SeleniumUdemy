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
    }

}
