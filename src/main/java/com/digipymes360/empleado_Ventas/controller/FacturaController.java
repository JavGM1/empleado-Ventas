package com.digipymes360.empleado_Ventas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digipymes360.empleado_Ventas.model.Factura;
import com.digipymes360.empleado_Ventas.service.FacturaService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor

public class FacturaController {

    private final FacturaService facturaService;
    
    @Operation(summary = "Emitir una nueva factura")
    @PostMapping
    public ResponseEntity<Factura> emitir(@RequestBody Factura f) {
        return ResponseEntity.ok(facturaService.emitir(f));
    }

    @Operation(summary = "Listar todas las facturas")
    @GetMapping
    public List<Factura> listar() {
        return facturaService.listar();
    }

    @Operation(summary = "Buscar factura por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtener(@PathVariable Long id) {
        return facturaService.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
