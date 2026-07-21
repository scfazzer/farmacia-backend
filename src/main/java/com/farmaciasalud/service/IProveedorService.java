package com.farmaciasalud.service;
import java.util.List;
import com.farmaciasalud.dto.request.ProveedorRequest;
import com.farmaciasalud.dto.response.ProveedorResponse;

public interface IProveedorService {
    List<ProveedorResponse> listar();
    List<ProveedorResponse> listarActivos();
    ProveedorResponse obtenerPorId(Long id);
    List<ProveedorResponse> buscarPorNombre(String nombre);
    ProveedorResponse crear(ProveedorRequest request);
    ProveedorResponse actualizar(Long id, ProveedorRequest request);
    ProveedorResponse cambiarEstado(Long id, Boolean activo);
    void eliminar(Long id);
}
