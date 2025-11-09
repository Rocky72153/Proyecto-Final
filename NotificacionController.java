package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    private final NotificacionService mail;
    public NotificacionController(NotificacionService mail) { this.mail = mail; }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestParam String to) {
        String html = "<h3>Prueba ProcureIT</h3><p>Este es un correo de prueba.</p>";
        mail.enviarCorreoHTML(to, "Prueba ProcureIT", html);
        return ResponseEntity.ok().build();
    }
}
