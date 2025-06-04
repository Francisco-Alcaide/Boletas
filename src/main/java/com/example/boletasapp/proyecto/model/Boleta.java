package com.example.boletasapp.proyecto.model; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;



@Entity 
@Table(name = "boletas") 
public class Boleta {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String numeroBoleta;
    private LocalDate fechaEmision;
    private String nombreCliente;
    private Double montoTotal;
    private String descripcion; 
    
    public Boleta() {
    }

    
    public Boleta(String numeroBoleta, LocalDate fechaEmision, String nombreCliente, Double montoTotal, String descripcion) {
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

    
    @Override
    public String toString() {
        return "Boleta{" +
                "id=" + id +
                ", numeroBoleta='" + numeroBoleta + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", montoTotal=" + montoTotal +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}