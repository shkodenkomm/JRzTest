package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class TovaryDliaDomaPage extends  BasePage {

    @FindBy(xpath = "//a[@href='https://rozetka.com.ua/ua/bytovaya-himiya/c4429255/']" )
    private WebElement bytovayaHimiyaLink;

    public TovaryDliaDomaPage(WebDriver baseDriver) {
        super(baseDriver);
    }

    public  BytovayaHimiyaPage openBytovayaHimiyaPage(){

        bytovayaHimiyaLink.click();
        getWt(20).until(urlContains("bytovaya-himiya"));
        closePopup();
        return new BytovayaHimiyaPage(baseDriver);
    }
}
