package com.farmaciasalud.model;
import jakarta.persistence.*;

@Entity @Table(name = "detalle_ventas")
public class DetalleVenta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;
    @Column(nullable = false)
    private Double subtotal;

    public DetalleVenta() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Venta getVenta() { return venta; } public void setVenta(Venta v) { this.venta = v; }
    public Medicamento getMedicamento() { return medicamento; } public void setMedicamento(Medicamento m) { this.medicamento = m; }
    public Integer getCantidad() { return cantidad; } public void setCantidad(Integer c) { this.cantidad = c; }
    public Double getPrecioUnitario() { return precioUnitario; } public void setPrecioUnitario(Double p) { this.precioUnitario = p; }
    public Double getSubtotal() { return subtotal; } public void setSubtotal(Double s) { this.subtotal = s; }
}
