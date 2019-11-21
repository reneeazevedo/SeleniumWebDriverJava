package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.ScreenShot;
import suporte.Web;


import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")
public class InformacoesUsuarioTest {
    private WebDriver navegador;
    private WebDriver na;
    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = Web.createChrome();

        //Maximizando o navegador
        navegador.manage().window().maximize();

        //Identificando o link de texto "Sign in"
        WebElement link = navegador.findElement(By.linkText("Sign in"));

        //Clicando no link
        link.click();

        //Identificando o elemento id signinbox
        WebElement formularioSignBox = navegador.findElement(By.id("signinbox"));

        //Digitando o usuário
        formularioSignBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitando a senha
        formularioSignBox.findElement(By.name("password")).sendKeys("123456");

        //Identificando o link "SIGN IN" e logo após clicando
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Identificando o elemento className = "me"
        WebElement me = navegador.findElement(By.className("me"));
        me.click();

        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {



        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
        popupAddMoreData.findElement(By.linkText("SAVE")).click();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement popMensagem = navegador.findElement(By.id("toast-container"));
        String mensagem = popMensagem.getText();
        Assert.assertEquals(mensagemEsperada, mensagem);


    }

    @Test

    public  void RemoverUmContatoDeUmUsuario(){
        // Clicar no elemento pelo seu xpath //span[text()="+551133334444"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+551133334444\"]/following-sibling::a")).click();
        // Confirmar a janela javascript
        navegador.switchTo().alert().accept();
        // Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement popMensagem = navegador.findElement(By.id("toast-container"));
        String mensagem = popMensagem.getText();
        Assert.assertEquals("Rest in peace, dear phone!", mensagem);
        // Aguardar 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador,20);
        aguardar.until(ExpectedConditions.stalenessOf(popMensagem));
        // Clicar no link com o texto logout
        navegador.findElement(By.linkText("Logout")).click();
        ScreenShot.tirar(navegador,"C:\\Users\\RENEE\\Documents\\testReport\\"+ Generator.dataHoraParaArquivo()+ test.getMethodName()+".png");

    }

    @After
    public void tearDown() throws InterruptedException {
        //Fechando o navegador

        //  navegador.quit();


    }
}
