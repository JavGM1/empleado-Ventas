package com.digipymes360.empleado_Ventas.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digipymes360.empleado_Ventas.DTO.DetalleVentaDTO;
import com.digipymes360.empleado_Ventas.DTO.VentaConDetallesDTO;
import com.digipymes360.empleado_Ventas.DTO.VentaRequestDTO;
import com.digipymes360.empleado_Ventas.model.DetalleVenta;
import com.digipymes360.empleado_Ventas.model.Venta;
import com.digipymes360.empleado_Ventas.service.VentaService;

import lombok.*;


@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor

public class VentaController {

private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody VentaRequestDTO ventaRequest) {
    Venta venta = new Venta();
        venta.setIdEmpleado(ventaRequest.getIdEmpleado());
        venta.setIdCliente(ventaRequest.getIdCliente());
        venta.setTotalVenta(ventaRequest.getTotalVenta());
        venta.setMetodoPago(ventaRequest.getMetodoPago());
        venta.setEstado(ventaRequest.getEstado());
        venta.setFechaVenta(LocalDateTime.now());

        List<DetalleVenta> detalles = new ArrayList<>();
        if (ventaRequest.getDetalles() != null) {
            for (DetalleVentaDTO d : ventaRequest.getDetalles()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdProducto(d.getIdProducto());
                detalle.setCantidad(d.getCantidad());
                detalles.add(detalle);
            }
        }

        Venta ventaRegistrada = ventaService.registrarVenta(venta, detalles);
        return ResponseEntity.ok(ventaRegistrada);
    }

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.ok("Venta eliminada exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVenta(@PathVariable Long id) {
    return ventaService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    }
    @GetMapping("/detalles/{id}")
    public ResponseEntity<VentaConDetallesDTO> obtenerVentaConDetalles(@PathVariable Long id) {
    return ventaService.obtenerVentaConDetalles(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

}
