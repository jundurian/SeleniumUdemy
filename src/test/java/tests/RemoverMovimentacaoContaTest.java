package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import page.ContasPage;
import page.MenuPage;

import static core.Propriedades.NOME_CONTA_ALTERADA;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoverMovimentacaoContaTest extends BaseTest {

    MenuPage menuPage = new MenuPage();
    ContasPage contasPage = new ContasPage();

    @Test
    public void testExcluirContaComMovimentacao(){
        menuPage.acessarTelaListarConta();

        contasPage.clicarExcluirConta(NOME_CONTA_ALTERADA);

        assertEquals("Conta em uso na movimentações",contasPage.opterMensagemErro());

    }
}
