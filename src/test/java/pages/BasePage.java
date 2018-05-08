package pages;

import org.apache.http.annotation.Contract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver baseDriver;

    public WebDriverWait getWt(int seconds){
        return new WebDriverWait(baseDriver, seconds);
    }

    public BasePage(WebDriver _drv){

        baseDriver = _drv;
        PageFactory.initElements(baseDriver, this);

    }


    public static boolean isNullOrEmpty(String param) {
        return param == null || param.trim().length() == 0;
    }

    public void closePopup(){
        try {
            getWt(1).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='popup-css lang-switcher-popup sprite-side']"))
            );

            WebElement close_button = getWt(1).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@name='close']"))
            );

            close_button.click();

            getWt(1).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='popup-css lang-switcher-popup sprite-side']"))
            );
        }catch (Exception e){

        }

    };
}
