package com.digipymes360.empleado_Ventas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
// @Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factura_seq")
    @SequenceGenerator(name = "factura_seq", sequenceName = "FACTURA_SEQ", allocationSize = 1)
    private Long idFactura;

    private Long idVenta;
    private LocalDateTime fechaEmision;
    private String archivoPdf;
}
