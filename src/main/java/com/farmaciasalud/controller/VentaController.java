package com.farmaciasalud.controller;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.request.VentaRequest;
import com.farmaciasalud.dto.response.VentaResponse;
import com.farmaciasalud.service.IVentaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired private IVentaService service;

    @GetMapping
    public List<VentaResponse> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<VentaResponse> porUsuario(@PathVariable Long usuarioId) {
        return service.porUsuario(usuarioId);
    }

    @GetMapping("/reporte")
    public ResponseEntity<Map<String, Object>> reporte(
            @RequestParam String desde, @RequestParam String hasta) {
        return ResponseEntity.ok(service.reporte(
                LocalDateTime.parse(desde), LocalDateTime.parse(hasta)));
    }

    @PostMapping
    public ResponseEntity<VentaResponse> registrar(@Valid @RequestBody VentaRequest req,
            Authentication auth) {
        return new ResponseEntity<>(service.registrar(req, auth.getName()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/anular")
    public ResponseEntity<VentaResponse> anular(@PathVariable Long id) {
        return ResponseEntity.ok(service.anular(id));
    }
}
