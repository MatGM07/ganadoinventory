package com.ganado.inventario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalRequestDTO {
    private String especie;
    private String raza;
    private String sexo;
    private LocalDate fechaNacimiento;
    private Double peso;
    private String ubicacion;
}