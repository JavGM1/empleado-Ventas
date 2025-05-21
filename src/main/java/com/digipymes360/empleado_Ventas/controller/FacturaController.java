package com.digipymes360.empleado_Ventas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digipymes360.empleado_Ventas.model.Factura;
import com.digipymes360.empleado_Ventas.service.FacturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor

public class FacturaController {

    private final FacturaService facturaService;

    @PostMapping
    public ResponseEntity<Factura> emitir(@RequestBody Factura f) {
        return ResponseEntity.ok(facturaService.emitir(f));
    }

    @GetMapping
    public List<Factura> listar() {
        return facturaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtener(@PathVariable Long id) {
        return facturaService.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
