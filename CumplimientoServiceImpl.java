package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.entity.Cumplimiento;
import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.entity.Solicitud.Estado;
import co.edu.elbosque.procureit.repository.CumplimientoRepository;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import co.edu.elbosque.procureit.service.CumplimientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class CumplimientoServiceImpl implements CumplimientoService {

	private final CumplimientoRepository repo;
	private final SolicitudRepository solRepo;

	public CumplimientoServiceImpl(CumplimientoRepository repo, SolicitudRepository solRepo) {
		this.repo = repo;
		this.solRepo = solRepo;
	}

	@Override
	@Transactional
	public Cumplimiento registrarCumplimiento(String solicitudId, Cumplimiento c) {
		Solicitud s = solRepo.findById(solicitudId).orElseThrow();
		c.setSolicitud(s);
		c.setFechaFinalizacion(LocalDateTime.now());

		Cumplimiento saved = repo.save(c);

		s.setEstado(Estado.COMPLETADA);
		s.setFechaActualizacion(LocalDateTime.now());
		solRepo.save(s);

		return saved;
	}
}
