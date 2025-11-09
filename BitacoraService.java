package co.edu.elbosque.procureit.service;

import co.edu.elbosque.procureit.entity.BitacoraEvento;
import co.edu.elbosque.procureit.entity.Solicitud;

public interface BitacoraService {
    BitacoraEvento registrar(Solicitud solicitud, BitacoraEvento.TipoEvento tipo, Integer actorId, String detalles);
}
