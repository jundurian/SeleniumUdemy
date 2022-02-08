package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import page.HomePage;
import page.MenuPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaldoTest extends BaseTest {

    HomePage homePage = new HomePage();
    MenuPage menuPage = new MenuPage();

    @Test
    public void testSaldoConta(){
        menuPage.acessarTelaHome();
        assertEquals("534.00",homePage.obterSaldoConta("Conta para saldo"));
    }

}
