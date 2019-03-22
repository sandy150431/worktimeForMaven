package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;


public class NewEmpMail {
 public static void main(String args[]) {
	 
  String host = "smtp.gmail.com";
  int port = 587;
  final String username = "dingq84@gmail.com";
  final String password = "ding5802";//your password

  Properties props = new Properties();
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.port", port);
  Session session = Session.getInstance(props, new Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
   }
  });

 try {
//	  String s;
//	  int i = (int)(Math.random() * 10000000 +1);
//	   System.out.println(i);
//	    s = String.valueOf(i);
	 
   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress("dingq85@gmail.com"));
   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dingq85@gmail.com"));
   message.setSubject("你的帳號已被建立");
   message.setText("帳號為員工編號，密碼為你的身分證，請於登入後修改密碼");
   
   Transport transport = session.getTransport("smtp");
   transport.connect(host, port, username, password);

   Transport.send(message);
  
   System.out.println("寄送email結束.");
   

  } catch (MessagingException e) {
   throw new RuntimeException(e);
  }
 }
}