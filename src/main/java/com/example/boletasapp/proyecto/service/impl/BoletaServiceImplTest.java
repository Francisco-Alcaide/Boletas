package com.example.boletasapp.proyecto.service.impl;

import com.example.boletasapp.proyecto.model.Boleta;
import com.example.boletasapp.proyecto.repository.BoletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita las anotaciones de Mockito en JUnit 5
class BoletaServiceImplTest {

    @Mock // Crea un mock (simulacro) de esta dependencia
    private BoletaRepository boletaRepository;

    @InjectMocks // Crea una instancia de esta clase e inyecta los mocks definidos con @Mock
    private BoletaServiceImpl boletaService; // Esta es la clase que vamos a probar

    private Boleta boleta;

    // Este método se ejecuta antes de cada prueba (@Test)
    @BeforeEach
    void setUp() {
        // Creamos un objeto de prueba que usaremos en varios tests
        boleta = new Boleta();
        boleta.setId(1L);
        boleta.setNumeroBoleta("B001-001");
        boleta.setNombreCliente("Cliente de Prueba");
        boleta.setFechaEmision(LocalDate.now());
        boleta.setMontoTotal(100.0);
    }

    @DisplayName("Test para guardar una boleta")
    @Test
    void crearBoleta_conDatosValidos_debeDevolverBoletaGuardada() {
        // Given (Dado): Configuramos el comportamiento del mock.
        // Cuando se llame a boletaRepository.save() con cualquier objeto Boleta...
        given(boletaRepository.save(any(Boleta.class))).willReturn(boleta);

        // When (Cuando): Ejecutamos el método que queremos probar.
        Boleta boletaGuardada = boletaService.crearBoleta(new Boleta());

        // Then (Entonces): Verificamos que el resultado es el esperado.
        assertNotNull(boletaGuardada);
        assertEquals("B001-001", boletaGuardada.getNumeroBoleta());

        // Opcional: Verificar que el método del mock fue llamado exactamente 1 vez.
        verify(boletaRepository, times(1)).save(any(Boleta.class));
    }

    @DisplayName("Test para obtener una boleta por su ID existente")
    @Test
    void obtenerBoletaPorId_cuandoIdExiste_debeDevolverBoleta() {
        // Given
        given(boletaRepository.findById(1L)).willReturn(Optional.of(boleta));

        // When
        Optional<Boleta> boletaEncontrada = boletaService.obtenerBoletaPorId(1L);

        // Then
        assertTrue(boletaEncontrada.isPresent());
        assertEquals(1L, boletaEncontrada.get().getId());
    }

    @DisplayName("Test para obtener una boleta por su ID no existente")
    @Test
    void obtenerBoletaPorId_cuandoIdNoExiste_debeDevolverOptionalVacio() {
        // Given
        given(boletaRepository.findById(99L)).willReturn(Optional.empty());

        // When
        Optional<Boleta> boletaEncontrada = boletaService.obtenerBoletaPorId(99L);

        // Then
        assertFalse(boletaEncontrada.isPresent());
    }

    @DisplayName("Test para obtener todas las boletas")
    @Test
    void obtenerTodasLasBoletas_debeDevolverListaDeBoletas() {
        // Given
        Boleta boleta2 = new Boleta();
        boleta2.setId(2L);
        boleta2.setNumeroBoleta("B001-002");

        given(boletaRepository.findAll()).willReturn(List.of(boleta, boleta2));

        // When
        List<Boleta> listaBoletas = boletaService.obtenerTodasLasBoletas();

        // Then
        assertNotNull(listaBoletas);
        assertEquals(2, listaBoletas.size());
    }

    @DisplayName("Test para actualizar una boleta que no existe debe lanzar una excepción")
    @Test
    void actualizarBoleta_cuandoIdNoExiste_debeLanzarRuntimeException() {
        // Given
        given(boletaRepository.findById(99L)).willReturn(Optional.empty());

        Boleta datosNuevos = new Boleta();
        datosNuevos.setNombreCliente("Cliente Nuevo");

        // When & Then
        // Verificamos que al ejecutar el método, se lanza la excepción esperada.
        assertThrows(RuntimeException.class, () -> boletaService.actualizarBoleta(99L, datosNuevos));

        // Verificamos que el método save NUNCA fue llamado.
        verify(boletaRepository, never()).save(any(Boleta.class));
    }
}