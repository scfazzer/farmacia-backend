package com.farmaciasalud.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmaciasalud.dto.request.ClienteRequest;
import com.farmaciasalud.dto.response.ClienteResponse;
import com.farmaciasalud.dto.response.VentaResponse;
import com.farmaciasalud.exception.BusinessException;
import com.farmaciasalud.exception.ResourceNotFoundException;
import com.farmaciasalud.model.Cliente;
import com.farmaciasalud.repository.ClienteRepository;
import com.farmaciasalud.repository.VentaRepository;
import com.farmaciasalud.service.IClienteService;
import com.farmaciasalud.service.Mapper;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired private ClienteRepository repo;
    @Autowired private VentaRepository ventaRepo;
    @Autowired private Mapper mapper;

    @Override public List<ClienteResponse> listar() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }
    @Override public ClienteResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }
    @Override public ClienteResponse obtenerPorDni(String dni) {
        return mapper.toResponse(repo.findByDni(dni)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con DNI: " + dni)));
    }
    @Override public List<ClienteResponse> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre).stream().map(mapper::toResponse).toList();
    }
    @Override public ClienteResponse crear(ClienteRequest req) {
        if (repo.existsByDni(req.getDni()))
            throw new BusinessException("Ya existe un cliente registrado con DNI: " + req.getDni());
        Cliente c = new Cliente();
        mapearDatos(c, req);
        return mapper.toResponse(repo.save(c));
    }
    @Override public ClienteResponse actualizar(Long id, ClienteRequest req) {
        Cliente existe = buscarOFallar(id);
        if (!existe.getDni().equals(req.getDni()) && repo.existsByDni(req.getDni()))
            throw new BusinessException("Ya existe un cliente con DNI: " + req.getDni());
        mapearDatos(existe, req);
        return mapper.toResponse(repo.save(existe));
    }
    @Override public ClienteResponse cambiarEstado(Long id, Boolean activo) {
        Cliente c = buscarOFallar(id);
        c.setActivo(activo);
        return mapper.toResponse(repo.save(c));
    }
    @Override public List<VentaResponse> historialCompras(Long id) {
        buscarOFallar(id); // valida que existe
        return ventaRepo.findByClienteIdOrderByFechaVentaDesc(id)
                        .stream().map(mapper::toResponse).toList();
    }

    private void mapearDatos(Cliente c, ClienteRequest req) {
        c.setNombre(req.getNombre()); c.setDni(req.getDni());
        c.setTelefono(req.getTelefono()); c.setEmail(req.getEmail());
        c.setDireccion(req.getDireccion()); c.setFechaNacimiento(req.getFechaNacimiento());
    }
    private Cliente buscarOFallar(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }
}
