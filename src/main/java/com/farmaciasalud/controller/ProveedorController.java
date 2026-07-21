package com.farmaciasalud.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.request.ProveedorRequest;
import com.farmaciasalud.dto.response.ProveedorResponse;
import com.farmaciasalud.service.IProveedorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired private IProveedorService service;

    @GetMapping
    public List<ProveedorResponse> listar() { return service.listar(); }

    @GetMapping("/activos")
    public List<ProveedorResponse> listarActivos() { return service.listarActivos(); }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    public List<ProveedorResponse> buscar(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<ProveedorResponse> crear(@Valid @RequestBody ProveedorRequest req) {
        return new ResponseEntity<>(service.crear(req), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponse> actualizar(@PathVariable Long id,
            @Valid @RequestBody ProveedorRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ProveedorResponse> cambiarEstado(@PathVariable Long id,
            @RequestParam Boolean activo) {
        return ResponseEntity.ok(service.cambiarEstado(id, activo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Proveedor eliminado correctamente"));
    }
}
