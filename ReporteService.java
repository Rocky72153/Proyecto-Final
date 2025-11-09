package co.edu.elbosque.procureit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.edu.elbosque.procureit.entity.Solicitud;

public interface ReporteService {
    Page<Solicitud> filtrar(String estado, String tipo, String desde, String hasta, String solicitante, Pageable pageable);
    // métodos para KPIs: tiempo promedio, % rechazadas, etc.
}