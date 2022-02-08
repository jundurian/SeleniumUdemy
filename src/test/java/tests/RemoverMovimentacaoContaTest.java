package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import page.ContasPage;
import page.MenuPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoverMovimentacaoContaTest extends BaseTest {

    MenuPage menuPage = new MenuPage();
    ContasPage contasPage = new ContasPage();

    @Test
    public void testExcluirContaComMovimentacao(){
        menuPage.acessarTelaListarConta();

        contasPage.clicarExcluirConta("Conta com movimentacao");

        assertEquals("Conta em uso na movimentações",contasPage.opterMensagemErro());

    }
}
