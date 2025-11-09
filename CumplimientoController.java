package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.entity.Cumplimiento;
import co.edu.elbosque.procureit.service.CumplimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/cumplimientos")

public class CumplimientoController {
	private final CumplimientoService service;

	public CumplimientoController(CumplimientoService service) {
		this.service = service;
	}

	@PreAuthorize("hasAnyRole('SOLICITANTE','ADMIN','TI','GERENTE','DIRECTOR')")
	@PostMapping("/{solicitudId}")
	public ResponseEntity<Cumplimiento> registrar(@PathVariable String solicitudId, @RequestBody Cumplimiento c) {
		return ResponseEntity.ok(service.registrarCumplimiento(solicitudId, c));
	}
}
