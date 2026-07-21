package com.farmaciasalud.controller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.response.DashboardResponse;
import com.farmaciasalud.repository.*;
import com.farmaciasalud.service.IMedicamentoService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired private MedicamentoRepository medRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private VentaRepository ventaRepo;
    @Autowired private IMedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<DashboardResponse> resumen() {
        DashboardResponse dash = new DashboardResponse();

        LocalDateTime inicioHoy = LocalDate.now().atStartOfDay();
        LocalDateTime finHoy    = LocalDate.now().atTime(23, 59, 59);
        LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();

        dash.setTotalMedicamentos(medRepo.countByActivoTrue());
        dash.setMedicamentosStockBajo((long) medRepo.findStockBajo().size());
        dash.setMedicamentosProximosAVencer(
            (long) medRepo.findProximosAVencer(LocalDate.now(), LocalDate.now().plusDays(30)).size());
        dash.setTotalClientes(clienteRepo.count());
        dash.setVentasHoy(ventaRepo.contarVentasCompletadas(inicioHoy, finHoy));
        dash.setTotalVentasHoy(ventaRepo.sumaTotalVentasCompletadas(inicioHoy, finHoy));
        dash.setVentasMes(ventaRepo.contarVentasCompletadas(inicioMes, finHoy));
        dash.setTotalVentasMes(ventaRepo.sumaTotalVentasCompletadas(inicioMes, finHoy));

        return ResponseEntity.ok(dash);
    }
}
