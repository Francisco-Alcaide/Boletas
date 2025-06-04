package com.example.boletasapp.proyecto.dto;

import java.time.LocalDate;



public class BoletaResponseDTO {
    private Long id;
    private String numeroBoleta;
    private LocalDate fechaEmision;
    private String nombreCliente;
    private Double montoTotal;
    private String descripcion;

    
    public BoletaResponseDTO() {
    }

    public BoletaResponseDTO(Long id, String numeroBoleta, LocalDate fechaEmision, String nombreCliente, Double montoTotal, String descripcion) {
        this.id = id;
        this.numeroBoleta = numeroBoleta;
        this.fechaEmision = fechaEmision;
        this.nombreCliente = nombreCliente;
        this.montoTotal = montoTotal;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(String numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}