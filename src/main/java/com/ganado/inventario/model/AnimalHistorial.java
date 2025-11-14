package com.ganado.inventario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "animal_historial")
@Data                   // getters, setters, toString, equals y hashCode
@NoArgsConstructor      // constructor vacío
@AllArgsConstructor     // constructor con todos los campos
@Builder
public class AnimalHistorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ejemplo: "VACUNACIÓN", "TRATAMIENTO", "NACIMIENTO", "PESO", "TRASLADO", ...
    @Column(nullable = false)
    private String tipoEvento;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    // getters y setters
}