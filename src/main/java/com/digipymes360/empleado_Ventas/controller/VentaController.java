package com.digipymes360.empleado_Ventas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digipymes360.empleado_Ventas.model.DetalleVenta;
import com.digipymes360.empleado_Ventas.model.Venta;
import com.digipymes360.empleado_Ventas.repository.DetalleVentaRepository;
import com.digipymes360.empleado_Ventas.repository.ProductoRepository;
import com.digipymes360.empleado_Ventas.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class VentaController {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;

    public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        Venta v = ventaRepository.save(venta);
        for (DetalleVenta d : detalles) {
            d.setIdVenta(v.getIdVenta());
            detalleVentaRepository.save(d);
            productoRepository.findById(d.getIdProducto()).ifPresent(p -> {
                p.setStock(p.getStock() - d.getCantidad());
                productoRepository.save(p);
            });
        }
        return v;
    }

    public Optional<Venta> obtenerPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

}
