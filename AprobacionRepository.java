package co.edu.elbosque.procureit.repository;

import co.edu.elbosque.procureit.entity.Aprobacion;
import co.edu.elbosque.procureit.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface AprobacionRepository extends JpaRepository<Aprobacion, Long> {

	// Obtiene las aprobaciones de una solicitud específica en orden ascendente
	List<Aprobacion> findBySolicitud_SolicitudIdOrderByOrdenAsc(String solicitudId);

	// Elimina todas las aprobaciones de una solicitud específica
	@Modifying
	@Transactional
	void deleteBySolicitud_SolicitudId(String solicitudId);

	// Busca todas las aprobaciones de una entidad Solicitud
	List<Aprobacion> findBySolicitudOrderByOrdenAsc(Solicitud solicitud);

	// Verifica si existen aprobaciones previas no finalizadas antes de cierto orden
	boolean existsBySolicitud_SolicitudIdAndOrdenLessThanAndEstadoNot(String solicitudId, byte orden,
			Aprobacion.Estado estado);
}
