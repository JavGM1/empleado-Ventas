package com.digipymes360.empleado_Ventas.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digipymes360.empleado_Ventas.model.DetalleVenta;
import com.digipymes360.empleado_Ventas.model.Factura;
import com.digipymes360.empleado_Ventas.model.Venta;
import com.digipymes360.empleado_Ventas.repository.DetalleVentaRepository;
import com.digipymes360.empleado_Ventas.repository.ProductoRepository;
import com.digipymes360.empleado_Ventas.repository.VentaRepository;
import com.digipymes360.empleado_Ventas.repository.FacturaRepository;



@Service
@Transactional

public class VentaService {
    
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;

    public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        Venta v = ventaRepository.save(venta);
        for (DetalleVenta d : detalles) {
            d.setVenta(v);
            detalleVentaRepository.save(d);
            productoRepository.findById(d.getIdProducto()).ifPresent(p -> {
            p.setStock(p.getStock() - d.getCantidad());
            productoRepository.save(p);
        });
        }
        Factura factura = new Factura();
        factura.setVenta(v);
        factura.setFechaEmision(LocalDateTime.now()); 
        facturaRepository.save(factura);
        v.setFactura(factura);
        v.setDetalles(detalles);
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
