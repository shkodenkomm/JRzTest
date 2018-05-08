package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class GoodsBasePage extends  BasePage {
    public GoodsBasePage(WebDriver _drv) {
        super(_drv);
    }

    @FindBy(xpath = "//ul[@name='paginator']")
    protected WebElement paginator;


    protected String xpath_goods_names ="//div[@id='catalog_goods_block']/div//div[@class='g-i-tile-i-title clearfix']/a";
    protected String xpath_page = "//li[@id='page%d']";
}

/*

    def set_filter_price(self, min,max):
        inpmin = self.w_xpath("//input[@id='price[min]']")
        inpmax = self.w_xpath("//input[@id='price[max]']")
        button =self.w_id("submitprice")


        inpmin.send_keys(str(min))
        inpmax.click()

        inpmax.send_keys(Keys.END)
        for i in range(10):
            inpmax.send_keys(Keys.BACKSPACE)
        inpmax.send_keys(str(max))

        button.click()

        self.w_id("reset_filterprice",10)
* */
