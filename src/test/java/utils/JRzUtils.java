package utils;

import com.sun.mail.smtp.SMTPTransport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.Security;
import java.util.*;


public class JRzUtils {

    public static void rz_send_mail(String body_text, List<String> to_list, Path file) throws MessagingException {

        String login = "jrztest@meta.ua";
        String password ="1234567890";

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.meta.ua");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);


        msg.setFrom(new InternetAddress(login));


        to_list.forEach(
                to -> {try{
                         msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
                        }catch(AddressException e){
                            e.printStackTrace(); }
                        catch (MessagingException e) {
                            e.printStackTrace();
                        }
                       });

        msg.setSubject("test mail");
        msg.setText("file added", "utf-8");
        if(file != null){
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(String.valueOf(file.toAbsolutePath()));
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(String.valueOf(file.getFileName()));
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
        }

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp.meta.ua", login, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

    public  static void save_to_xlsx(Path f, Map<String,Integer> t1,Map<String,Integer> t2){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sh1 = workbook.createSheet("Top sale");
        XSSFSheet sh2 = workbook.createSheet("from 3000 to 6000 uah");

        int rowNum = 0;

        for (String key: t1.keySet()) {
            Row row = sh1.createRow(rowNum++);
            row.createCell(0).setCellValue(key);
            row.createCell(1).setCellValue(t1.get(key));
        }

        rowNum = 0;

        for (String key: t2.keySet()) {
            Row row = sh2.createRow(rowNum++);
            row.createCell(0).setCellValue(key);
            row.createCell(1).setCellValue(t2.get(key));
        }

        try ( FileOutputStream outputStream = new FileOutputStream(String.valueOf(f.toAbsolutePath()))){
            workbook.write(outputStream);
            workbook.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }
}
