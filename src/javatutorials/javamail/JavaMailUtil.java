/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatutorials.javamail;
/*import java.util.Properties; 
import javax.mail.Authenticator; 
import javax.mail.PasswordAuthentication; 
import javax.mail.Session; 
import sun.security.tools.KeyStoreUtil;

/**
 *
 * @author User
 */


public class JavaMailUtil {
    public static void sendMail(String recepient)
    {
       Properties properties = new Properties(); 

       properties.put("mail.smtp.auth", true); 
       properties.put("mail.smtp.starttls.enable", true); 
       properties.put("mail.smtp.host", "smtp.gmail.com"); 
    properties.put("mail.smtp.host", "587"); 
    
    String myAccountEmail = "xxxxx@gmail.com"; 
    String password ="xxxxxxxx"; 
   /* 
    Session session = Session.getInstance(properties, new Authenticator )
            {
         @Override
          PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(myAccountEmail , password); 
        
    }
    }
        Message message =prepareMessage(session); 
        
    }

    private static class Message prepareMessage(Session session)
    {

   Message message = new MimeMessage(session)
           message.setForm(new InterfaceAdress()); 
    }

        public Message() {
        }
    }*/
//}


import com.TunTripsPI.Services.UserCruds;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Lotfi
 */
public class JavaMailUtil {
      public static String a; 
      public static String context; 
    public static boolean  sendmail(String recipien,String context) {
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String accountemail="tuntrips2022@gmail.com";
        String password="123456789Loo";
        Session session =Session.getInstance(properties,new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
               
                return new javax.mail.PasswordAuthentication(accountemail, password); 
            }
           
});
          Message message = prepareMessage(session,accountemail,recipien,context);
        
        try {
            Transport.send(message);
            if(message!=null){
                return true ; 
            }
            System.out.println("Message send Successfully");
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }


        return false ; 
    }

    public static Message prepareMessage(Session session, String accountemail, String recipien,String context) {
        try {
           Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(accountemail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipien));
            message.setSubject("Hello TunTrips ... ");
            UserCruds uc =new UserCruds();
              a=CodeGen();
            message.setText(context+a);
            
          
            return message ; 
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    
    //code generator  for mail validation 
    public  static String CodeGen(){
        String caracters="ABCDEFJHIJKLMNOPQRSTUVWXYZ0123456789";
        String randomcode="";
        int lenght=6;
        Random rand=new Random();
        char[] text = new char[lenght];
        for(int i=0;i<lenght;i++){
            text[i]=caracters.charAt(rand.nextInt(caracters.length()));
        }
        for(int i=0;i<lenght;i++){
        randomcode+=text[i];
        
        }
        return randomcode ;
       
    }
    
}