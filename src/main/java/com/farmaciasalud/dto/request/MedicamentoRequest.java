package com.farmaciasalud.dto.request;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class MedicamentoRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 200)
    private String nombre;

    @NotBlank(message = "El principio activo es obligatorio")
    @Size(max = 150)
    private String principioActivo;

    @Size(max = 100)
    private String presentacion;

    @Size(max = 50)
    private String concentracion;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio de venta debe ser mayor a 0")
    private Double precioVenta;

    @DecimalMin(value = "0.0", message = "El precio de compra no puede ser negativo")
    private Double precioCompra;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo = 5;

    private LocalDate fechaVencimiento;
    private Boolean requiereReceta = false;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;

    @NotNull(message = "El proveedor es obligatorio")
    private Long proveedorId;

    public MedicamentoRequest() {}
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
    public Long getCategoriaId() { return categoriaId; } public void setCategoriaId(Long id) { this.categoriaId = id; }
    public Long getProveedorId() { return proveedorId; } public void setProveedorId(Long id) { this.proveedorId = id; }
}
