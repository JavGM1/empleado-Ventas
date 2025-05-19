package com.digipymes360.empleado_Ventas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
@Entity
@Data
// @Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_venta_seq")
    @SequenceGenerator(name = "detalle_venta_seq", sequenceName = "DETALLE_VENTA_SEQ", allocationSize = 1)

    private Long idDetalle;

    private Long idVenta;
    private Long idProducto;
    private Integer cantidad;
    private Double subtotal;
}
