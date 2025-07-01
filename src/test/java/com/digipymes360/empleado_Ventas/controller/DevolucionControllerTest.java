package com.digipymes360.empleado_Ventas.controller;

import com.digipymes360.empleado_Ventas.model.Devolucion;
import com.digipymes360.empleado_Ventas.service.DevolucionService;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DevolucionControllerTest {

    private final DevolucionService devolucionService = mock(DevolucionService.class);
    private final DevolucionController controller = new DevolucionController(devolucionService);
    private final Faker faker = new Faker();

    private Devolucion crearDevolucionFaker() {
        Devolucion devolucion = new Devolucion();
        devolucion.setIdDevolucion(faker.number().randomNumber());
        devolucion.setIdVenta(faker.number().randomNumber());
        devolucion.setMotivo(faker.lorem().sentence());
        devolucion.setFecha(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));
        devolucion.setEstado(faker.options().option("PENDIENTE", "APROBADA", "RECHAZADA"));
        return devolucion;
    }

    @Test
    void registrar_deberiaRetornarDevolucionRegistrada() {
        Devolucion devolucion = crearDevolucionFaker();
        when(devolucionService.registrar(devolucion)).thenReturn(devolucion);

        ResponseEntity<Devolucion> response = controller.registrar(devolucion);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(devolucion, response.getBody());
        verify(devolucionService).registrar(devolucion);
    }

    @Test
    void listar_deberiaRetornarLista() {
        Devolucion devolucion = crearDevolucionFaker();
        when(devolucionService.listar()).thenReturn(List.of(devolucion));

        List<Devolucion> resultado = controller.listar();

        assertEquals(1, resultado.size());
        verify(devolucionService).listar();
    }

    @Test
    void eliminar_deberiaRetornarNoContent() {
        doNothing().when(devolucionService).eliminar(1L);

        ResponseEntity<Void> response = controller.eliminar(1L);

        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(devolucionService).eliminar(1L);
    }
}