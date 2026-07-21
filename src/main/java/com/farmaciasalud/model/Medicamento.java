package com.farmaciasalud.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

@Entity @Table(name = "medicamentos")
public class Medicamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(name = "principio_activo", nullable = false, length = 150)
    private String principioActivo;
    @Column(length = 100)
    private String presentacion;
    @Column(length = 50)
    private String concentracion;
    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta;
    @Column(name = "precio_compra")
    private Double precioCompra;
    @Column(nullable = false)
    private Integer stock = 0;
    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo = 5;
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;
    @Column(name = "requiere_receta", nullable = false)
    private Boolean requiereReceta = false;
    @Column(nullable = false)
    private Boolean activo = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;
    @CreationTimestamp @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Medicamento() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getPrincipioActivo() { return principioActivo; } public void setPrincipioActivo(String p) { this.principioActivo = p; }
    public String getPresentacion() { return presentacion; } public void setPresentacion(String p) { this.presentacion = p; }
    public String getConcentracion() { return concentracion; } public void setConcentracion(String c) { this.concentracion = c; }
    public Double getPrecioVenta() { return precioVenta; } public void setPrecioVenta(Double p) { this.precioVenta = p; }
    public Double getPrecioCompra() { return precioCompra; } public void setPrecioCompra(Double p) { this.precioCompra = p; }
    public Integer getStock() { return stock; } public void setStock(Integer s) { this.stock = s; }
    public Integer getStockMinimo() { return stockMinimo; } public void setStockMinimo(Integer s) { this.stockMinimo = s; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; } public void setFechaVencimiento(LocalDate f) { this.fechaVencimiento = f; }
    public Boolean getRequiereReceta() { return requiereReceta; } public void setRequiereReceta(Boolean r) { this.requiereReceta = r; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean a) { this.activo = a; }
    public Categoria getCategoria() { return categoria; } public void setCategoria(Categoria c) { this.categoria = c; }
    public Proveedor getProveedor() { return proveedor; } public void setProveedor(Proveedor p) { this.proveedor = p; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
