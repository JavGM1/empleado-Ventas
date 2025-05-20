package com.digipymes360.empleado_Ventas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digipymes360.empleado_Ventas.model.Producto;
import com.digipymes360.empleado_Ventas.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProducto(Long id) {
        return productoRepository.findById(id);
    }
}
