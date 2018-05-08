package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class PoroshkiDlyaStirkPage extends GoodsBasePage{
    public PoroshkiDlyaStirkPage(WebDriver baseDriver) {
        super(baseDriver);
    }

    public ArrayList<String> get_poroshki_za_100_300(int maxPage) {

        setFilterPrice(100,300);

        ArrayList<String> r =new ArrayList<>();
        ArrayList<WebElement> goods_names = new ArrayList<>(getWt(20).until(presenceOfAllElementsLocatedBy(By.xpath(xpath_goods_names))));
        goods_names.forEach(el -> r.add(el.getText()));

        for (int i=2; i< maxPage; i++){
            getWt(10).until(elementToBeClickable(By.xpath(String.format(xpath_page, i))));
            try{ sleep(5000);  }catch (InterruptedException e) {}
            goods_names.addAll(getWt(20).until(presenceOfAllElementsLocatedBy(By.xpath(xpath_goods_names))));
            goods_names.forEach(el -> r.add(el.getText()));
        }

        return r;

    }
}


/*
*  self.set_filter_price(100,300)
        self.close_popup()

        r=[]

        for pageN in range(1,6):

            WebDriverWait(self.drv, 10).until(
                expected_conditions.element_to_be_clickable(
                    (By.XPATH, self.xpath_page.format(pageN)))).\
            click()

            sleep(3)
            q = self.w_xpathes(self.xpath_goods_names,10)
            r.extend(list(map(lambda e : e.text, q)))

* */