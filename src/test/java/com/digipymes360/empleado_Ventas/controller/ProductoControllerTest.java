package com.digipymes360.empleado_Ventas.controller;

import com.digipymes360.empleado_Ventas.model.Producto;
import com.digipymes360.empleado_Ventas.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    private final ProductoService productoService = mock(ProductoService.class);
    private final ProductoController controller = new ProductoController(productoService);

    @Test
    void listarProductos_deberiaRetornarLista() {
        Producto producto = new Producto();
        when(productoService.listarProductos()).thenReturn(List.of(producto));

        List<Producto> resultado = controller.listarProductos();

        assertEquals(1, resultado.size());
        verify(productoService).listarProductos();
    }

    @Test
    void obtenerProducto_existente_deberiaRetornarOk() {
        Producto producto = new Producto();
        when(productoService.obtenerProducto(1L)).thenReturn(Optional.of(producto));

        ResponseEntity<Producto> response = controller.obtenerProducto(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(producto, response.getBody());
    }

    @Test
    void obtenerProducto_noExistente_deberiaRetornarNotFound() {
        when(productoService.obtenerProducto(2L)).thenReturn(Optional.empty());

        ResponseEntity<Producto> response = controller.obtenerProducto(2L);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}