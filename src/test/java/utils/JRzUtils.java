package utils;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.nio.file.Path;
import java.security.Security;
import java.util.List;
import java.util.Properties;

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
}
