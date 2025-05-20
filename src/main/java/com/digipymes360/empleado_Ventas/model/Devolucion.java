package com.digipymes360.empleado_Ventas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
// @Table(name = "devolucion")

public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devolucion_seq")
    @SequenceGenerator(name = "devolucion_seq", sequenceName = "DEVOLUCION_SEQ", allocationSize = 1)
    
    private Long idDevolucion;

    private Long idVenta;
    private String motivo;
    private LocalDateTime fecha;
    private String estado;
}
