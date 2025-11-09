package co.edu.elbosque.procureit.service;

import co.edu.elbosque.procureit.entity.Cumplimiento;

public interface CumplimientoService {
    Cumplimiento registrarCumplimiento(String solicitudId, Cumplimiento cumplimiento);
}