package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class PhonePage extends BasePage {

    @FindBy(xpath = "//a[@href='https://rozetka.com.ua/ua/mobile-phones/c80003/filter/preset=smartfon/']")
    private WebElement smartLink;

    public PhonePage(WebDriver baseDriver) {
        super(baseDriver);
    }

    public SmartPhonePage open_smartphones(){
        smartLink.click();
        getWt(20).until(urlContains("mobile-phones/c80003/preset=smartfon"));
        closePopup();
        return new SmartPhonePage(baseDriver);
    }
}
