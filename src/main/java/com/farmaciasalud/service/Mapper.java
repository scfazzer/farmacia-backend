package com.farmaciasalud.service;

import com.farmaciasalud.dto.response.*;
import com.farmaciasalud.model.*;
import org.springframework.stereotype.Component;

/**
 * Mapper: convierte entidades JPA a DTOs de respuesta.
 * Centraliza la lógica de mapeo — no se repite en cada service.
 */
@Component
public class Mapper {

    public CategoriaResponse toResponse(Categoria c) {
        CategoriaResponse r = new CategoriaResponse();
        r.setId(c.getId());
        r.setNombre(c.getNombre());
        r.setDescripcion(c.getDescripcion());
        r.setCreatedAt(c.getCreatedAt());
        r.setUpdatedAt(c.getUpdatedAt());
        return r;
    }

    public ProveedorResponse toResponse(Proveedor p) {
        ProveedorResponse r = new ProveedorResponse();
        r.setId(p.getId()); r.setNombre(p.getNombre()); r.setRuc(p.getRuc());
        r.setDireccion(p.getDireccion()); r.setTelefono(p.getTelefono());
        r.setEmail(p.getEmail()); r.setActivo(p.getActivo());
        r.setCreatedAt(p.getCreatedAt()); r.setUpdatedAt(p.getUpdatedAt());
        return r;
    }

    public ClienteResponse toResponse(Cliente c) {
        ClienteResponse r = new ClienteResponse();
        r.setId(c.getId()); r.setNombre(c.getNombre()); r.setDni(c.getDni());
        r.setTelefono(c.getTelefono()); r.setEmail(c.getEmail());
        r.setDireccion(c.getDireccion()); r.setFechaNacimiento(c.getFechaNacimiento());
        r.setActivo(c.getActivo()); r.setCreatedAt(c.getCreatedAt()); r.setUpdatedAt(c.getUpdatedAt());
        return r;
    }

    public MedicamentoResponse toResponse(Medicamento m) {
        MedicamentoResponse r = new MedicamentoResponse();
        r.setId(m.getId()); r.setNombre(m.getNombre());
        r.setPrincipioActivo(m.getPrincipioActivo()); r.setPresentacion(m.getPresentacion());
        r.setConcentracion(m.getConcentracion()); r.setPrecioVenta(m.getPrecioVenta());
        r.setPrecioCompra(m.getPrecioCompra()); r.setStock(m.getStock());
        r.setStockMinimo(m.getStockMinimo());
        r.setStockBajo(m.getStock() <= m.getStockMinimo());
        r.setFechaVencimiento(m.getFechaVencimiento()); r.setRequiereReceta(m.getRequiereReceta());
        r.setActivo(m.getActivo());
        if (m.getCategoria() != null) {
            r.setCategoriaId(m.getCategoria().getId());
            r.setCategoriaNombre(m.getCategoria().getNombre());
        }
        if (m.getProveedor() != null) {
            r.setProveedorId(m.getProveedor().getId());
            r.setProveedorNombre(m.getProveedor().getNombre());
        }
        r.setCreatedAt(m.getCreatedAt()); r.setUpdatedAt(m.getUpdatedAt());
        return r;
    }

    public DetalleVentaResponse toResponse(DetalleVenta d) {
        DetalleVentaResponse r = new DetalleVentaResponse();
        r.setId(d.getId()); r.setCantidad(d.getCantidad());
        r.setPrecioUnitario(d.getPrecioUnitario()); r.setSubtotal(d.getSubtotal());
        if (d.getMedicamento() != null) {
            r.setMedicamentoId(d.getMedicamento().getId());
            r.setMedicamentoNombre(d.getMedicamento().getNombre());
            r.setMedicamentoPresentacion(d.getMedicamento().getPresentacion());
        }
        return r;
    }

    public VentaResponse toResponse(Venta v) {
        VentaResponse r = new VentaResponse();
        r.setId(v.getId()); r.setTotalVenta(v.getTotalVenta());
        r.setEstado(v.getEstado().name()); r.setObservacion(v.getObservacion());
        r.setFechaVenta(v.getFechaVenta());
        if (v.getCliente() != null) {
            r.setClienteId(v.getCliente().getId());
            r.setClienteNombre(v.getCliente().getNombre());
            r.setClienteDni(v.getCliente().getDni());
        }
        if (v.getUsuario() != null) {
            r.setUsuarioId(v.getUsuario().getId());
            r.setUsuarioNombre(v.getUsuario().getNombreCompleto());
        }
        if (v.getDetalles() != null) {
            r.setDetalles(v.getDetalles().stream().map(this::toResponse).toList());
        }
        return r;
    }

    public UsuarioResponse toResponse(Usuario u) {
        UsuarioResponse r = new UsuarioResponse();
        r.setId(u.getId()); r.setUsername(u.getUsername());
        r.setNombreCompleto(u.getNombreCompleto());
        r.setRol(u.getRol().name()); r.setActivo(u.getActivo());
        return r;
    }
}
