package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PoroshkiDlaStyrky {
    private static WebDriver drv ;
    private static BasePage bfPage;
    private static Path fileGoodsNames;
    private static ArrayList<String> titles;



    @BeforeClass
    public static void beforeClass() {
        fileGoodsNames = Paths.get("").toAbsolutePath().resolve(Paths.get("src","test","java","files","fileGoodsNames.txt"));
        System.setProperty("webdriver.chromedriver.driver","chromedriver");
        drv = new ChromeDriver();
    }


    @AfterClass
    public static void afterClass() {
        drv.close();
    }

    /** зайти на сайт rozetka.com.ua */
    @Test
    public void  test1_open_main_page()   {
        bfPage = new RozetkaMain(drv,null).loadPage();

    }

    /** перейти в раздел 'Товары для дома'  */
    @Test
    public void  test2_open_tovary_dlya_doma(){
        assertNotNull("MainPage null", bfPage );
        bfPage =((RozetkaMain)bfPage).openTovaryDliaDoma();
        assertNotNull("TovaryDliaDomaPage null", bfPage);
    }

    /** перейти в раздел 'Бытовая химия' */
    @Test
    public void  test3_open_bytovaya_himiya(){
        assertNotNull("TovaryDliaDomaPage null", bfPage );
        bfPage =((TovaryDliaDomaPage)bfPage).openBytovayaHimiyaPage();
        assertNotNull("BytovayaHimiyaPage null", bfPage);
    }

    /** перейти в раздел 'Для стирки' */
    @Test
    public void  test4_open_sredstva_dlya_stirki(){
        assertNotNull("BytovayaHimiyaPage null", bfPage );
        bfPage =((BytovayaHimiyaPage)bfPage).openSredstvaDlyaStirki();
        assertNotNull("SredstvaDlyaStirkiPage null", bfPage);
    }

    /** перейти в раздел 'Порошки для стирки' */
    @Test
    public void  test5_open_poroshki_dlya_stirki(){
        assertNotNull("BytovayaHimiyaPage null", bfPage );
        bfPage =((SredstvaDlyaStirkiPage)bfPage).openPoroshkiDlyaStirk();
        assertNotNull("PoroshkiDlyaStirkiPage null", bfPage);
    }

    /**выбрать из первых пяти страниц поисковой выдачи
     * название и цены всех товаров в диапазоне от 100 до 300 гриве */
    @Test
    public void  test6_get_poroshki_za_100_300(){
        assertNotNull("BytovayaHimiyaPage null", bfPage );
        ArrayList<String> ps =((PoroshkiDlyaStirkPage)bfPage).get_poroshki_za_100_300(5);
        assertNotNull(ps);
        assertTrue(ps.size()>0);
    }
}

/*
    def test7_save_to_db(self):
        """записать полученные результаты в базу данных"""
        self.assertTrue("rlist" in self.parent_suite.params)

        id = None

        id = utils.save_to_db({"date": datetime.now(),
                               "results":self.parent_suite.params["rlist"]
                               })

        self.assertIsNotNone(id)
        self.log.info(str(id.inserted_id))
* */
