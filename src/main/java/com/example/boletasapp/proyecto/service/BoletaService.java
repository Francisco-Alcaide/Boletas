package com.example.boletasapp.proyecto.service; 
import com.example.boletasapp.proyecto.model.Boleta;
import java.util.List;
import java.util.Optional;

public interface BoletaService {

    Boleta crearBoleta(Boleta boleta);
    Optional<Boleta> obtenerBoletaPorId(Long id);
    List<Boleta> obtenerTodasLasBoletas();
    Boleta actualizarBoleta(Long id, Boleta boletaActualizada);
    void eliminarBoleta(Long id);
}