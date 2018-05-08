package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class RozetkaMain extends  BasePage {

    public RozetkaMain(WebDriver _drv, String _url){
        super(_drv);

        if (isNullOrEmpty(_url)){
            m_url = "https://rozetka.com.ua";
        }
    }


    private String m_url ;

    // UA lang selector
    @FindBy(xpath = "//a[@data-lang='ua']")
    private WebElement langUaLink;

    @FindBy(xpath = "//a[contains(@href,'telefony-tv-i-ehlektronika')][@name='fat_menu_link']")
    private WebElement smartTvDevisesLink;

    @FindBy(xpath = "//a[contains(@href,'tovary-dlya-doma')][@name='fat_menu_link']")
    private WebElement goodsHomeLink;


    public RozetkaMain loadPage(){
        baseDriver.get(m_url);
        langUaLink.click();
        getWt(15).until(urlContains("/ua"));
        return this;
    }

    public SmartTvDevisesPage openSmartTvDevises(){
        smartTvDevisesLink.click();
        getWt(15).until(urlContains("telefony-tv-i-ehlektronika"));
        closePopup();
        return new SmartTvDevisesPage(baseDriver);
    }
/*


    def open_tovary_dlya_doma(self):
        self.goods_home_link.click()
        self.close_popup()
        return GoodsHome(self.drv)
    *
    *
    * */
}
