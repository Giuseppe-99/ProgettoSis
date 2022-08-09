package com.example.progettosis.Controllers;

import com.example.progettosis.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/send")
public class MailController {
    @Autowired
    EmailService emailService;

    @GetMapping("/invia")
    public String sendMail(){
        return emailService.sendEmail();
    }

    /*
    @GetMapping("/allegato")
    public String sendMailAllegato(@RequestParam("path")String path){
        return emailService.sendMailAllegato(path);
    }

     */


}
