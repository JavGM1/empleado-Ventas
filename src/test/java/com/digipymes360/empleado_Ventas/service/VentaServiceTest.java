package com.digipymes360.empleado_Ventas.service;


import com.digipymes360.empleado_Ventas.model.*;
import com.digipymes360.empleado_Ventas.repository.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;
    @Mock
    private DetalleVentaRepository detalleVentaRepository;
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private VentaService ventaService;

    private Faker faker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
    }

    @Test
    void registrarVenta_deberiaGuardarVentaYActualizarStock() {

        Producto producto = new Producto();
        producto.setIdProducto(faker.number().randomNumber());
        producto.setNombre(faker.commerce().productName());
        producto.setPrecio((float) faker.number().randomDouble(2, 10, 100));
        producto.setStock(50L);

        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdProducto(producto.getIdProducto());
        detalle.setCantidad(2);

        Venta venta = new Venta();
        venta.setMetodoPago(faker.commerce().promotionCode());
        venta.setEstado("OK");

        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);
        when(productoRepository.findById(producto.getIdProducto())).thenReturn(Optional.of(producto));
        when(detalleVentaRepository.save(any(DetalleVenta.class))).thenReturn(detalle);

        Venta resultado = ventaService.registrarVenta(venta, List.of(detalle));

        assertNotNull(resultado);
        verify(ventaRepository, atLeastOnce()).save(any(Venta.class));
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void listarVentas_deberiaRetornarListaDeVentas() {
        Venta venta = new Venta();
        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        List<Venta> resultado = ventaService.listarVentas();

        assertEquals(1, resultado.size());
        verify(ventaRepository).findAll();
}
}