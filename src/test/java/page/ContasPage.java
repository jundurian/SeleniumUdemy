package page;

import core.BasePage;
import org.openqa.selenium.By;

public class ContasPage extends BasePage {

    public void setNome(String nome){
        escrever("nome",nome);
    }

    public void salvar(){

        clicarBotaoPorTexto("Salvar");
    }

    public String opterMensagemSucesso(){
        return obterTexto(By.xpath("//div[@class='alert alert-success']"));
    }

    public String opterMensagemErro(){
        return obterTexto(By.xpath("//div[@class='alert alert-danger']"));
    }


    public void clicarAlterarConta(String conta_do_teste) {

        obterCelula("Conta",conta_do_teste,"Ações","tabelaContas")
                .findElement(By.xpath(".//span[@class='glyphicon glyphicon-edit']")).click();
    }
}
