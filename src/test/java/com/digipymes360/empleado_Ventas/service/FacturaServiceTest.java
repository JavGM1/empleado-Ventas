package com.digipymes360.empleado_Ventas.service;

import com.digipymes360.empleado_Ventas.model.Factura;
import com.digipymes360.empleado_Ventas.repository.FacturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void emitir_deberiaGuardarYRetornarFactura() {
        Factura factura = new Factura();
        when(facturaRepository.save(factura)).thenReturn(factura);

        Factura resultado = facturaService.emitir(factura);

        assertEquals(factura, resultado);
        verify(facturaRepository).save(factura);
    }

    @Test
    void listar_deberiaRetornarListaDeFacturas() {
        Factura factura = new Factura();
        when(facturaRepository.findAll()).thenReturn(List.of(factura));

        List<Factura> resultado = facturaService.listar();

        assertEquals(1, resultado.size());
        verify(facturaRepository).findAll();
    }

    @Test
    void obtener_existente_deberiaRetornarFactura() {
        Factura factura = new Factura();
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        Optional<Factura> resultado = facturaService.obtener(1L);

        assertTrue(resultado.isPresent());
        assertEquals(factura, resultado.get());
        verify(facturaRepository).findById(1L);
    }

    @Test
    void obtener_noExistente_deberiaRetornarVacio() {
        when(facturaRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Factura> resultado = facturaService.obtener(2L);

        assertFalse(resultado.isPresent());
        verify(facturaRepository).findById(2L);
    }
}