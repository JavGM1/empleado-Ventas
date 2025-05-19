package com.digipymes360.empleado_Ventas.model;

import jakarta.persistence.Entity;
import lombok.Data;
import java.time.LocalDateTime;
@Entity
@Data

public class Venta {
    private Long idVenta;

    private Long idEmpleado;
    private Long idCliente;

    private LocalDateTime fechaVenta;
    private Double totalVenta;
    private String metodoPago;
    private String estado;

}
