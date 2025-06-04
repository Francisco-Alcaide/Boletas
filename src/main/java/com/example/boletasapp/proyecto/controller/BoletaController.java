package com.example.boletasapp.proyecto.controller; 
import com.example.boletasapp.proyecto.dto.BoletaRequestDTO;
import com.example.boletasapp.proyecto.dto.BoletaResponseDTO;
import com.example.boletasapp.proyecto.model.Boleta;
import com.example.boletasapp.proyecto.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/boletas") 
public class BoletaController {

    private final BoletaService boletaService;

    @Autowired 
    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @PostMapping
    public ResponseEntity<BoletaResponseDTO> crearBoleta(@RequestBody BoletaRequestDTO boletaRequestDTO) {
        Boleta boleta = convertToEntity(boletaRequestDTO);
        Boleta boletaCreada = boletaService.crearBoleta(boleta);
        return new ResponseEntity<>(convertToResponseDTO(boletaCreada), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletaResponseDTO> obtenerBoletaPorId(@PathVariable Long id) {
        return boletaService.obtenerBoletaPorId(id)
                .map(boleta -> ResponseEntity.ok(convertToResponseDTO(boleta)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BoletaResponseDTO>> obtenerTodasLasBoletas() {
        List<Boleta> boletas = boletaService.obtenerTodasLasBoletas();
        List<BoletaResponseDTO> boletaResponseDTOs = boletas.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(boletaResponseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletaResponseDTO> actualizarBoleta(@PathVariable Long id, @RequestBody BoletaRequestDTO boletaRequestDTO) {
        Boleta boletaActualizada = convertToEntity(boletaRequestDTO);
        
        try {
            Boleta boletaGuardada = boletaService.actualizarBoleta(id, boletaActualizada);
            return ResponseEntity.ok(convertToResponseDTO(boletaGuardada));
        } catch (RuntimeException e) { 
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBoleta(@PathVariable Long id) {
        try {
            boletaService.eliminarBoleta(id);
            return ResponseEntity.noContent().build(); 
        } catch (RuntimeException e) { 
             return ResponseEntity.notFound().build();
        }
    }

    




    private Boleta convertToEntity(BoletaRequestDTO dto) {
        Boleta boleta = new Boleta();
        boleta.setNumeroBoleta(dto.getNumeroBoleta());
        boleta.setFechaEmision(dto.getFechaEmision());
        boleta.setNombreCliente(dto.getNombreCliente());
        boleta.setMontoTotal(dto.getMontoTotal());
        boleta.setDescripcion(dto.getDescripcion());
        return boleta;
    }

    private BoletaResponseDTO convertToResponseDTO(Boleta boleta) {
        return new BoletaResponseDTO(
                boleta.getId(),
                boleta.getNumeroBoleta(),
                boleta.getFechaEmision(),
                boleta.getNombreCliente(),
                boleta.getMontoTotal(),
                boleta.getDescripcion()
        );
    }
}