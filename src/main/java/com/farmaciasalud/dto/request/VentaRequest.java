package com.farmaciasalud.dto.request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class VentaRequest {
    // cliente_id puede ser null si es cliente ocasional
    private Long clienteId;
    private String observacion;

    @NotEmpty(message = "La venta debe tener al menos un producto")
    @Valid
    private List<DetalleRequest> detalles;

    public static class DetalleRequest {
        @NotNull(message = "El id del medicamento es obligatorio")
        private Long medicamentoId;
        @NotNull @Min(value = 1, message = "La cantidad debe ser al menos 1")
        private Integer cantidad;

        public Long getMedicamentoId() { return medicamentoId; } public void setMedicamentoId(Long id) { this.medicamentoId = id; }
        public Integer getCantidad() { return cantidad; } public void setCantidad(Integer c) { this.cantidad = c; }
    }

    public VentaRequest() {}
    public Long getClienteId() { return clienteId; } public void setClienteId(Long c) { this.clienteId = c; }
    public String getObservacion() { return observacion; } public void setObservacion(String o) { this.observacion = o; }
    public List<DetalleRequest> getDetalles() { return detalles; } public void setDetalles(List<DetalleRequest> d) { this.detalles = d; }
}
