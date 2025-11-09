package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.entity.BitacoraEvento;
import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.repository.BitacoraEventoRepository;
import co.edu.elbosque.procureit.service.BitacoraService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BitacoraServiceImpl implements BitacoraService {
	private final BitacoraEventoRepository repo;

	public BitacoraServiceImpl(BitacoraEventoRepository repo) {
		this.repo = repo;
	}

	@Override
	public BitacoraEvento registrar(Solicitud s, BitacoraEvento.TipoEvento tipo, Integer actorId, String detalles) {
		BitacoraEvento b = new BitacoraEvento();
		b.setSolicitud(s);
		b.setTipoEvento(tipo);
		b.setFechaEvento(LocalDateTime.now());
		b.setDetalles(detalles);

		return repo.save(b);
	}
}
