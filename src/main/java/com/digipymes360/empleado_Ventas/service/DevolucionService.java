package com.digipymes360.empleado_Ventas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digipymes360.empleado_Ventas.model.Devolucion;
import com.digipymes360.empleado_Ventas.repository.DevolucionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class DevolucionService {

    private final DevolucionRepository devolucionRepository;

    public Devolucion registrar(Devolucion d) {
        return devolucionRepository.save(d);
    }

    public List<Devolucion> listar() {
        return devolucionRepository.findAll();
    }

    public void eliminar(Long id) {
        devolucionRepository.deleteById(id);
    }

}
