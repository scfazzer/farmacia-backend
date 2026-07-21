package com.farmaciasalud.service.impl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.farmaciasalud.dto.request.MedicamentoRequest;
import com.farmaciasalud.dto.response.*;
import com.farmaciasalud.exception.*;
import com.farmaciasalud.model.*;
import com.farmaciasalud.repository.*;
import com.farmaciasalud.service.IMedicamentoService;
import com.farmaciasalud.service.Mapper;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

    @Autowired private MedicamentoRepository repo;
    @Autowired private CategoriaRepository catRepo;
    @Autowired private ProveedorRepository provRepo;
    @Autowired private Mapper mapper;

    @Override
    public PageResponse<MedicamentoResponse> listar(int pagina, int tamanio) {
        Pageable p = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        Page<Medicamento> page = repo.findByActivoTrue(p);
        return toPage(page);
    }

    @Override
    public PageResponse<MedicamentoResponse> buscar(String q, int pagina, int tamanio) {
        Pageable p = PageRequest.of(pagina, tamanio, Sort.by("nombre"));
        Page<Medicamento> page = repo.buscarActivos(q, p);
        return toPage(page);
    }

    @Override
    public MedicamentoResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    @Override
    public List<MedicamentoResponse> porCategoria(Long categoriaId) {
        if (!catRepo.existsById(categoriaId))
            throw new ResourceNotFoundException("Categoría no encontrada con id: " + categoriaId);
        return repo.findByCategoriaIdAndActivoTrue(categoriaId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<MedicamentoResponse> porProveedor(Long proveedorId) {
        if (!provRepo.existsById(proveedorId))
            throw new ResourceNotFoundException("Proveedor no encontrado con id: " + proveedorId);
        return repo.findByProveedorIdAndActivoTrue(proveedorId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<MedicamentoResponse> stockBajo() {
        return repo.findStockBajo().stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<MedicamentoResponse> proximosAVencer(int dias) {
        return repo.findProximosAVencer(LocalDate.now(), LocalDate.now().plusDays(dias))
                   .stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<MedicamentoResponse> porReceta(Boolean requiereReceta) {
        return repo.findByRequiereRecetaAndActivoTrue(requiereReceta)
                   .stream().map(mapper::toResponse).toList();
    }

    @Override
    public MedicamentoResponse crear(MedicamentoRequest req) {
        validar(req);
        Medicamento m = new Medicamento();
        mapearDatos(m, req);
        return mapper.toResponse(repo.save(m));
    }

    @Override
    public MedicamentoResponse actualizar(Long id, MedicamentoRequest req) {
        Medicamento existe = buscarOFallar(id);
        validar(req);
        mapearDatos(existe, req);
        return mapper.toResponse(repo.save(existe));
    }

    @Override
    public MedicamentoResponse actualizarParcial(Long id, MedicamentoRequest req) {
        Medicamento existe = buscarOFallar(id);
        if (req.getNombre() != null) existe.setNombre(req.getNombre());
        if (req.getPrecioVenta() != null) {
            if (req.getPrecioVenta() <= 0) throw new BusinessException("El precio debe ser mayor a 0");
            existe.setPrecioVenta(req.getPrecioVenta());
        }
        if (req.getStock() != null) {
            if (req.getStock() < 0) throw new BusinessException("El stock no puede ser negativo");
            existe.setStock(req.getStock());
        }
        if (req.getStockMinimo() != null) existe.setStockMinimo(req.getStockMinimo());
        if (req.getFechaVencimiento() != null) existe.setFechaVencimiento(req.getFechaVencimiento());
        if (req.getCategoriaId() != null)
            existe.setCategoria(catRepo.findById(req.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada")));
        if (req.getProveedorId() != null)
            existe.setProveedor(provRepo.findById(req.getProveedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado")));
        return mapper.toResponse(repo.save(existe));
    }

    @Override
    public void desactivar(Long id) {
        Medicamento m = buscarOFallar(id);
        m.setActivo(false);
        repo.save(m);
    }

    @Override
    public void reducirStock(Long id, int cantidad) {
        Medicamento m = buscarOFallar(id);
        if (m.getStock() < cantidad)
            throw new BusinessException("Stock insuficiente para '" + m.getNombre()
                + "'. Disponible: " + m.getStock() + ", solicitado: " + cantidad);
        m.setStock(m.getStock() - cantidad);
        repo.save(m);
    }

    @Override
    public void devolverStock(Long id, int cantidad) {
        Medicamento m = buscarOFallar(id);
        m.setStock(m.getStock() + cantidad);
        repo.save(m);
    }

    private void validar(MedicamentoRequest req) {
        if (req.getPrecioVenta() != null && req.getPrecioVenta() <= 0)
            throw new BusinessException("El precio de venta debe ser mayor a 0");
        if (req.getFechaVencimiento() != null && req.getFechaVencimiento().isBefore(LocalDate.now()))
            throw new BusinessException("La fecha de vencimiento no puede ser en el pasado");
    }

    private void mapearDatos(Medicamento m, MedicamentoRequest req) {
        m.setNombre(req.getNombre()); m.setPrincipioActivo(req.getPrincipioActivo());
        m.setPresentacion(req.getPresentacion()); m.setConcentracion(req.getConcentracion());
        m.setPrecioVenta(req.getPrecioVenta()); m.setPrecioCompra(req.getPrecioCompra());
        m.setStock(req.getStock()); m.setStockMinimo(req.getStockMinimo() != null ? req.getStockMinimo() : 5);
        m.setFechaVencimiento(req.getFechaVencimiento());
        m.setRequiereReceta(req.getRequiereReceta() != null ? req.getRequiereReceta() : false);
        m.setCategoria(catRepo.findById(req.getCategoriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + req.getCategoriaId())));
        m.setProveedor(provRepo.findById(req.getProveedorId())
            .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + req.getProveedorId())));
    }

    private Medicamento buscarOFallar(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medicamento no encontrado con id: " + id));
    }

    private PageResponse<MedicamentoResponse> toPage(Page<Medicamento> page) {
        return new PageResponse<>(page.getContent().stream().map(mapper::toResponse).toList(),
                page.getNumber(), page.getTotalPages(), page.getTotalElements(), page.getSize());
    }
}
