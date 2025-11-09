package co.edu.elbosque.procureit.repository;

import co.edu.elbosque.procureit.entity.CatalogoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CatalogoItemRepository extends JpaRepository<CatalogoItem, Integer> {
    Page<CatalogoItem> findByActivoTrue(Pageable pageable);
}