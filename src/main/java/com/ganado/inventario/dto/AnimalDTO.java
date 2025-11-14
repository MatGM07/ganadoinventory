package com.ganado.inventario.dto;

import java.time.LocalDate;
import java.util.List;

public record AnimalDTO(
        Long id,
        String identificacion,
        String raza,
        Integer edad,
        Double peso,
        String ubicacion,
        LocalDate fechaNacimiento,
        List<HistorialDTO> historial
) {}