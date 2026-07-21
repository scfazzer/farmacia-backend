package com.farmaciasalud.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import com.farmaciasalud.dto.request.VentaRequest;
import com.farmaciasalud.dto.response.VentaResponse;

public interface IVentaService {
    List<VentaResponse> listar();
    VentaResponse obtenerPorId(Long id);
    List<VentaResponse> porUsuario(Long usuarioId);
    List<VentaResponse> porRangoFechas(LocalDateTime desde, LocalDateTime hasta);
    Map<String, Object> reporte(LocalDateTime desde, LocalDateTime hasta);
    VentaResponse registrar(VentaRequest request, String usernameVendedor);
    VentaResponse anular(Long id);
}
