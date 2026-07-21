package com.farmaciasalud.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.request.UsuarioRequest;
import com.farmaciasalud.dto.response.UsuarioResponse;
import com.farmaciasalud.service.IUsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired private IUsuarioService service;

    @GetMapping
    public List<UsuarioResponse> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody UsuarioRequest req) {
        return new ResponseEntity<>(service.crear(req), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Long id,
            @Valid @RequestBody UsuarioRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<UsuarioResponse> cambiarEstado(@PathVariable Long id,
            @RequestParam Boolean activo) {
        return ResponseEntity.ok(service.cambiarEstado(id, activo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado correctamente"));
    }
}
