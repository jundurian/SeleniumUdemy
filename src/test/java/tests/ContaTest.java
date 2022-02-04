package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import page.ContasPage;
import page.MenuPage;

import static core.Propriedades.NOME_CONTA_ALTERADA;
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

        contasPage.clicarAlterarConta("Conta do Teste");
        contasPage.setNome(NOME_CONTA_ALTERADA);
        contasPage.salvar();

        assertEquals("Conta alterada com sucesso!", contasPage.opterMensagemSucesso());

    }

    @Test
    public void testInserirContaMesmoNome(){

        menuPage.acessarTelaInserirConta();
        contasPage.setNome(NOME_CONTA_ALTERADA);
        contasPage.salvar();

        assertEquals("Já existe uma conta com esse nome!", contasPage.opterMensagemErro());
    }

}
