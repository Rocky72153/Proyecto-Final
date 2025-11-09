package co.edu.elbosque.procureit.repository;

import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.entity.Solicitud.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SolicitudRepository extends JpaRepository<Solicitud, String> {
    Page<Solicitud> findByEstado(Estado estado, Pageable pageable);
}