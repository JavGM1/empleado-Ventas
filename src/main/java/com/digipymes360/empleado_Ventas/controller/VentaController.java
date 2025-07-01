package com.digipymes360.empleado_Ventas.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import com.digipymes360.empleado_Ventas.DTO.DetalleVentaDTO;
import com.digipymes360.empleado_Ventas.DTO.VentaConDetallesDTO;
import com.digipymes360.empleado_Ventas.DTO.VentaRequestDTO;
import com.digipymes360.empleado_Ventas.assembler.VentaModelAssembler;
import com.digipymes360.empleado_Ventas.model.DetalleVenta;
import com.digipymes360.empleado_Ventas.model.Venta;
import com.digipymes360.empleado_Ventas.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;
    private final VentaModelAssembler assembler;

    @Operation(summary = "Registrar una nueva venta")
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

    @Operation(summary = "Listar todas las ventas")
    @GetMapping
    public CollectionModel<EntityModel<Venta>> listarVentas() {
        List<EntityModel<Venta>> ventas = ventaService.listarVentas().stream()
            .map(assembler::toModel)
            .toList();
        return CollectionModel.of(ventas,
            linkTo(methodOn(VentaController.class).listarVentas()).withSelfRel());
    }

    @Operation(summary = "Eliminar una venta por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.ok("Venta eliminada exitosamente");
    }

    @Operation(summary = "Obtener venta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Venta>> obtenerVenta(@PathVariable Long id) {
        return ventaService.obtenerPorId(id)
            .map(assembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener venta con detalles por ID")
    @GetMapping("/detalles/{id}")
    public ResponseEntity<VentaConDetallesDTO> obtenerVentaConDetalles(@PathVariable Long id) {
        return ventaService.obtenerVentaConDetalles(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
