package com.digipymes360.empleado_Ventas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digipymes360.empleado_Ventas.model.Producto;
import com.digipymes360.empleado_Ventas.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }
    
    @Operation(summary = "Buscar producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerProducto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
