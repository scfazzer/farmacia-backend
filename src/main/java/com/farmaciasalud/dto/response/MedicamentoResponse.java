package com.farmaciasalud.dto.response;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicamentoResponse {
    private Long id;
    private String nombre;
    private String principioActivo;
    private String presentacion;
    private String concentracion;
    private Double precioVenta;
    private Double precioCompra;
    private Integer stock;
    private Integer stockMinimo;
    private Boolean stockBajo;
    private LocalDate fechaVencimiento;
    private Boolean requiereReceta;
    private Boolean activo;
    private Long categoriaId;
    private String categoriaNombre;
    private Long proveedorId;
    private String proveedorNombre;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MedicamentoResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getPrincipioActivo() { return principioActivo; } public void setPrincipioActivo(String p) { this.principioActivo = p; }
    public String getPresentacion() { return presentacion; } public void setPresentacion(String p) { this.presentacion = p; }
    public String getConcentracion() { return concentracion; } public void setConcentracion(String c) { this.concentracion = c; }
    public Double getPrecioVenta() { return precioVenta; } public void setPrecioVenta(Double p) { this.precioVenta = p; }
    public Double getPrecioCompra() { return precioCompra; } public void setPrecioCompra(Double p) { this.precioCompra = p; }
    public Integer getStock() { return stock; } public void setStock(Integer s) { this.stock = s; }
    public Integer getStockMinimo() { return stockMinimo; } public void setStockMinimo(Integer s) { this.stockMinimo = s; }
    public Boolean getStockBajo() { return stockBajo; } public void setStockBajo(Boolean s) { this.stockBajo = s; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; } public void setFechaVencimiento(LocalDate f) { this.fechaVencimiento = f; }
    public Boolean getRequiereReceta() { return requiereReceta; } public void setRequiereReceta(Boolean r) { this.requiereReceta = r; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean a) { this.activo = a; }
    public Long getCategoriaId() { return categoriaId; } public void setCategoriaId(Long id) { this.categoriaId = id; }
    public String getCategoriaNombre() { return categoriaNombre; } public void setCategoriaNombre(String n) { this.categoriaNombre = n; }
    public Long getProveedorId() { return proveedorId; } public void setProveedorId(Long id) { this.proveedorId = id; }
    public String getProveedorNombre() { return proveedorNombre; } public void setProveedorNombre(String n) { this.proveedorNombre = n; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
    public LocalDateTime getUpdatedAt() { return updatedAt; } public void setUpdatedAt(LocalDateTime u) { this.updatedAt = u; }
}
