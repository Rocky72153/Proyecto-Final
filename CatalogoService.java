package co.edu.elbosque.procureit.service;

import co.edu.elbosque.procureit.entity.CatalogoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CatalogoService {
    Page<CatalogoItem> listarActivos(Pageable pageable);
    CatalogoItem crear(CatalogoItem item);
    CatalogoItem actualizar(Integer id, CatalogoItem cambios);
    void bajaLogica(Integer id);
}