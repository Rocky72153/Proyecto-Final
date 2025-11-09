package co.edu.elbosque.procureit.service;

import co.edu.elbosque.procureit.entity.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SolicitudService {
    Solicitud crear(Solicitud solicitud);
    Solicitud enviar(String solicitudId);
    Solicitud actualizar(String solicitudId, Solicitud cambios);
    Page<Solicitud> listar(String estado, Pageable pageable);
}