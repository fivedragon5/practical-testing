package sample.cafekiosk.spring.client.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSendClient {

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
        log.info("Sending email...");
        log.info("From: " + fromEmail);
        log.info("To: " + toEmail);
        log.info("Subject: " + subject);
        log.info("Content: " + content);
        return true;
    }
}
