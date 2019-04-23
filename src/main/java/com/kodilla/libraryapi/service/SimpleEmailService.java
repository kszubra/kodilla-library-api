package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Mail;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@AllArgsConstructor
public class SimpleEmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    private final JavaMailSender javaMailSender;

    public void send(Mail mail) {

        LOGGER.info("Preparing mail to send");

        try{
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            LOGGER.info("Mail sent properly");
        } catch (MailException e) {
            LOGGER.error("Failed to send mail: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc() != null) {
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }
}
