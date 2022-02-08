package page;

import core.BasePage;
import org.openqa.selenium.By;

public class ResumoPage extends BasePage {

    public void excluirMovimentacao(){
        clicarBotao(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']"));

    }

    public String opterMensagemSucesso(){

        return obterTexto(By.xpath("//div[@class='alert alert-success']"));
    }

    public void selecionarAno(String ano){
        selecionarCombo("ano",ano);
    }

    public void buscar(){
        clicarBotao(By.xpath("//input[@value='Buscar']"));
    }
}
