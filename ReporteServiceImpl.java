package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import co.edu.elbosque.procureit.service.ReporteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {
	private final SolicitudRepository repo;

	public ReporteServiceImpl(SolicitudRepository repo) {
		this.repo = repo;
	}

	@Override
	public Page<Solicitud> filtrar(String estado, String tipo, String desde, String hasta, String solicitante,
			Pageable pageable) {

		return repo.findAll(pageable);
	}
}
