package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Services.MailProject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("api/mail")
@AllArgsConstructor
public class MailController {

    @Autowired
    private MailProject mailProject;

    @PostMapping("/sendmail")
    public String sendMail(@RequestBody MailRequest request) {
        try {
            mailProject.send(request.getTo(), request.getSubject(), request.getBody());
            return "Email envoyé avec succès à " + request.getTo();
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Erreur lors de l'envoi de l'email à " + request.getTo();
        }
    }

    // Classe interne pour représenter la demande de courrier
    public static class MailRequest {
        private String to;
        private String subject;
        private String body;

        // Getters et setters
        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
