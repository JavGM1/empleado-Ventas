package com.digipymes360.empleado_Ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digipymes360.empleado_Ventas.model.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {
} 


