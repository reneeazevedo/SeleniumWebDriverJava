package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{


    public LoginPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage clickSingIn(){
//Identificando o link de texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();
        return  new LoginFormPage(navegador);
    }
}
