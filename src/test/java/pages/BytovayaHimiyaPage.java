package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class BytovayaHimiyaPage extends  BasePage{


    @FindBy(xpath = "//a[@href='https://rozetka.com.ua/ua/sredstva-dlya-stirki/c4625084/']" )
    private WebElement sredstvaDlyaStirkiLink ;

    public BytovayaHimiyaPage(WebDriver baseDriver) {
        super(baseDriver);
    }

    public  SredstvaDlyaStirkiPage openSredstvaDlyaStirki(){

        sredstvaDlyaStirkiLink.click();
        getWt(20).until(urlContains("sredstva-dlya-stirki"));
        closePopup();
        return new SredstvaDlyaStirkiPage(baseDriver);
    }

}
