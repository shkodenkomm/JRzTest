package tests;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import javax.mail.MessagingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static utils.JRzUtils.rz_send_mail;
import static utils.JRzUtils.sortByValue;
import static utils.JRzUtils.save_to_xlsx;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmartPhonesFilteredList {

    private static HashMap<String,Integer> tp, tp1;
    private static WebDriver drv ;
    private static BasePage bfPage;
    private static Path xlsx_file;



    @BeforeClass
    public static void beforeClass() {
        xlsx_file = Paths.get("").toAbsolutePath().resolve(Paths.get("src","test","java","files","smarts_titles_prises.xlsx"));
        System.setProperty("webdriver.chromedriver.driver","chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        options.setBinary("C:\\Users\\mm\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        drv = new ChromeDriver();
    }


    @AfterClass
    public static void afterClass() throws Exception {
        drv.close();
    }

    /** зайти на сайт rozetka.com.ua */
    @Test
    public void  test1_open_main_page()  throws Exception {
        bfPage = new RozetkaMain(drv,null).loadPage();

    }

    /** перейти в раздел "Телефоны, ТВ и электроника */
    @Test
    public void  test2_open_phones_tv_other()  throws Exception {
        assertNotNull("MainPage null", bfPage );
        bfPage = ((RozetkaMain)bfPage).openSmartTvDevises();
        assertNotNull("SmartTvDevisesPage  null", bfPage );
    }

    /** перейти в раздел 'Телефоны' */
    @Test
    public void  test3_open_phones() {
        assertNotNull("SmartTvDevisesPage null", bfPage );
        bfPage = ((SmartTvDevisesPage)bfPage).open_phones();
        assertNotNull("PhonePage  null", bfPage );
    }

    /** перейти в раздел 'Смартфоны' */
    @Test
    public void  test4_open_smartphones(){
        assertNotNull("PhonePage null", bfPage );
        bfPage = ((PhonePage)bfPage).open_smartphones();
        assertNotNull("SmartPhonePage  null", bfPage );
    }


    /** выбрать из первых трех страниц поисковой выдачи название и цены всех девайсов,
     обозначенных как “Топ продаж”
     */
    @Test
    public void test5_get_top_smart_title_price(){
        assertNotNull("SmartPhonePage null", bfPage );
        SmartPhonePage smarts =(SmartPhonePage)bfPage ;

        String titles = "//div[@id='catalog_goods_block']//div[@class='g-i-tile-i-box']//i[@name='prices_active_element_original'][contains(@class, 'g-tag-icon-middle-popularity')]/../../..//div[@class='g-i-tile-i-title clearfix']";
        String prises = "//div[@id='catalog_goods_block']//div[@class='g-i-tile-i-box']//i[@name='prices_active_element_original'][contains(@class, 'g-tag-icon-middle-popularity')]/../../..//div[contains(@class,'g-price-uah')]";

        tp = smarts.get_titles_prises(3, titles,prises);
        assertTrue(tp.size()>0);

    }

    /** выбрать из первых пяти страниц поисковой выдачи название и цены всех товаров в диапазоне от 3000 до 6000 гривен
     */
    @Test
    public void test6_get_smart_title_price_3000_6000(){
        assertNotNull("SmartPhonePage null", bfPage );
        SmartPhonePage smarts =(SmartPhonePage)bfPage ;

        smarts.setFilterPrice(3000,6000);
        String titles = "//div[@id='catalog_goods_block']//div[@class='g-i-tile-i-box']//div[@class='g-i-tile-i-title clearfix']";
        String prises = "//div[@id='catalog_goods_block']//div[@class='g-i-tile-i-box']//div[contains(@class,'g-price-uah')]";


        tp1 = smarts.get_titles_prises(5,titles, prises);

        assertTrue(tp1.size()>0);

    }

    @Test
    public void test7_set_info_to_xlsm_mail() throws MessagingException {
        assertNotNull("top_smart_title_price null", tp );
        assertNotNull("smart_title_price_3000_6000 null", tp1 );

        Map<String, Integer>sortedTp1 = sortByValue(tp1);

        save_to_xlsx(xlsx_file, tp, sortedTp1);

        ArrayList<String> to_list = new ArrayList<String>( ){};
        to_list.add("shkodenkomm@gmail.com");
        rz_send_mail("is file", to_list, xlsx_file);

    }

}


