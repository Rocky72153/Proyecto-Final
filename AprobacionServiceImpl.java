package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.entity.Aprobacion;
import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.repository.AprobacionRepository;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import co.edu.elbosque.procureit.service.AprobacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AprobacionServiceImpl implements AprobacionService {

	private static final BigDecimal UMBRAL_TI = new BigDecimal("0"); // >= 0 -> TI
	private static final BigDecimal UMBRAL_GERENTE = new BigDecimal("100"); // >= 100 -> GERENTE + TI
	private static final BigDecimal UMBRAL_DIRECTOR = new BigDecimal("1000"); // >= 1000 -> GERENTE + DIRECTOR + TI

	private final SolicitudRepository solRepo;
	private final AprobacionRepository aprRepo;

	public AprobacionServiceImpl(SolicitudRepository solRepo, AprobacionRepository aprRepo) {
		this.solRepo = solRepo;
		this.aprRepo = aprRepo;
	}

	@Override
	public List<Aprobacion> listarPorSolicitud(String solicitudId) {
		return aprRepo.findBySolicitud_SolicitudIdOrderByOrdenAsc(solicitudId);
	}

	@Override
	@Transactional
	public List<Aprobacion> generarFlujo(String solicitudId) {
		Solicitud s = solRepo.findById(solicitudId).orElseThrow();

		// 1) Limpia flujo previo para evitar duplicados (usar propiedad correcta:
		// solicitudId)
		aprRepo.deleteBySolicitud_SolicitudId(solicitudId);

		// 2) Construye nuevo flujo según monto
		List<Aprobacion> flujo = new ArrayList<>();
		BigDecimal monto = (s.getCostoEstimado() == null) ? BigDecimal.ZERO : s.getCostoEstimado();
		byte orden = 1;

		if (monto.compareTo(UMBRAL_DIRECTOR) >= 0) {
			flujo.add(nuevaAprobacion(s, Aprobacion.RolAprobador.GERENTE, orden++));
			flujo.add(nuevaAprobacion(s, Aprobacion.RolAprobador.DIRECTOR, orden++));
			flujo.add(nuevaAprobacion(s, Aprobacion.RolAprobador.TI, orden++));
		} else if (monto.compareTo(UMBRAL_GERENTE) >= 0) {
			flujo.add(nuevaAprobacion(s, Aprobacion.RolAprobador.GERENTE, orden++));
			flujo.add(nuevaAprobacion(s, Aprobacion.RolAprobador.TI, orden++));
		} else {
			flujo.add(nuevaAprobacion(s, Aprobacion.RolAprobador.TI, orden++));
		}

		aprRepo.saveAll(flujo);
		return aprRepo.findBySolicitud_SolicitudIdOrderByOrdenAsc(solicitudId);
	}

	private Aprobacion nuevaAprobacion(Solicitud s, Aprobacion.RolAprobador rol, byte orden) {
		Aprobacion a = new Aprobacion();
		a.setSolicitud(s);
		a.setRolAprobador(rol);
		a.setOrden(orden);
		a.setEstado(Aprobacion.Estado.PENDIENTE); // PENDIENTE|APROBADA|RECHAZADA|DEVUELTA_POR_INFO
		a.setFechaAsignacion(LocalDateTime.now());
		return a;
	}

	@Override
	@Transactional
	public void aprobar(Long id, String comentario) {
		Aprobacion a = aprRepo.findById(id).orElseThrow();
		a.setEstado(Aprobacion.Estado.APROBADA);
		a.setComentario(comentario);
		a.setFechaDecision(LocalDateTime.now());
		aprRepo.save(a);
	}

	@Override
	@Transactional
	public void rechazar(Long id, String comentario) {
		Aprobacion a = aprRepo.findById(id).orElseThrow();
		a.setEstado(Aprobacion.Estado.RECHAZADA);
		a.setComentario(comentario);
		a.setFechaDecision(LocalDateTime.now());
		aprRepo.save(a);
	}

	@Override
	@Transactional
	public void devolver(Long id, String comentario) {
		Aprobacion a = aprRepo.findById(id).orElseThrow();
		a.setEstado(Aprobacion.Estado.DEVUELTA_POR_INFO);
		a.setComentario(comentario);
		a.setFechaDecision(LocalDateTime.now());
		aprRepo.save(a);
	}
}
