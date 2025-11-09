package co.edu.elbosque.procureit.service;

public interface NotificacionService {
    void enviarCorreoHTML(String to, String subject, String html);
}