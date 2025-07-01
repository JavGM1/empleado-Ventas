package com.digipymes360.empleado_Ventas.assembler;

import com.digipymes360.empleado_Ventas.controller.VentaController;
import com.digipymes360.empleado_Ventas.model.Venta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {
    @Override
    public EntityModel<Venta> toModel(Venta venta) {
        return EntityModel.of(venta,
            linkTo(methodOn(VentaController.class).obtenerVenta(venta.getIdVenta())).withSelfRel(),
            linkTo(methodOn(VentaController.class).listarVentas()).withRel("ventas")
        );
    }
}