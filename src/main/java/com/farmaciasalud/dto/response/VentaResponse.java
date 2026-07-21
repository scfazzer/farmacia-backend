package com.farmaciasalud.dto.response;
import java.time.LocalDateTime;
import java.util.List;

public class VentaResponse {
    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private String clienteDni;
    private Double totalVenta;
    private String estado;
    private String observacion;
    private Long usuarioId;
    private String usuarioNombre;
    private LocalDateTime fechaVenta;
    private List<DetalleVentaResponse> detalles;

    public VentaResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; } public void setClienteId(Long id) { this.clienteId = id; }
    public String getClienteNombre() { return clienteNombre; } public void setClienteNombre(String n) { this.clienteNombre = n; }
    public String getClienteDni() { return clienteDni; } public void setClienteDni(String d) { this.clienteDni = d; }
    public Double getTotalVenta() { return totalVenta; } public void setTotalVenta(Double t) { this.totalVenta = t; }
    public String getEstado() { return estado; } public void setEstado(String e) { this.estado = e; }
    public String getObservacion() { return observacion; } public void setObservacion(String o) { this.observacion = o; }
    public Long getUsuarioId() { return usuarioId; } public void setUsuarioId(Long id) { this.usuarioId = id; }
    public String getUsuarioNombre() { return usuarioNombre; } public void setUsuarioNombre(String n) { this.usuarioNombre = n; }
    public LocalDateTime getFechaVenta() { return fechaVenta; } public void setFechaVenta(LocalDateTime f) { this.fechaVenta = f; }
    public List<DetalleVentaResponse> getDetalles() { return detalles; } public void setDetalles(List<DetalleVentaResponse> d) { this.detalles = d; }
}
