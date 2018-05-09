package tests;

import org.junit.*;

import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import utils.JRzUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmartPhonesList {

        private static WebDriver drv ;
        protected static BasePage bfPage;
        private static Path fileGoodsNames;


        @BeforeClass
        public static void beforeClass() {
            fileGoodsNames = Paths.get("").toAbsolutePath().resolve(Paths.get("src","test","java","files","fileGoodsNames.txt"));
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
            SmartTvDevisesPage std =((RozetkaMain)bfPage).openSmartTvDevises();
            bfPage = std;

            assertNotNull("SmartTvDevisesPage  null", bfPage );
        }

        /** перейти в раздел 'Телефоны' */
        @Test
        public void  test3_open_phones() {
            assertNotNull("SmartTvDevisesPage null", bfPage );
            PhonePage phones =((SmartTvDevisesPage)bfPage).open_phones();
            bfPage = phones;

            assertNotNull("PhonePage  null", bfPage );
        }

        /** перейти в раздел 'Смартфоны' */
        @Test
        public void  test4_open_smartphones(){
            assertNotNull("PhonePage null", bfPage );
            SmartPhonePage smpp =((PhonePage)bfPage).open_smartphones();
            bfPage = smpp;

            assertNotNull("SmartPhonePage  null", bfPage );
        }


        /** выбрать из первых трех страниц поисковой выдачи название всех девайсов
            записать полученные результаты в текстовый файл
         */
        @Test
        public void  test5_get_devises_names() throws IOException {
            assertNotNull("SmartPhonePage null", bfPage );
            SmartPhonePage smarts =(SmartPhonePage)bfPage ;

            ArrayList<String> devises_names = smarts.get_goods_names_on_page(3);

            assertNotNull(devises_names);
            assertTrue(devises_names.size()>0);

            Files.write(fileGoodsNames, devises_names, Charset.forName("UTF-8"));

            assertTrue(Files.exists(fileGoodsNames));
            assertTrue(Files.size(fileGoodsNames)>1);

        }

        /** отправить данный файл по списку рассылки
           (e-mails из отдельного файла) */
        @Test
        public void test6_sand_emails() throws MessagingException {
            assertTrue(Files.exists(fileGoodsNames));
            ArrayList<String> to_list = new ArrayList<String>(){};
            to_list.add("shkodenkomm@gmail.com");
            JRzUtils.rz_send_mail("is file", to_list, fileGoodsNames );
        }



}
