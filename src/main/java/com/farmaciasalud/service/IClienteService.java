package com.farmaciasalud.service;
import java.util.List;
import com.farmaciasalud.dto.request.ClienteRequest;
import com.farmaciasalud.dto.response.ClienteResponse;
import com.farmaciasalud.dto.response.VentaResponse;

public interface IClienteService {
    List<ClienteResponse> listar();
    ClienteResponse obtenerPorId(Long id);
    ClienteResponse obtenerPorDni(String dni);
    List<ClienteResponse> buscarPorNombre(String nombre);
    ClienteResponse crear(ClienteRequest request);
    ClienteResponse actualizar(Long id, ClienteRequest request);
    ClienteResponse cambiarEstado(Long id, Boolean activo);
    List<VentaResponse> historialCompras(Long id);
}
