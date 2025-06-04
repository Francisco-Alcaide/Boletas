
package com.example.boletasapp.proyecto.service.impl; 
import com.example.boletasapp.proyecto.model.Boleta;
import com.example.boletasapp.proyecto.repository.BoletaRepository;
import com.example.boletasapp.proyecto.service.BoletaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;
import java.util.Optional;

@Service
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository boletaRepository;

    @Autowired 
    public BoletaServiceImpl(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    @Override
    @Transactional 
    public Boleta crearBoleta(Boleta boleta) {
        
        return boletaRepository.save(boleta);
    }

    @Override
    @Transactional(readOnly = true) 
    public Optional<Boleta> obtenerBoletaPorId(Long id) {
        return boletaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> obtenerTodasLasBoletas() {
        return boletaRepository.findAll();
    }

    @Override
    @Transactional
    public Boleta actualizarBoleta(Long id, Boleta boletaActualizada) {
        
        return boletaRepository.findById(id).map(boletaExistente -> {
            boletaExistente.setNumeroBoleta(boletaActualizada.getNumeroBoleta());
            boletaExistente.setFechaEmision(boletaActualizada.getFechaEmision());
            boletaExistente.setNombreCliente(boletaActualizada.getNombreCliente());
            boletaExistente.setMontoTotal(boletaActualizada.getMontoTotal());
            boletaExistente.setDescripcion(boletaActualizada.getDescripcion());
            
            return boletaRepository.save(boletaExistente);
        }).orElseThrow(() -> new RuntimeException("Boleta no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public void eliminarBoleta(Long id) {
        if (boletaRepository.existsById(id)) {
            boletaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Boleta no encontrada con id: " + id); 
        }
        
    }
}