package Services;

import Utils.MailService;

public class MailerService {
    public void replyMail(String mail ,String Username , String Description,String title) {
        String from = "zaher.amri@esprit.tn";
        String pass = "not today.";
        String[] to = {"" + mail}; // list of recipient email addresses
        String subject = ""+title;
        String body = "Greetings Mr  "+Username.toUpperCase()+ " We would like to inform you that our administration wanted to contact you as this :  \n "+Description+" . \n For more Please CONTACT our CLIENT SUPPORT AT IFOOD.COM/SUPPORT \n Best Regards. \n";
        MailService serv = new MailService();
        serv.sendFromGMail(from,pass,to,subject,body);
    }
}
