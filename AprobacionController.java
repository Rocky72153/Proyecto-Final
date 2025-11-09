package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.entity.Aprobacion;
import co.edu.elbosque.procureit.service.AprobacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aprobaciones")
public class AprobacionController {

	private final AprobacionService service;

	public AprobacionController(AprobacionService service) {
		this.service = service;
	}

	// Listar aprobaciones existentes de una solicitud (no recrea el flujo)
	@GetMapping("/{solicitudId}")
	@PreAuthorize("hasAnyRole('GERENTE','DIRECTOR','TI','ADMIN','SOLICITANTE')")
	public ResponseEntity<List<Aprobacion>> listar(@PathVariable String solicitudId) {
		return ResponseEntity.ok(service.listarPorSolicitud(solicitudId));
	}

	// Generar/Refrescar el flujo explícitamente (único endpoint POST para flujo)
	@PostMapping("/flujo/{solicitudId}")
	@PreAuthorize("hasAnyRole('GERENTE','DIRECTOR','TI','ADMIN')")
	public ResponseEntity<List<Aprobacion>> generarFlujo(@PathVariable String solicitudId) {
		return ResponseEntity.ok(service.generarFlujo(solicitudId));
	}

	// Aprobar
	@PostMapping("/{id}/aprobar")
	@PreAuthorize("hasAnyRole('GERENTE','DIRECTOR','TI','ADMIN')")
	public ResponseEntity<Void> aprobar(@PathVariable Long id,
			@RequestParam(required = false, defaultValue = "") String comentario) {
		service.aprobar(id, comentario);
		return ResponseEntity.noContent().build();
	}

	// Rechazar
	@PostMapping("/{id}/rechazar")
	@PreAuthorize("hasAnyRole('GERENTE','DIRECTOR','TI','ADMIN')")
	public ResponseEntity<Void> rechazar(@PathVariable Long id,
			@RequestParam(required = false, defaultValue = "") String comentario) {
		service.rechazar(id, comentario);
		return ResponseEntity.noContent().build();
	}

	// Devolver (solicitar info)
	@PostMapping("/{id}/devolver")
	@PreAuthorize("hasAnyRole('GERENTE','DIRECTOR','TI','ADMIN')")
	public ResponseEntity<Void> devolver(@PathVariable Long id,
			@RequestParam(required = false, defaultValue = "") String comentario) {
		service.devolver(id, comentario);
		return ResponseEntity.noContent().build();
	}
}
