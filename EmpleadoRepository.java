package co.edu.elbosque.procureit.repository;

import co.edu.elbosque.procureit.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByUsername(String username);
}