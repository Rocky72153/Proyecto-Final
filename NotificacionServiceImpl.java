package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.service.NotificacionService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificacionServiceImpl implements NotificacionService {
    private final JavaMailSender mailSender;

    public NotificacionServiceImpl(JavaMailSender mailSender) { this.mailSender = mailSender; }

    @Override
    public void enviarCorreoHTML(String to, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
 
        }
    }
}
