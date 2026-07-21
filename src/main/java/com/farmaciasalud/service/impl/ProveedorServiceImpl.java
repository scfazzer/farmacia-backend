package com.farmaciasalud.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmaciasalud.dto.request.ProveedorRequest;
import com.farmaciasalud.dto.response.ProveedorResponse;
import com.farmaciasalud.exception.BusinessException;
import com.farmaciasalud.exception.ResourceNotFoundException;
import com.farmaciasalud.model.Proveedor;
import com.farmaciasalud.repository.ProveedorRepository;
import com.farmaciasalud.service.IProveedorService;
import com.farmaciasalud.service.Mapper;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired private ProveedorRepository repo;
    @Autowired private Mapper mapper;

    @Override public List<ProveedorResponse> listar() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }
    @Override public List<ProveedorResponse> listarActivos() {
        return repo.findByActivoTrue().stream().map(mapper::toResponse).toList();
    }
    @Override public ProveedorResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }
    @Override public List<ProveedorResponse> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre).stream().map(mapper::toResponse).toList();
    }
    @Override public ProveedorResponse crear(ProveedorRequest req) {
        if (repo.existsByRuc(req.getRuc()))
            throw new BusinessException("Ya existe un proveedor con RUC: " + req.getRuc());
        Proveedor p = new Proveedor();
        mapearDatos(p, req);
        return mapper.toResponse(repo.save(p));
    }
    @Override public ProveedorResponse actualizar(Long id, ProveedorRequest req) {
        Proveedor existe = buscarOFallar(id);
        if (!existe.getRuc().equals(req.getRuc()) && repo.existsByRuc(req.getRuc()))
            throw new BusinessException("Ya existe un proveedor con RUC: " + req.getRuc());
        mapearDatos(existe, req);
        return mapper.toResponse(repo.save(existe));
    }
    @Override public ProveedorResponse cambiarEstado(Long id, Boolean activo) {
        Proveedor p = buscarOFallar(id);
        p.setActivo(activo);
        return mapper.toResponse(repo.save(p));
    }
    @Override public void eliminar(Long id) { repo.delete(buscarOFallar(id)); }

    private void mapearDatos(Proveedor p, ProveedorRequest req) {
        p.setNombre(req.getNombre()); p.setRuc(req.getRuc());
        p.setDireccion(req.getDireccion()); p.setTelefono(req.getTelefono());
        p.setEmail(req.getEmail());
    }
    private Proveedor buscarOFallar(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + id));
    }
}
