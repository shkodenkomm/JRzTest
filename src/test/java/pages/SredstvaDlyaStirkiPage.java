package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class SredstvaDlyaStirkiPage extends BasePage{

    @FindBy(xpath = "//a[@href='https://rozetka.com.ua/ua/sredstva-dlya-stirki4632103/c4632103/']")
    private WebElement poroshkiDlyaStirkiLink;

    public SredstvaDlyaStirkiPage(WebDriver _drv) {
        super(_drv);
    }

    public PoroshkiDlyaStirkPage openPoroshkiDlyaStirk(){
        poroshkiDlyaStirkiLink.click();
        getWt(20).until(urlContains("/sredstva-dlya-stirki4632103"));
        closePopup();
        baseDriver.findElement(By.name("vid-sredstva-71899_301219")).click();
        getWt(20).until(urlContains("vid-sredstva-71899"));

        return new PoroshkiDlyaStirkPage(baseDriver);

    }
}
