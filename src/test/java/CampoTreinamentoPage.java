import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }

    public void setNome(String nome){
        dsl.escreve("elementosForm:nome",nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escreve("elementosForm:sobrenome",sobrenome);

    }

    public void setSexoMasculino(){
        dsl.clicar("elementosForm:sexo:0");
    }

    public void setEscolaridade(String valor){
        dsl.selecionarCombo("elementosForm:escolaridade",valor);

    }

    public void setEsporte(String... valores){
        for (String valor: valores)
            dsl.selecionarCombo("elementosForm:esportes",valor);

    }

    public void cadastrar(){
        dsl.clicar("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro(){
        return dsl.obterTexto(By.id("resultado"));
    }

    public String obterNomeCadastro(){
        return dsl.obterTexto(By.id("descNome"));
    }

    public String obterSobrenomeCadastro(){
        return dsl.obterTexto(By.id("descSobrenome"));
    }

    public void setComidaCarne(){
        dsl.clicar("elementosForm:comidaFavorita:0");
    }

    public void setComidaVegetariano(){
        dsl.clicar("elementosForm:comidaFavorita:3");
    }
}
