package com.digipymes360.empleado_Ventas.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class VentaConDetallesDTO {
    private Long idVenta;
    private LocalDateTime fechaVenta;
    private Double totalVenta;
    private String metodoPago;
    private String estado;
    private List<DetalleVentaConProductoDTO> detalles;
}
