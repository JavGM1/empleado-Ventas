package com.digipymes360.empleado_Ventas.controller;

import com.digipymes360.empleado_Ventas.DTO.DetalleVentaDTO;
import com.digipymes360.empleado_Ventas.DTO.VentaConDetallesDTO;
import com.digipymes360.empleado_Ventas.DTO.VentaRequestDTO;
import com.digipymes360.empleado_Ventas.assembler.VentaModelAssembler;
import com.digipymes360.empleado_Ventas.model.DetalleVenta;
import com.digipymes360.empleado_Ventas.model.Venta;
import com.digipymes360.empleado_Ventas.service.VentaService;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class VentaControllerTest {

    private final VentaService ventaService = mock(VentaService.class);
    private final VentaModelAssembler assembler = mock(VentaModelAssembler.class);
    private final VentaController controller = new VentaController(ventaService, assembler);

    @Test
    void crearVenta_deberiaRetornarVentaRegistrada() {
        VentaRequestDTO request = new VentaRequestDTO();
        request.setIdEmpleado(1L);
        request.setIdCliente(2L);
        request.setTotalVenta(100.0);
        request.setMetodoPago("Efectivo");
        request.setEstado("COMPLETADA");
        DetalleVentaDTO detalleDTO = new DetalleVentaDTO();
        detalleDTO.setIdProducto(10L);
        detalleDTO.setCantidad(2);
        request.setDetalles(List.of(detalleDTO));

        Venta ventaRegistrada = new Venta();
        when(ventaService.registrarVenta(any(Venta.class), anyList())).thenReturn(ventaRegistrada);

        ResponseEntity<Venta> response = controller.crearVenta(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(ventaRegistrada, response.getBody());
        verify(ventaService).registrarVenta(any(Venta.class), anyList());
    }

    @Test
    void listarVentas_deberiaRetornarLista() {
        Venta venta = new Venta();
        EntityModel<Venta> entityModel = EntityModel.of(venta);
        when(ventaService.obtenerPorId(1L)).thenReturn(Optional.of(venta));
        when(assembler.toModel(venta)).thenReturn(entityModel);

        ResponseEntity<EntityModel<Venta>> response = controller.obtenerVenta(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(entityModel, response.getBody());
    }


    @Test
    void eliminarVenta_deberiaRetornarMensajeExito() {
        doNothing().when(ventaService).eliminarVenta(1L);

        ResponseEntity<String> response = controller.eliminarVenta(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Venta eliminada exitosamente", response.getBody());
        verify(ventaService).eliminarVenta(1L);
    }

    @Test
    void obtenerVenta_existente_deberiaRetornarOk() {
        Venta venta = new Venta();
        EntityModel<Venta> entityModel = EntityModel.of(venta);
        when(ventaService.obtenerPorId(1L)).thenReturn(Optional.of(venta));
        when(assembler.toModel(venta)).thenReturn(entityModel);

        ResponseEntity<EntityModel<Venta>> response = controller.obtenerVenta(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(entityModel, response.getBody());
    }

    @Test
    void obtenerVenta_noExistente_deberiaRetornarNotFound() {
        when(ventaService.obtenerPorId(2L)).thenReturn(Optional.empty());

        ResponseEntity<EntityModel<Venta>> response = controller.obtenerVenta(2L);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void obtenerVentaConDetalles_existente_deberiaRetornarOk() {
        VentaConDetallesDTO dto = new VentaConDetallesDTO();
        when(ventaService.obtenerVentaConDetalles(1L)).thenReturn(Optional.of(dto));

        ResponseEntity<VentaConDetallesDTO> response = controller.obtenerVentaConDetalles(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }

    @Test
    void obtenerVentaConDetalles_noExistente_deberiaRetornarNotFound() {
        when(ventaService.obtenerVentaConDetalles(2L)).thenReturn(Optional.empty());

        ResponseEntity<VentaConDetallesDTO> response = controller.obtenerVentaConDetalles(2L);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}