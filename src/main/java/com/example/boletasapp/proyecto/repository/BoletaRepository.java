package com.example.boletasapp.proyecto.repository; 

import com.example.boletasapp.proyecto.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    
}