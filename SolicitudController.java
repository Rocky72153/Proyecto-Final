package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.service.SolicitudService;
import co.edu.elbosque.procureit.service.AprobacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

	private final SolicitudService service;
	private final AprobacionService aprobacionService;

	public SolicitudController(SolicitudService service, AprobacionService aprobacionService) {
		this.service = service;
		this.aprobacionService = aprobacionService;
	}

	// Crear solicitud
	@PreAuthorize("hasAnyRole('SOLICITANTE','ADMIN','TI','GERENTE','DIRECTOR')")
	@PostMapping
	public ResponseEntity<Solicitud> crear(@RequestBody Solicitud s) {
		return ResponseEntity.ok(service.crear(s));
	}

	// Enviar solicitud
	@PreAuthorize("hasAnyRole('SOLICITANTE','ADMIN','TI','GERENTE','DIRECTOR')")
	@PostMapping("/{id}/enviar")
	public ResponseEntity<Solicitud> enviar(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.enviar(id));
	}

	// Actualizar solicitud
	@PreAuthorize("hasAnyRole('SOLICITANTE','ADMIN','TI','GERENTE','DIRECTOR')")
	@PutMapping("/{id}")
	public ResponseEntity<Solicitud> actualizar(@PathVariable("id") String id, @RequestBody Solicitud cambios) {
		return ResponseEntity.ok(service.actualizar(id, cambios));
	}

	// Listar solicitudes (con paginación y filtro por estado opcional)
	@PreAuthorize("hasAnyRole('SOLICITANTE','ADMIN','TI','GERENTE','DIRECTOR')")
	@GetMapping
	public ResponseEntity<Page<Solicitud>> listar(@RequestParam(value = "estado", required = false) String estado,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(service.listar(estado, pageable));
	}

	// >>> Endpoint que el frontend usa para generar/refresh del flujo de
	// aprobaciones
	@PreAuthorize("hasAnyRole('ADMIN','TI','GERENTE','DIRECTOR')")
	@PostMapping("/{id}/generar-flujo")
	public ResponseEntity<Void> generarFlujo(@PathVariable("id") String id) {
		aprobacionService.generarFlujo(id);
		return ResponseEntity.noContent().build();
	}
}
