package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(
            @Param(name="login")String login,
            @Param(name="senha")String senha,
            @Param(name="tipo")String tipo,
            @Param(name="contato")String contato,
            @Param(name="mensagem")String mensagem){
        String textoToast = new LoginPage(navegador)
                .clickSingIn()
                .fazerLogin(login,senha)
                .ClicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoAddMoreDataAboutYou()
                .AdicionarContato(tipo, contato)
                .capturarTextoDoToast();

        Assert.assertEquals(mensagem,textoToast);

    }
    @After
    public  void tearDown(){
        //navegador.quit();
    }
}
