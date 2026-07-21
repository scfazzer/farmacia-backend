package com.farmaciasalud.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmaciasalud.dto.request.CategoriaRequest;
import com.farmaciasalud.dto.response.CategoriaResponse;
import com.farmaciasalud.exception.BusinessException;
import com.farmaciasalud.exception.ResourceNotFoundException;
import com.farmaciasalud.model.Categoria;
import com.farmaciasalud.repository.CategoriaRepository;
import com.farmaciasalud.service.ICategoriaService;
import com.farmaciasalud.service.Mapper;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired private CategoriaRepository repo;
    @Autowired private Mapper mapper;

    @Override
    public List<CategoriaResponse> listar() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public CategoriaResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    @Override
    public List<CategoriaResponse> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre)
                   .stream().map(mapper::toResponse).toList();
    }

    @Override
    public CategoriaResponse crear(CategoriaRequest request) {
        if (repo.existsByNombreIgnoreCase(request.getNombre()))
            throw new BusinessException("Ya existe una categoría con el nombre: " + request.getNombre());
        Categoria c = new Categoria();
        c.setNombre(request.getNombre());
        c.setDescripcion(request.getDescripcion());
        return mapper.toResponse(repo.save(c));
    }

    @Override
    public CategoriaResponse actualizar(Long id, CategoriaRequest request) {
        Categoria existe = buscarOFallar(id);
        if (!existe.getNombre().equalsIgnoreCase(request.getNombre())
                && repo.existsByNombreIgnoreCase(request.getNombre()))
            throw new BusinessException("Ya existe una categoría con el nombre: " + request.getNombre());
        existe.setNombre(request.getNombre());
        existe.setDescripcion(request.getDescripcion());
        return mapper.toResponse(repo.save(existe));
    }

    @Override
    public void eliminar(Long id) {
        repo.delete(buscarOFallar(id));
    }

    private Categoria buscarOFallar(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }
}
