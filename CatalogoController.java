package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.entity.CatalogoItem;
import co.edu.elbosque.procureit.service.CatalogoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

	private final CatalogoService service;

	public CatalogoController(CatalogoService service) {
		this.service = service;
	}

	// Listar catálogo (acceso amplio)
	@PreAuthorize("hasAnyRole('SOLICITANTE','ADMIN','TI','GERENTE','DIRECTOR')")
	@GetMapping
	public ResponseEntity<Page<CatalogoItem>> listar(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(service.listarActivos(PageRequest.of(page, size)));
	}

	// Crear ítem (solo ADMIN o TI)
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','TI')")
	public ResponseEntity<CatalogoItem> crear(@RequestBody CatalogoItem item) {
		return ResponseEntity.ok(service.crear(item));
	}

	// Actualizar ítem (solo ADMIN o TI)
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','TI')")
	public ResponseEntity<CatalogoItem> actualizar(@PathVariable Integer id, @RequestBody CatalogoItem item) {
		return ResponseEntity.ok(service.actualizar(id, item));
	}

	// Baja lógica (solo ADMIN o TI)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','TI')")
	public ResponseEntity<Void> baja(@PathVariable Integer id) {
		service.bajaLogica(id);
		return ResponseEntity.noContent().build();
	}
}
