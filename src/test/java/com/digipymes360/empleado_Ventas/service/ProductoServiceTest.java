package com.digipymes360.empleado_Ventas.service;

import com.digipymes360.empleado_Ventas.model.Producto;
import com.digipymes360.empleado_Ventas.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarProductos_deberiaRetornarListaDeProductos() {
        Producto producto = new Producto();
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> resultado = productoService.listarProductos();

        assertEquals(1, resultado.size());
        verify(productoRepository).findAll();
    }

    @Test
    void obtenerProducto_existente_deberiaRetornarProducto() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.obtenerProducto(1L);

        assertTrue(resultado.isPresent());
        assertEquals(producto, resultado.get());
        verify(productoRepository).findById(1L);
    }

    @Test
    void obtenerProducto_noExistente_deberiaRetornarVacio() {
        when(productoRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Producto> resultado = productoService.obtenerProducto(2L);

        assertFalse(resultado.isPresent());
        verify(productoRepository).findById(2L);
    }
}