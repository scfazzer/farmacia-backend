package com.farmaciasalud.service;
import java.util.List;
import com.farmaciasalud.dto.request.UsuarioRequest;
import com.farmaciasalud.dto.response.UsuarioResponse;

public interface IUsuarioService {
    List<UsuarioResponse> listar();
    UsuarioResponse obtenerPorId(Long id);
    UsuarioResponse crear(UsuarioRequest request);
    UsuarioResponse actualizar(Long id, UsuarioRequest request);
    UsuarioResponse cambiarEstado(Long id, Boolean activo);
    void eliminar(Long id);
}
