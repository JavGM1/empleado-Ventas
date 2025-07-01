package com.digipymes360.empleado_Ventas.controller;

import com.digipymes360.empleado_Ventas.model.Factura;
import com.digipymes360.empleado_Ventas.service.FacturaService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacturaControllerTest {

    private final FacturaService facturaService = mock(FacturaService.class);
    private final FacturaController controller = new FacturaController(facturaService);

    @Test
    void emitir_deberiaRetornarFacturaEmitida() {
        Factura factura = new Factura();
        when(facturaService.emitir(factura)).thenReturn(factura);

        ResponseEntity<Factura> response = controller.emitir(factura);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(factura, response.getBody());
        verify(facturaService).emitir(factura);
    }

    @Test
    void listar_deberiaRetornarLista() {
        Factura factura = new Factura();
        when(facturaService.listar()).thenReturn(List.of(factura));

        List<Factura> resultado = controller.listar();

        assertEquals(1, resultado.size());
        verify(facturaService).listar();
    }

    @Test
    void obtener_existente_deberiaRetornarOk() {
        Factura factura = new Factura();
        when(facturaService.obtener(1L)).thenReturn(Optional.of(factura));

        ResponseEntity<Factura> response = controller.obtener(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(factura, response.getBody());
    }

    @Test
    void obtener_noExistente_deberiaRetornarNotFound() {
        when(facturaService.obtener(2L)).thenReturn(Optional.empty());

        ResponseEntity<Factura> response = controller.obtener(2L);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}