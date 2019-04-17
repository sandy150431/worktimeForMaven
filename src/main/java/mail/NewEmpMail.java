package mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NewEmpMail {

	public static void sendMail(String recipient) {

		String host = "localhost";
		int port = 25;
		final String username = "sandy150431@gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		//props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(props, null);

		try {
			MimeMessage msg = new MimeMessage(session);
		      //set message headers
		      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		      msg.addHeader("format", "flowed");
		      msg.addHeader("Content-Transfer-Encoding", "8bit");

		      msg.setFrom(new InternetAddress(username, "NoReply-JD"));
		      //msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
		      msg.setSubject("你的帳號已被建立", "UTF-8");
		      msg.setText("帳號為員工編號，密碼為你的身分證，請於登入後修改密碼", "UTF-8");
		      msg.setSentDate(new Date());
		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
	    	  Transport.send(msg);  

		      System.out.println("EMail Sent Successfully!!");
		      
		} catch (MessagingException e) {
			//throw new RuntimeException(e);
		}catch (Exception e) {
			//throw new RuntimeException(e);
		}
	}
	
}