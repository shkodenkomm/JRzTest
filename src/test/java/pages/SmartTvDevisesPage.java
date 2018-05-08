package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class SmartTvDevisesPage extends BasePage {

    @FindBy(xpath = "//ul[@id='menu_categories_left']//a[@href='https://rozetka.com.ua/ua/telefony/c4627900/']")
    private WebElement telefonyLink;

    public SmartTvDevisesPage(WebDriver _drv) {
        super(_drv);
    }

    public PhonePage open_phones(){
        telefonyLink.click();
        getWt(15).until(urlContains("/telefony/"));
        closePopup();
        return new PhonePage(baseDriver);
    }
}

