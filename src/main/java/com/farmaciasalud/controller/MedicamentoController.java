package com.farmaciasalud.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.request.MedicamentoRequest;
import com.farmaciasalud.dto.response.*;
import com.farmaciasalud.service.IMedicamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired private IMedicamentoService service;

    @GetMapping
    public ResponseEntity<PageResponse<MedicamentoResponse>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        return ResponseEntity.ok(service.listar(pagina, tamanio));
    }

    @GetMapping("/buscar")
    public ResponseEntity<PageResponse<MedicamentoResponse>> buscar(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        return ResponseEntity.ok(service.buscar(q, pagina, tamanio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<MedicamentoResponse> porCategoria(@PathVariable Long categoriaId) {
        return service.porCategoria(categoriaId);
    }

    @GetMapping("/proveedor/{proveedorId}")
    public List<MedicamentoResponse> porProveedor(@PathVariable Long proveedorId) {
        return service.porProveedor(proveedorId);
    }

    @GetMapping("/stock-bajo")
    public List<MedicamentoResponse> stockBajo() { return service.stockBajo(); }

    @GetMapping("/por-vencer")
    public List<MedicamentoResponse> porVencer(@RequestParam(defaultValue = "30") int dias) {
        return service.proximosAVencer(dias);
    }

    @GetMapping("/receta")
    public List<MedicamentoResponse> porReceta(@RequestParam Boolean requiere) {
        return service.porReceta(requiere);
    }

    @PostMapping
    public ResponseEntity<MedicamentoResponse> crear(@Valid @RequestBody MedicamentoRequest req) {
        return new ResponseEntity<>(service.crear(req), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> actualizar(@PathVariable Long id,
            @Valid @RequestBody MedicamentoRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> actualizarParcial(@PathVariable Long id,
            @RequestBody MedicamentoRequest req) {
        return ResponseEntity.ok(service.actualizarParcial(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> desactivar(@PathVariable Long id) {
        service.desactivar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Medicamento desactivado correctamente"));
    }
}
