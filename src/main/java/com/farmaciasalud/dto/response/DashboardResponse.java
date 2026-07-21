package com.farmaciasalud.dto.response;

public class DashboardResponse {
    private Long totalMedicamentos;
    private Long medicamentosStockBajo;
    private Long medicamentosProximosAVencer;
    private Long totalClientes;
    private Long ventasHoy;
    private Double totalVentasHoy;
    private Long ventasMes;
    private Double totalVentasMes;

    public DashboardResponse() {}
    public Long getTotalMedicamentos() { return totalMedicamentos; } public void setTotalMedicamentos(Long v) { this.totalMedicamentos = v; }
    public Long getMedicamentosStockBajo() { return medicamentosStockBajo; } public void setMedicamentosStockBajo(Long v) { this.medicamentosStockBajo = v; }
    public Long getMedicamentosProximosAVencer() { return medicamentosProximosAVencer; } public void setMedicamentosProximosAVencer(Long v) { this.medicamentosProximosAVencer = v; }
    public Long getTotalClientes() { return totalClientes; } public void setTotalClientes(Long v) { this.totalClientes = v; }
    public Long getVentasHoy() { return ventasHoy; } public void setVentasHoy(Long v) { this.ventasHoy = v; }
    public Double getTotalVentasHoy() { return totalVentasHoy; } public void setTotalVentasHoy(Double v) { this.totalVentasHoy = v; }
    public Long getVentasMes() { return ventasMes; } public void setVentasMes(Long v) { this.ventasMes = v; }
    public Double getTotalVentasMes() { return totalVentasMes; } public void setTotalVentasMes(Double v) { this.totalVentasMes = v; }
}
