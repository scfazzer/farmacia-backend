package com.farmaciasalud.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmaciasalud.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
}
