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
// @Table(name = "venta")

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    @SequenceGenerator(name = "venta_seq", sequenceName = "VENTA_SEQ", allocationSize = 1)

    private Long idVenta;

    private Long idEmpleado;
    private Long idCliente;

    private LocalDateTime fechaVenta;
    private Double totalVenta;
    private String metodoPago;
    private String estado;

}
