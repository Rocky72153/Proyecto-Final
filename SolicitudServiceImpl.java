package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.entity.Solicitud.Estado;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import co.edu.elbosque.procureit.service.SolicitudService;
import co.edu.elbosque.procureit.util.IdGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class SolicitudServiceImpl implements SolicitudService {

	private final SolicitudRepository repo;

	public SolicitudServiceImpl(SolicitudRepository repo) {
		this.repo = repo;
	}

	@Override
	@Transactional
	public Solicitud crear(Solicitud s) {
		s.setSolicitudId(IdGenerator.generarSolicitudId());
		s.setCodigoSeguimiento(IdGenerator.generarUUID());
		s.setEstado(Estado.NUEVA);
		s.setFechaCreacion(LocalDateTime.now());
		s.setFechaActualizacion(LocalDateTime.now());
		return repo.save(s);
	}

	@Override
	@Transactional
	public Solicitud enviar(String solicitudId) {
		Solicitud s = repo.findById(solicitudId).orElseThrow();
		s.setEstado(Estado.PENDIENTE_APROBACION);
		s.setFechaActualizacion(LocalDateTime.now());
		return repo.save(s);
	}

	@Override
	@Transactional
	public Solicitud actualizar(String solicitudId, Solicitud cambios) {
		Solicitud s = repo.findById(solicitudId).orElseThrow();

		s.setJustificacion(cambios.getJustificacion());
		s.setPrioridad(cambios.getPrioridad());
		s.setFechaRequerida(cambios.getFechaRequerida());
		s.setFechaActualizacion(LocalDateTime.now());
		return repo.save(s);
	}

	@Override
	public Page<Solicitud> listar(String estado, Pageable pageable) {
		if (estado == null || estado.isBlank()) {
			return repo.findAll(pageable);
		}
		return repo.findByEstado(Estado.valueOf(estado), pageable);
	}
}
