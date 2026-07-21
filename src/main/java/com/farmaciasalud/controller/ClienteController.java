package com.farmaciasalud.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.request.ClienteRequest;
import com.farmaciasalud.dto.response.ClienteResponse;
import com.farmaciasalud.dto.response.VentaResponse;
import com.farmaciasalud.service.IClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired private IClienteService service;

    @GetMapping
    public List<ClienteResponse> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ClienteResponse> obtenerPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(service.obtenerPorDni(dni));
    }

    @GetMapping("/buscar")
    public List<ClienteResponse> buscar(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @GetMapping("/{id}/historial")
    public List<VentaResponse> historialCompras(@PathVariable Long id) {
        return service.historialCompras(id);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest req) {
        return new ResponseEntity<>(service.crear(req), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(@PathVariable Long id,
            @Valid @RequestBody ClienteRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ClienteResponse> cambiarEstado(@PathVariable Long id,
            @RequestParam Boolean activo) {
        return ResponseEntity.ok(service.cambiarEstado(id, activo));
    }
}
