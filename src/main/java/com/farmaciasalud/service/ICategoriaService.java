package com.farmaciasalud.service;
import java.util.List;
import com.farmaciasalud.dto.request.CategoriaRequest;
import com.farmaciasalud.dto.response.CategoriaResponse;

public interface ICategoriaService {
    List<CategoriaResponse> listar();
    CategoriaResponse obtenerPorId(Long id);
    List<CategoriaResponse> buscarPorNombre(String nombre);
    CategoriaResponse crear(CategoriaRequest request);
    CategoriaResponse actualizar(Long id, CategoriaRequest request);
    void eliminar(Long id);
}
