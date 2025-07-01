package com.digipymes360.empleado_Ventas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "ID_PRODUCTO", nullable = false)
    private Long idProducto;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "idVenta")
    @JsonBackReference
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", insertable = false, updatable = false)
    private Producto producto;
}
