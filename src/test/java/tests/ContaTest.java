package tests;

import core.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import page.ContasPage;
import page.MenuPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaTest extends BaseTest {

    MenuPage menuPage = new MenuPage();
    ContasPage contasPage = new ContasPage();

    @Test
    public void testInserirConta(){
        menuPage.acessarTelaInserirConta();
        contasPage.setNome("Conta do Teste");
        contasPage.salvar();

        assertEquals("Conta adicionada com sucesso!", contasPage.opterMensagemSucesso());

    }

    @Test
    public void testAlterarConta(){
        menuPage.acessarTelaListarConta();

        contasPage.clicarAlterarConta("Conta para alterar");
        contasPage.setNome("Conta alterada");
        contasPage.salvar();

        assertEquals("Conta alterada com sucesso!", contasPage.opterMensagemSucesso());

    }

    @Test
    public void testInserirContaMesmoNome(){

        menuPage.acessarTelaInserirConta();
        contasPage.setNome("Conta mesmo nome");
        contasPage.salvar();

        assertEquals("Já existe uma conta com esse nome!", contasPage.opterMensagemErro());
    }

}
