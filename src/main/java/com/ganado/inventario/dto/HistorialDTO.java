package com.ganado.inventario.dto;

import java.time.LocalDateTime;

public record HistorialDTO(
        Long id,
        String tipoEvento,
        String descripcion,
        LocalDateTime fecha
) {}
