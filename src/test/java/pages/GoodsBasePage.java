package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class GoodsBasePage extends  BasePage {
    public GoodsBasePage(WebDriver _drv) {
        super(_drv);
    }

    @FindBy(xpath = "//ul[@name='paginator']")
    protected WebElement paginator;


    protected String xpath_goods_names ="//div[@id='catalog_goods_block']/div//div[@class='g-i-tile-i-title clearfix']/a";
    protected String xpath_page = "//li[@id='page%d']";

    public void setFilterPrice(int min, int max){
        WebElement inpmin = baseDriver.findElement (By.xpath("//input[@id='price[min]']"));
        WebElement inpmax = baseDriver.findElement (By.xpath("//input[@id='price[max]']"));
        WebElement button = baseDriver.findElement (By.id("submitprice"));

        inpmin.sendKeys(Integer.toString(min));
        inpmax.click();

        inpmax.sendKeys(Keys.END);
        for (int i=1; i<=10; i++){
            inpmax.sendKeys(Keys.BACK_SPACE);
        }

        inpmax.sendKeys(Integer.toString(max));

        button.click();

        getWt(10).until(elementToBeClickable(By.id("reset_filterprice")));



    }
}
