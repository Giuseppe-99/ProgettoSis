package com.example.progettosis.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public String sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("clusteralgo@gmail.com");
        message.setTo("santorog65@gmail.com");
        message.setSubject("Prova");
        message.setText("Ciao");

        javaMailSender.send(message);

        return "Mail inviata correttamente";
    }

    public String sendMailAllegato(String path, String mail){
        try{
            MimeMessage message= javaMailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom("clusteralgo@gmail.com");
            messageHelper.setTo(mail);
            messageHelper.setSubject("Cluster");
            messageHelper.setText("In allegato è presente la rappresentazione grafica del cluster");

            File file = new File(path);

            messageHelper.addAttachment(file.getName(), file);

            javaMailSender.send(message);

            return "Mail inviata";
        }catch (Exception e){
            return "Mail non inviata";

        }
    }
}
