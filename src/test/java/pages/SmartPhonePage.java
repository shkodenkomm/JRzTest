package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.HashMap;

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
            try{ sleep(5000);  }catch (InterruptedException e) {}
            goods_names.addAll(getWt(20).until(presenceOfAllElementsLocatedBy(By.xpath(xpath_goods_names))));

            goods_names.forEach(el -> r.add(el.getText()));
        }

        return  r;
    }

    public HashMap<String,Integer> get_titles_prises(int maxPage, String titles, String prises) {
        ArrayList<String> rt =new ArrayList<>();
        ArrayList<Integer> rp =new ArrayList<>();
        HashMap<String,Integer> res = new HashMap<>();



        ArrayList<WebElement> qt =new ArrayList<>(getWt(20).until(
                presenceOfAllElementsLocatedBy(
                        By.xpath(titles)
                )
        ));
        ArrayList<WebElement> qp =new ArrayList<>(getWt(20).until(
                presenceOfAllElementsLocatedBy(
                        By.xpath(prises)
                )
        ));

        qt.forEach(el -> rt.add(el.getText()));
        qp.forEach(el -> rp.add(new Integer( el.getText().replaceAll("[^0-9]",""))));

        for (int i=2; i<=maxPage; i++){
            qt.clear();
            qp.clear();

            try{ sleep(100);  }catch (InterruptedException e) {}
            getWt(20).until( elementToBeClickable(By.xpath(String.format(xpath_page,  i)))).click();
            try{ sleep(4000);  }catch (InterruptedException e) {}

            try {
                qt.addAll(getWt(10).until(presenceOfAllElementsLocatedBy(By.xpath(titles))));
                qp.addAll(getWt(10).until(presenceOfAllElementsLocatedBy(By.xpath(prises))));

                qt.forEach(el -> rt.add(el.getText()));
                qp.forEach(el -> rp.add(new Integer( el.getText().replaceAll("[^0-9]",""))));

            }catch(TimeoutException e ){
                System.out.print("empty page "+baseDriver.getCurrentUrl());
                continue;
            }

            for (int t = 0; t < rt.size() - 1; t++) {
                res.put(rt.get(t), rp.get(t));
            }
        }

        return  res;
    }

}


