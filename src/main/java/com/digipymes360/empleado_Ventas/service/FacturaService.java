package com.digipymes360.empleado_Ventas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digipymes360.empleado_Ventas.model.Factura;
import com.digipymes360.empleado_Ventas.repository.FacturaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class FacturaService {

    private final FacturaRepository facturaRepository;

    public Factura emitir(Factura f) {
        return facturaRepository.save(f);
    }

    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> obtener(Long id) {
        return facturaRepository.findById(id);
    }
}
