package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RENEE\\Drivers\\chromedriver.exe");

        //Abrindo o navegado
        WebDriver navegador = new ChromeDriver();

        //Definindo um tempo de espera para abertura dos formulários
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Abrindo o site pelo navegador
        navegador.get("http://www.juliodelima.com.br/taskit");
        return navegador;
    }
}
