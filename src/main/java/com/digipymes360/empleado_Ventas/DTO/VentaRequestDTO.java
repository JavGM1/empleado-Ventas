package com.digipymes360.empleado_Ventas.DTO;

import lombok.Data;
import java.util.List;
@Data
public class VentaRequestDTO {
    private Long idEmpleado;
    private Long idCliente;
    private Double totalVenta;
    private String metodoPago;
    private String estado;
    private List<DetalleVentaDTO> detalles;
}
