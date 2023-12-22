package com.airline_reservation_system.Service;

import com.airline_reservation_system.Model.Admin;
import com.airline_reservation_system.Model.Customer;
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

        String body = String.format("Welcome to Sam Airlines," + "\n" +
                "You've been successfully added as 'Admin' in Sam Airlines." + "\n" +
                "Your Admin Id: '%s' " + "\n" +
                "Your Username: '%s' " + "\n" +
                "Your Password: '%s' " + "\n" +
                "Thank you.", id, username, password);

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

        String body = String.format("Welcome to Sam Airlines, " + "\n" +
                "Your Admin details have been successfully updated: " + "\n" +
                "Your Admin Id: '%s' " + "\n" +
                "Your Username: '%s' " + "\n" +
                "Your Password: '%s' " + "\n" +
                "Your phoneNumber: '%s' " + "\n" +
                "Your email: '%s' " + "\n" +
                "Your address: '%s' " + "\n" +
                "Thank you.", id, username, password, phoneNumber, email, address);

        sendMail(to, subject, body);
    }

    public void deleteAdminMail(Admin admin) {
        String to = admin.getEmail();
        String subject = "Deleted admin successfully.";
        String body = "Admin deleted successfully from our database." +
                "Thank you.";

        sendMail(to, subject, body);
    }
    public void saveNewCustomerMail(Customer customer) {
        String to = customer.getEmail();
        String sub = "You've been added as Customer.";
        String id = String.valueOf(customer.getId());
        String username = customer.getUserName();
        String password = customer.getPassword();

        String body = String.format("Welcome to Sam Airlines," + "\n" +
                "You've been successfully added as 'Customer' in Sam Airlines." + "\n" +
                "Your Customer Id: '%s' " + "\n" +
                "Your Username: '%s' " + "\n" +
                "Your Password: '%s' " + "\n" +
                "Thank you.", id, username, password);

        sendMail(to, sub, body);
    }
}
