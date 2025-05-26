package com.digipymes360.empleado_Ventas.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        List<DetalleVenta> detalles = ventaRequest.getDetalles().stream().map(dto -> {
            DetalleVenta d = new DetalleVenta();
            d.setIdProducto(dto.getIdProducto());
            d.setCantidad(dto.getCantidad());
            // Si tienes más campos en DetalleVentaDTO, agrégalos aquí
            return d;
        }).collect(Collectors.toList());

        Venta v = ventaService.registrarVenta(venta, detalles);
        return ResponseEntity.ok(v);
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

}
