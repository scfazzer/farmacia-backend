package com.farmaciasalud.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmaciasalud.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDni(String dni);
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    List<Cliente> findByActivoTrue();
    boolean existsByDni(String dni);
}
