import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.asm.SpringAsmInfo;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteCampoTreinamento {

    @Test
    public void testTextField(){
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste");
        String value = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");

        assertEquals("Teste",value);

        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea(){
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///" +System.getProperty("user.dir") + "/src/test/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Jundurian");
        String value = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");

        assertEquals("Jundurian",value);

        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButton(){
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:sexo:0")).isSelected();

        assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        driver.quit();
}

    @Test
    public void deveInteragirComCheckbox(){
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).isSelected();

        assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:3")).isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComCombo(){
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(escolaridade);
        combo.selectByIndex(2);
        combo.selectByValue("superior");
        combo.selectByVisibleText("Mestrado");

        assertEquals("Mestrado",combo.getFirstSelectedOption().getText());

        driver.quit();


        //driver.quit();
    }

    @Test
    public void deveVerificarValoresCombo() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(escolaridade);

        List<WebElement> options = combo.getOptions();
        assertEquals(8,options.size());

        boolean encontrou = false;

        for (WebElement option: options){
            if (option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        assertTrue(encontrou);

    }

    @Test
    public void deveVerificarValoresComboMultiplaEscolha() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        WebElement escolaridade = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(escolaridade);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        // opcao para desmarcar: combo.deselectByVisibleText();

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        assertEquals(4,allSelectedOptions.size());

    }

    @Test
    public void deveClicarEmBotao() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("buttonSimple")).click();

        assertEquals("Obrigado!",driver.findElement(By.id("buttonSimple")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveIntegatirComLinks() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("C:\\Users\\Itatiba\\IdeaProjects\\SeleniumUdemy\\src\\test\\resources\\componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.linkText("Voltar")).click();

        assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());

        driver.quit();
    }


}
