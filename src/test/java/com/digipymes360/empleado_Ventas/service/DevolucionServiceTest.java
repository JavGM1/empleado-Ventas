package com.digipymes360.empleado_Ventas.service;

import com.digipymes360.empleado_Ventas.model.Devolucion;
import com.digipymes360.empleado_Ventas.repository.DevolucionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DevolucionServiceTest {

    @Mock
    private DevolucionRepository devolucionRepository;

    @InjectMocks
    private DevolucionService devolucionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrar_deberiaGuardarYRetornarDevolucion() {
        Devolucion devolucion = new Devolucion();
        when(devolucionRepository.save(devolucion)).thenReturn(devolucion);

        Devolucion resultado = devolucionService.registrar(devolucion);

        assertEquals(devolucion, resultado);
        verify(devolucionRepository).save(devolucion);
    }

    @Test
    void listar_deberiaRetornarListaDeDevoluciones() {
        Devolucion devolucion = new Devolucion();
        when(devolucionRepository.findAll()).thenReturn(List.of(devolucion));

        List<Devolucion> resultado = devolucionService.listar();

        assertEquals(1, resultado.size());
        verify(devolucionRepository).findAll();
    }

    @Test
    void eliminar_deberiaEliminarDevolucionPorId() {
        doNothing().when(devolucionRepository).deleteById(1L);

        devolucionService.eliminar(1L);

        verify(devolucionRepository).deleteById(1L);
    }
}