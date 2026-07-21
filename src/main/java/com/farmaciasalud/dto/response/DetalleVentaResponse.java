package com.farmaciasalud.dto.response;

public class DetalleVentaResponse {
    private Long id;
    private Long medicamentoId;
    private String medicamentoNombre;
    private String medicamentoPresentacion;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public DetalleVentaResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getMedicamentoId() { return medicamentoId; } public void setMedicamentoId(Long id) { this.medicamentoId = id; }
    public String getMedicamentoNombre() { return medicamentoNombre; } public void setMedicamentoNombre(String n) { this.medicamentoNombre = n; }
    public String getMedicamentoPresentacion() { return medicamentoPresentacion; } public void setMedicamentoPresentacion(String p) { this.medicamentoPresentacion = p; }
    public Integer getCantidad() { return cantidad; } public void setCantidad(Integer c) { this.cantidad = c; }
    public Double getPrecioUnitario() { return precioUnitario; } public void setPrecioUnitario(Double p) { this.precioUnitario = p; }
    public Double getSubtotal() { return subtotal; } public void setSubtotal(Double s) { this.subtotal = s; }
}
