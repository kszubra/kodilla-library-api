package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Mail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@AllArgsConstructor
@Slf4j
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;

    public void send(Mail mail) {

        log.info("Preparing mail to send");

        try{
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Mail sent properly");
        } catch (MailException e) {
            log.error("Failed to send mail: ", e.getMessage(), e);
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
