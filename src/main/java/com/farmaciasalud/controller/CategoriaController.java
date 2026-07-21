package com.farmaciasalud.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.request.CategoriaRequest;
import com.farmaciasalud.dto.response.CategoriaResponse;
import com.farmaciasalud.service.ICategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired private ICategoriaService service;

    @GetMapping
    public List<CategoriaResponse> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    public List<CategoriaResponse> buscar(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CategoriaRequest req) {
        return new ResponseEntity<>(service.crear(req), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizar(@PathVariable Long id,
            @Valid @RequestBody CategoriaRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Categoría eliminada correctamente"));
    }
}
