package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.repository.AprobacionRepository;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
	private final SolicitudRepository solRepo;
	private final AprobacionRepository aprRepo;

	public ReporteController(SolicitudRepository s, AprobacionRepository a) {
		this.solRepo = s;
		this.aprRepo = a;
	}

	@GetMapping("/solicitudes")
	public ResponseEntity<Page<Solicitud>> filtrar(@RequestParam(required = false) String estado,
			@RequestParam(required = false) String tipo,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		Pageable p = PageRequest.of(page, size);

		return ResponseEntity.ok(solRepo.findAll(p));
	}

	@GetMapping("/kpis")
	public ResponseEntity<Map<String, Object>> kpis() {
		List<Solicitud> todas = solRepo.findAll();
		long total = todas.size();
		long rechazadas = todas.stream().filter(s -> s.getEstado().name().contains("RECHAZADA")).count();
		double rechazoPct = total == 0 ? 0 : (rechazadas * 100.0 / total);

		double promHoras = todas.stream()
				.filter(s -> s.getEstado() == Solicitud.Estado.EN_PROCESO
						|| s.getEstado() == Solicitud.Estado.COMPLETADA)
				.mapToDouble(s -> Duration.between(s.getFechaCreacion(), s.getFechaActualizacion()).toHours()).average()
				.orElse(0.0);

		return ResponseEntity.ok(Map.of("totalSolicitudes", total, "porcentajeRechazo", rechazoPct,
				"tiempoPromedioAprobacionHoras", promHoras));
	}
}
