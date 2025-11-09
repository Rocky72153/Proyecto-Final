package co.edu.elbosque.procureit.service;

import co.edu.elbosque.procureit.entity.Aprobacion;
import java.util.List;

public interface AprobacionService {
	/** Devuelve el flujo de aprobaciones de una solicitud (sin crear nada). */
	List<Aprobacion> listarPorSolicitud(String solicitudId);

	/** Genera (o regenera) el flujo de aprobaciones y lo retorna ordenado. */
	List<Aprobacion> generarFlujo(String solicitudId);

	void aprobar(Long aprobacionId, String comentario);

	void rechazar(Long aprobacionId, String comentario);

	void devolver(Long aprobacionId, String comentario);
}
