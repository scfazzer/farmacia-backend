package com.farmaciasalud.repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farmaciasalud.model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    Page<Medicamento> findByActivoTrue(Pageable pageable);
    long countByActivoTrue();

    @Query("SELECT m FROM Medicamento m WHERE " +
           "(LOWER(m.nombre) LIKE LOWER(CONCAT('%',:q,'%')) OR " +
           "LOWER(m.principioActivo) LIKE LOWER(CONCAT('%',:q,'%'))) AND m.activo = true")
    Page<Medicamento> buscarActivos(@Param("q") String q, Pageable pageable);

    List<Medicamento> findByCategoriaIdAndActivoTrue(Long categoriaId);
    List<Medicamento> findByProveedorIdAndActivoTrue(Long proveedorId);

    @Query("SELECT m FROM Medicamento m WHERE m.stock <= m.stockMinimo AND m.activo = true ORDER BY m.stock ASC")
    List<Medicamento> findStockBajo();

    @Query("SELECT m FROM Medicamento m WHERE m.fechaVencimiento BETWEEN :hoy AND :limite AND m.activo = true ORDER BY m.fechaVencimiento ASC")
    List<Medicamento> findProximosAVencer(@Param("hoy") LocalDate hoy, @Param("limite") LocalDate limite);

    List<Medicamento> findByRequiereRecetaAndActivoTrue(Boolean requiereReceta);
}
