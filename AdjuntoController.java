package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.entity.Adjunto;
import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.repository.AdjuntoRepository;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.HexFormat;


@RestController
@RequestMapping("/api/adjuntos")
public class AdjuntoController {
    private final AdjuntoRepository adjRepo;
    private final SolicitudRepository solRepo;

    public AdjuntoController(AdjuntoRepository a, SolicitudRepository s) { this.adjRepo = a; this.solRepo = s; }

    @PostMapping("/{solicitudId}")
    public ResponseEntity<Adjunto> subir(@PathVariable String solicitudId,
                                         @RequestParam("tipo") Adjunto.Tipo tipo,
                                         @RequestParam("file") MultipartFile file) throws Exception {
        Solicitud s = solRepo.findById(solicitudId).orElseThrow();
        File dest = File.createTempFile("adj_", "_" + file.getOriginalFilename(), new File("src/main/resources/uploads"));
        try (FileOutputStream fos = new FileOutputStream(dest)) {
            fos.write(file.getBytes());
        }
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(file.getBytes());
        String sha = HexFormat.of().formatHex(hash);

        Adjunto adj = new Adjunto();
        adj.setSolicitud(s);
        adj.setTipo(tipo);
        adj.setNombreArchivo(file.getOriginalFilename());
        adj.setUrl(dest.getPath());
        adj.setHashSha256(sha);
        adj.setFechaCarga(LocalDateTime.now());
        return ResponseEntity.ok(adjRepo.save(adj));
    }
}
