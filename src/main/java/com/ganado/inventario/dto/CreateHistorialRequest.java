package com.ganado.inventario.dto;

public record CreateHistorialRequest(
        String tipoEvento,
        String descripcion
) {}
