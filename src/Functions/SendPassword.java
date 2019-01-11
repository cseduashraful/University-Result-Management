package Functions;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendPassword {
    public static void perform(String receiver, int code) {

    	String to = receiver;
        String subject = "Password";
        String msg ="Your new password is "+code;
        final String from ="resultprocessor@gmail.com";
        final  String password ="resultprocessorresultprocessor";


        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(from,password);
       }
       });

       //session.setDebug(true);
       try{
    	   Transport transport = session.getTransport();
           InternetAddress addressFrom = new InternetAddress(from);

           MimeMessage message = new MimeMessage(session);
           message.setSender(addressFrom);
           message.setSubject(subject);
           message.setContent(msg, "text/plain");
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           transport.connect();
           Transport.send(message);
           transport.close();

       }catch(Exception e){
    	   e.printStackTrace();
       }
       }


}
