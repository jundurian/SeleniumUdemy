package tests;

import core.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import page.MenuPage;
import page.MovimentacaoPage;
import utils.DataUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static core.Propriedades.NOME_CONTA_ALTERADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.DataUtils.obterDataFormatada;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovimentacaoTest extends BaseTest {

    private MenuPage menuPage = new MenuPage();
    private MovimentacaoPage movPage = new MovimentacaoPage();

    @Test
    @Order(1)
    public void testInserirMovimentacao(){

        menuPage.acessarTelaInserirMovimentaca();

        movPage.setDataMovimentacao(obterDataFormatada(new Date()));
        movPage.setDataPagamento(obterDataFormatada(new Date()));
        movPage.setDescricao("Movimentação do Teste");
        movPage.setInteressado("Qualquer");
        movPage.setValor("500");
        movPage.setConta(NOME_CONTA_ALTERADA);
        movPage.setStatusPago();
        movPage.salvar();

        assertEquals("Movimentação adicionada com sucesso!", movPage.opterMensagemSucesso());

    }

    @Test
    @Order(2)
    public void testCamposObrigatorios(){
        menuPage.acessarTelaInserirMovimentaca();
        movPage.salvar();

        List<String> erros = movPage.obterErros();

        assertTrue(erros.containsAll(Arrays.asList(
               "Data da Movimentação é obrigatório",
               "Data do pagamento é obrigatório",
                "Descrição é obrigatório",
                "Interessado é obrigatório",
                "Valor é obrigatório",
                "Valor deve ser um número"
        )));
        assertEquals(6,erros.size());
    }

    @Test
    @Order(3)
    public void testInserirMovimentacaoFutura(){

        menuPage.acessarTelaInserirMovimentaca();

        Date dataFutura = DataUtils.obterDataComDiferencaDias(5);

        movPage.setDataMovimentacao(obterDataFormatada(dataFutura));
        movPage.setDataPagamento(obterDataFormatada(dataFutura));
        movPage.setDescricao("Movimentação do Teste");
        movPage.setInteressado("Qualquer");
        movPage.setValor("500");
        movPage.setConta(NOME_CONTA_ALTERADA);
        movPage.setStatusPago();
        movPage.salvar();

        List<String> erros = movPage.obterErros();

        assertTrue(erros.contains("Data da Movimentação deve ser menor ou igual à data atual"));
        assertEquals(1,erros.size());


    }
}
