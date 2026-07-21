package com.farmaciasalud.service.impl;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.farmaciasalud.dto.request.VentaRequest;
import com.farmaciasalud.dto.response.VentaResponse;
import com.farmaciasalud.exception.*;
import com.farmaciasalud.model.*;
import com.farmaciasalud.repository.*;
import com.farmaciasalud.service.IMedicamentoService;
import com.farmaciasalud.service.IVentaService;
import com.farmaciasalud.service.Mapper;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired private VentaRepository ventaRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private MedicamentoRepository medRepo;
    @Autowired private IMedicamentoService medicamentoService;
    @Autowired private Mapper mapper;

    @Override
    public List<VentaResponse> listar() {
        return ventaRepo.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public VentaResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    @Override
    public List<VentaResponse> porUsuario(Long usuarioId) {
        return ventaRepo.findByUsuarioIdOrderByFechaVentaDesc(usuarioId)
                        .stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<VentaResponse> porRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        return ventaRepo.findByFechaVentaBetweenOrderByFechaVentaDesc(desde, hasta)
                        .stream().map(mapper::toResponse).toList();
    }

    @Override
    public Map<String, Object> reporte(LocalDateTime desde, LocalDateTime hasta) {
        List<VentaResponse> ventas = porRangoFechas(desde, hasta);
        Double total = ventaRepo.sumaTotalVentasCompletadas(desde, hasta);
        Long cantidad = ventaRepo.contarVentasCompletadas(desde, hasta);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("desde", desde.toString());
        result.put("hasta", hasta.toString());
        result.put("cantidadVentas", cantidad);
        result.put("totalVentas", total != null ? total : 0.0);
        result.put("ventas", ventas);
        return result;
    }

    @Override
    @Transactional
    public VentaResponse registrar(VentaRequest request, String usernameVendedor) {
        Usuario vendedor = usuarioRepo.findByUsername(usernameVendedor)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario vendedor no encontrado"));

        Venta venta = new Venta();
        venta.setUsuario(vendedor);
        venta.setObservacion(request.getObservacion());

        // Asociar cliente si viene clienteId
        if (request.getClienteId() != null) {
            Cliente cliente = clienteRepo.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + request.getClienteId()));
            if (!cliente.getActivo())
                throw new BusinessException("El cliente indicado está inactivo");
            venta.setCliente(cliente);
        }

        double total = 0.0;
        for (VentaRequest.DetalleRequest dr : request.getDetalles()) {
            Medicamento med = medRepo.findById(dr.getMedicamentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento no encontrado con id: " + dr.getMedicamentoId()));
            if (!med.getActivo())
                throw new BusinessException("El medicamento '" + med.getNombre() + "' no está activo");

            medicamentoService.reducirStock(med.getId(), dr.getCantidad());

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setMedicamento(med);
            detalle.setCantidad(dr.getCantidad());
            detalle.setPrecioUnitario(med.getPrecioVenta());
            detalle.setSubtotal(Math.round(med.getPrecioVenta() * dr.getCantidad() * 100.0) / 100.0);
            venta.getDetalles().add(detalle);
            total += detalle.getSubtotal();
        }

        venta.setTotalVenta(Math.round(total * 100.0) / 100.0);
        return mapper.toResponse(ventaRepo.save(venta));
    }

    @Override
    @Transactional
    public VentaResponse anular(Long id) {
        Venta venta = buscarOFallar(id);
        if (venta.getEstado() == Venta.EstadoVenta.ANULADA)
            throw new BusinessException("La venta con id " + id + " ya está anulada");
        venta.getDetalles().forEach(d ->
            medicamentoService.devolverStock(d.getMedicamento().getId(), d.getCantidad()));
        venta.setEstado(Venta.EstadoVenta.ANULADA);
        return mapper.toResponse(ventaRepo.save(venta));
    }

    private Venta buscarOFallar(Long id) {
        return ventaRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));
    }
}
