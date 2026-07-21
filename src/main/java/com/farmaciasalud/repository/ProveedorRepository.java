package com.farmaciasalud.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmaciasalud.model.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findByRuc(String ruc);
    List<Proveedor> findByActivoTrue();
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByRuc(String ruc);
}
