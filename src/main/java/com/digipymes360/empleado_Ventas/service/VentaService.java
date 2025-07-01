package com.digipymes360.empleado_Ventas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digipymes360.empleado_Ventas.DTO.DetalleVentaConProductoDTO;
import com.digipymes360.empleado_Ventas.DTO.VentaConDetallesDTO;
import com.digipymes360.empleado_Ventas.model.DetalleVenta;
import com.digipymes360.empleado_Ventas.model.Factura;
import com.digipymes360.empleado_Ventas.model.Producto;
import com.digipymes360.empleado_Ventas.model.Venta;
import com.digipymes360.empleado_Ventas.repository.DetalleVentaRepository;
import com.digipymes360.empleado_Ventas.repository.FacturaRepository;
import com.digipymes360.empleado_Ventas.repository.ProductoRepository;
import com.digipymes360.empleado_Ventas.repository.VentaRepository;

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

    // 🧾 REGISTRAR VENTA con cálculo total y actualización de stock
    public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        Venta v = ventaRepository.save(venta);
        final double[] totalVentaCalculado = {0.0};

        for (DetalleVenta d : detalles) {
            d.setVenta(v);
            detalleVentaRepository.save(d);

            // Buscar producto y actualizar stock + total
            productoRepository.findById(d.getIdProducto()).ifPresent(p -> {
                double subtotal = p.getPrecio() * d.getCantidad();
                totalVentaCalculado[0] += subtotal;

                p.setStock(p.getStock() - d.getCantidad());
                productoRepository.save(p);
            });
        }

        v.setTotalVenta(totalVentaCalculado[0]);

        // Crear y guardar factura
        Factura factura = new Factura();
        factura.setVenta(v);
        factura.setFechaEmision(LocalDateTime.now());
        facturaRepository.save(factura);

        v.setFactura(factura);
        v.setDetalles(detalles);

        return ventaRepository.save(v); // Guardamos con el total actualizado
    }

    // 🔎 OBTENER VENTA SIMPLE
    public Optional<Venta> obtenerPorId(Long id) {
        return ventaRepository.findById(id);
    }

    // 📋 LISTAR TODAS LAS VENTAS
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    // ❌ ELIMINAR VENTA
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    // 📦 OBTENER DETALLES DE VENTA CON DATOS DE PRODUCTO
    public Optional<VentaConDetallesDTO> obtenerVentaConDetalles(Long id) {
        Optional<Venta> ventaOpt = ventaRepository.findById(id);
        if (ventaOpt.isEmpty()) return Optional.empty();

        Venta venta = ventaOpt.get();

        List<DetalleVentaConProductoDTO> detallesDTO = venta.getDetalles().stream()
            .map(detalle -> {
                Producto producto = productoRepository.findById(detalle.getIdProducto()).orElse(null);
                if (producto == null) return null;

                DetalleVentaConProductoDTO dto = new DetalleVentaConProductoDTO();
                dto.setIdProducto(producto.getIdProducto());
                dto.setNombre(producto.getNombre());
                dto.setDescripcion(producto.getDescripcion());
                dto.setPrecio(producto.getPrecio());
                dto.setCantidad(detalle.getCantidad());
                return dto;
            })
            .filter(d -> d != null)
            .toList();

        VentaConDetallesDTO response = new VentaConDetallesDTO();
        response.setIdVenta(venta.getIdVenta());
        response.setFechaVenta(venta.getFechaVenta());
        response.setTotalVenta(venta.getTotalVenta());
        response.setMetodoPago(venta.getMetodoPago());
        response.setEstado(venta.getEstado());
        response.setDetalles(detallesDTO);

        return Optional.of(response);
    }
}