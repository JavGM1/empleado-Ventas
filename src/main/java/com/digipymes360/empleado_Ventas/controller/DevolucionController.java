package com.digipymes360.empleado_Ventas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digipymes360.empleado_Ventas.model.Devolucion;
import com.digipymes360.empleado_Ventas.service.DevolucionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/devoluciones")
@RequiredArgsConstructor

public class DevolucionController {

   private final DevolucionService devolucionService;

    @PostMapping
    public ResponseEntity<Devolucion> registrar(@RequestBody Devolucion d) {
        return ResponseEntity.ok(devolucionService.registrar(d));
    }

    @GetMapping
    public List<Devolucion> listar() {
        return devolucionService.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        devolucionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
