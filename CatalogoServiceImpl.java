package co.edu.elbosque.procureit.service.impl;

import co.edu.elbosque.procureit.entity.CatalogoItem;
import co.edu.elbosque.procureit.repository.CatalogoItemRepository;
import co.edu.elbosque.procureit.service.CatalogoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoServiceImpl implements CatalogoService {

	private final CatalogoItemRepository repo;

	public CatalogoServiceImpl(CatalogoItemRepository repo) {
		this.repo = repo;
	}

	@Override
	public Page<CatalogoItem> listarActivos(Pageable pageable) {
		return repo.findByActivoTrue(pageable);
	}

	@Override
	@Transactional
	public CatalogoItem crear(CatalogoItem item) {
		return repo.save(item);
	}

	@Override
	@Transactional
	public CatalogoItem actualizar(Integer id, CatalogoItem cambios) {
		CatalogoItem it = repo.findById(id).orElseThrow();
		it.setActivo(cambios.getActivo());
		it.setNombre(cambios.getNombre());
		it.setProveedor(cambios.getProveedor());
		it.setCostoEstimado(cambios.getCostoEstimado());
		it.setSku(cambios.getSku());
		it.setVigenteDesde(cambios.getVigenteDesde());
		it.setVigenteHasta(cambios.getVigenteHasta());
		it.setTipo(cambios.getTipo());
		return repo.save(it);
	}

	@Override
	@Transactional
	public void bajaLogica(Integer id) {
		CatalogoItem it = repo.findById(id).orElseThrow();
		it.setActivo(false);
		repo.save(it);
	}
}
