package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.entity.Aprobacion;
import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.repository.AprobacionRepository;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import co.edu.elbosque.procureit.service.BitacoraService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

@Component
public class EscalamientoScheduler {

	private final AprobacionRepository aprobacionRepository;
	private final SolicitudRepository solicitudRepository;
	private final BitacoraService bitacora;

	public EscalamientoScheduler(AprobacionRepository a, SolicitudRepository s, BitacoraService b) {
		this.aprobacionRepository = a;
		this.solicitudRepository = s;
		this.bitacora = b;
	}

	@Scheduled(fixedDelay = 30 * 60 * 1000)
	public void escalarAprobaciones() {
		List<Solicitud> solicitudes = solicitudRepository.findAll();

		for (Solicitud sol : solicitudes) {
			List<Aprobacion> flujo = aprobacionRepository.findBySolicitudOrderByOrdenAsc(sol);

			flujo.stream().filter(a -> a.getEstado() == Aprobacion.Estado.PENDIENTE).forEach(a -> {
				long horas = Duration.between(a.getFechaAsignacion(), LocalDateTime.now()).toHours();
				if (horas >= 48) {

					a.setEstado(Aprobacion.Estado.DEVUELTA_POR_INFO);
					a.setFechaDecision(LocalDateTime.now());
					aprobacionRepository.save(a);

					bitacora.registrar(sol, co.edu.elbosque.procureit.entity.BitacoraEvento.TipoEvento.ACTUALIZACION,
							null, "Escalamiento automático tras 48h sin decisión en aprobador " + a.getRolAprobador());
				}
			});
		}
	}
}
