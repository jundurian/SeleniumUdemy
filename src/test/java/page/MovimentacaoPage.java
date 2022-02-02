package page;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static core.DriverFactory.getDriver;

public class MovimentacaoPage extends BasePage {

    public void setDataMovimentacao(String data){
        escrever("data_transacao",data);
    }

    public void setDataPagamento(String pagamento){
        escrever("data_pagamento",pagamento);
    }

    public void setDescricao(String descricao){
        escrever("descricao",descricao);
    }

    public void setInteressado(String interessado){
        escrever("interessado",interessado);
    }

    public void setValor(String valor){
        escrever("valor",valor);
    }

    public void setConta(String conta){
        selecionarCombo("conta",conta);
    }

    public void setStatusPago(){
        clicarRadio("status_pago");
    }

    public void salvar() {
        clicarBotaoPorTexto("Salvar");
    }

    public String opterMensagemSucesso(){

        return obterTexto(By.xpath("//div[@class='alert alert-success']"));
    }

    public List<String> obterErros(){
        List<WebElement> erros = getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));

        List<String> retorno = new ArrayList<>();

        for (WebElement erro:erros){
            retorno.add(erro.getText());
        }
        return retorno;
    }


}
