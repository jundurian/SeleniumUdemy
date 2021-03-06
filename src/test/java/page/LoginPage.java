package page;

import core.BasePage;

import static core.DriverFactory.getDriver;

public class LoginPage extends BasePage {

    public void acessarTelaInicial(){
        getDriver().get("https://seubarriga.wcaquino.me/");
    }

    public void setEmail(String email){
        escrever("email",email);
    }

    public void setSenha(String senha){
        escrever("senha",senha);
    }

    public void entrar(){
        clicarBotaoPorTexto("Entrar");
    }

    public void resetar(){
        clicarLink("reset");
    }


}
