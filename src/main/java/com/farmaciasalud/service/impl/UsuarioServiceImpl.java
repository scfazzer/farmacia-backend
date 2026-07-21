package com.farmaciasalud.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.farmaciasalud.dto.request.UsuarioRequest;
import com.farmaciasalud.dto.response.UsuarioResponse;
import com.farmaciasalud.exception.*;
import com.farmaciasalud.model.Usuario;
import com.farmaciasalud.repository.UsuarioRepository;
import com.farmaciasalud.service.IUsuarioService;
import com.farmaciasalud.service.Mapper;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired private UsuarioRepository repo;
    @Autowired private PasswordEncoder passEncoder;
    @Autowired private Mapper mapper;

    @Override public List<UsuarioResponse> listar() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }
    @Override public UsuarioResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }
    @Override public UsuarioResponse crear(UsuarioRequest req) {
        if (repo.existsByUsername(req.getUsername()))
            throw new BusinessException("El username '" + req.getUsername() + "' ya está en uso");
        if (req.getPassword() == null || req.getPassword().isBlank())
            throw new BusinessException("La contraseña es obligatoria al crear un usuario");
        Usuario u = new Usuario();
        u.setUsername(req.getUsername());
        u.setPassword(passEncoder.encode(req.getPassword()));
        u.setNombreCompleto(req.getNombreCompleto());
        u.setRol(req.getRol());
        return mapper.toResponse(repo.save(u));
    }
    @Override public UsuarioResponse actualizar(Long id, UsuarioRequest req) {
        Usuario existe = buscarOFallar(id);
        if (!existe.getUsername().equals(req.getUsername()) && repo.existsByUsername(req.getUsername()))
            throw new BusinessException("El username '" + req.getUsername() + "' ya está en uso");
        existe.setUsername(req.getUsername());
        existe.setNombreCompleto(req.getNombreCompleto());
        existe.setRol(req.getRol());
        if (req.getPassword() != null && !req.getPassword().isBlank())
            existe.setPassword(passEncoder.encode(req.getPassword()));
        return mapper.toResponse(repo.save(existe));
    }
    @Override public UsuarioResponse cambiarEstado(Long id, Boolean activo) {
        Usuario u = buscarOFallar(id);
        u.setActivo(activo);
        return mapper.toResponse(repo.save(u));
    }
    @Override public void eliminar(Long id) { repo.delete(buscarOFallar(id)); }

    private Usuario buscarOFallar(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }
}
