package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import page.HomePage;
import page.MenuPage;

import static core.Propriedades.NOME_CONTA_ALTERADA;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaldoTest extends BaseTest {

    HomePage homePage = new HomePage();
    MenuPage menuPage = new MenuPage();

    @Test
    public void testSaldoConta(){
        menuPage.acessarTelaHome();
        assertEquals("500.00",homePage.obterSaldoConta(NOME_CONTA_ALTERADA));
    }

}
