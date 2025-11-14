package com.ganado.inventario.dto;

import java.time.LocalDate;

public record CreateAnimalRequest(
        String identificacion,
        String raza,
        Integer edad,
        Double peso,
        String ubicacion,
        LocalDate fechaNacimiento
) {}