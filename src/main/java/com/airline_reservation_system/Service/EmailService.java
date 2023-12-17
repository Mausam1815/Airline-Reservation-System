package com.airline_reservation_system.Service;

import com.airline_reservation_system.Model.Admin;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            javaMailSender.send(message);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNewAdminMail(Admin admin) {
        String to = admin.getEmail();
        String subject = "You've been added as Admin.";

        String id = String.valueOf(admin.getId());
        String username = admin.getUserName();
        String password = admin.getPassword();

        String body = "Welcome to Sam Airlines," + "\n" +
                "You've been successfully added as 'Admin' in Sam Airlines." + "\n" +
                "Your Admin Id: " + "'"+id+"'" + "\n" +
                "Your Username: " + "'"+username+"'" + "\n" +
                "Your Password: " + "'"+password+"'" + "\n" +
                "Thank you.";

        sendMail(to, subject, body);
    }
    public void updateAdminMail(Admin admin) {
        String to = admin.getEmail();
        String subject = "Admin details are updated.";

        String id = String.valueOf(admin.getId());
        String username = admin.getUserName();
        String password = admin.getPassword();
        String phoneNumber = admin.getPhoneNumber();
        String email = admin.getEmail();
        String address = admin.getAddress();

        String body = String.format("Welcome to Sam Airlines, " +
                "Your Admin details have been successfully updated: " +
                "Your Admin Id: '%s' " +
                "Your Username: '%s' " +
                "Your Password: '%s' " +
                "Your phoneNumber: '%s' " +
                "Your email: '%s' " +
                "Your address: '%s' " +
                "Thank you.", id, username, password, phoneNumber, email, address);

        sendMail(to, subject, body);
    }
}
