package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;

import static java.lang.Thread.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SmartPhonePage extends GoodsBasePage {
    public SmartPhonePage(WebDriver baseDriver) {
        super(baseDriver);

    }

    public ArrayList<String> get_goods_names_on_page(int pagesCount) {

        ArrayList<String> r =new ArrayList<>();
        ArrayList<WebElement> goods_names = new ArrayList<>(getWt(20).until(presenceOfAllElementsLocatedBy(By.xpath(xpath_goods_names))));
        goods_names.forEach(el -> r.add(el.getText()));


        for( int i=2;i<=pagesCount; i++){
            goods_names.clear();

            try{ sleep(500);  }catch (InterruptedException e) {}

            getWt(20).until( elementToBeClickable(By.xpath(String.format(xpath_page,  i)))).click();
            try{ sleep(3000);  }catch (InterruptedException e) {}
            goods_names.addAll(getWt(20).until(presenceOfAllElementsLocatedBy(By.xpath(xpath_goods_names))));

            goods_names.forEach(el -> r.add(el.getText()));
        }

        return  r;
    }
}
