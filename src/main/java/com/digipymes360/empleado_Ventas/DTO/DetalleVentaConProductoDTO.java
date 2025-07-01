package com.digipymes360.empleado_Ventas.DTO;

import lombok.Data;

@Data

public class DetalleVentaConProductoDTO {
    private Long idDetalle;
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer cantidad;
}
