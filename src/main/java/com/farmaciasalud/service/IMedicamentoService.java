package com.farmaciasalud.service;
import java.util.List;
import com.farmaciasalud.dto.request.MedicamentoRequest;
import com.farmaciasalud.dto.response.MedicamentoResponse;
import com.farmaciasalud.dto.response.PageResponse;

public interface IMedicamentoService {
    PageResponse<MedicamentoResponse> listar(int pagina, int tamanio);
    PageResponse<MedicamentoResponse> buscar(String q, int pagina, int tamanio);
    MedicamentoResponse obtenerPorId(Long id);
    List<MedicamentoResponse> porCategoria(Long categoriaId);
    List<MedicamentoResponse> porProveedor(Long proveedorId);
    List<MedicamentoResponse> stockBajo();
    List<MedicamentoResponse> proximosAVencer(int dias);
    List<MedicamentoResponse> porReceta(Boolean requiereReceta);
    MedicamentoResponse crear(MedicamentoRequest request);
    MedicamentoResponse actualizar(Long id, MedicamentoRequest request);
    MedicamentoResponse actualizarParcial(Long id, MedicamentoRequest request);
    void desactivar(Long id);
    // Usados internamente por VentaService
    void reducirStock(Long id, int cantidad);
    void devolverStock(Long id, int cantidad);
}
