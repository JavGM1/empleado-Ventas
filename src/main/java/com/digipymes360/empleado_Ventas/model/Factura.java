package com.digipymes360.empleado_Ventas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data

// @Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factura_seq")
    @SequenceGenerator(name = "factura_seq", sequenceName = "FACTURA_SEQ", allocationSize = 1)
    private Long idFactura;

    private LocalDateTime fechaEmision;

@OneToOne
@JoinColumn(name = "idVenta", nullable = false, unique = true)
@JsonBackReference

private Venta venta;
}
