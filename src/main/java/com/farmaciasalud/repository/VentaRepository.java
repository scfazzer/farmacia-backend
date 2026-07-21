package com.farmaciasalud.repository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farmaciasalud.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByUsuarioIdOrderByFechaVentaDesc(Long usuarioId);
    List<Venta> findByClienteIdOrderByFechaVentaDesc(Long clienteId);
    List<Venta> findByFechaVentaBetweenOrderByFechaVentaDesc(LocalDateTime desde, LocalDateTime hasta);
    List<Venta> findByEstado(Venta.EstadoVenta estado);

    @Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v WHERE v.estado = 'COMPLETADA' AND v.fechaVenta BETWEEN :desde AND :hasta")
    Double sumaTotalVentasCompletadas(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);

    @Query("SELECT COUNT(v) FROM Venta v WHERE v.estado = 'COMPLETADA' AND v.fechaVenta BETWEEN :desde AND :hasta")
    Long contarVentasCompletadas(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);
}
