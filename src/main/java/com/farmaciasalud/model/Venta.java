package com.farmaciasalud.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;

@Entity @Table(name = "ventas")
public class Venta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Ahora apunta a la tabla clientes (puede ser null si cliente no registrado)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "total_venta", nullable = false)
    private Double totalVenta = 0.0;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoVenta estado = EstadoVenta.COMPLETADA;
    @Column(length = 300)
    private String observacion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();
    @CreationTimestamp @Column(name = "fecha_venta", updatable = false)
    private LocalDateTime fechaVenta;

    public enum EstadoVenta { COMPLETADA, ANULADA }
    public Venta() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; } public void setCliente(Cliente c) { this.cliente = c; }
    public Double getTotalVenta() { return totalVenta; } public void setTotalVenta(Double t) { this.totalVenta = t; }
    public EstadoVenta getEstado() { return estado; } public void setEstado(EstadoVenta e) { this.estado = e; }
    public String getObservacion() { return observacion; } public void setObservacion(String o) { this.observacion = o; }
    public Usuario getUsuario() { return usuario; } public void setUsuario(Usuario u) { this.usuario = u; }
    public List<DetalleVenta> getDetalles() { return detalles; } public void setDetalles(List<DetalleVenta> d) { this.detalles = d; }
    public LocalDateTime getFechaVenta() { return fechaVenta; } public void setFechaVenta(LocalDateTime f) { this.fechaVenta = f; }
}
