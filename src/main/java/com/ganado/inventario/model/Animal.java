package com.ganado.inventario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animales")
@Data                   // getters, setters, toString, equals y hashCode
@NoArgsConstructor      // constructor vacío
@AllArgsConstructor     // constructor con todos los campos
@Builder                // builder pattern (opcional)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identificacion; // chip, marca o código

    @Column(nullable = false)
    private String raza;

    @Column(nullable = false)
    private String especie;

    // edad en años (se puede calcular a partir de fechaNacimiento si prefieres)
    private Integer edad;

    private Double peso; // kg

    private String ubicacion; // nombre del potrero/galpón/estabulación

    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("fecha DESC")
    private List<AnimalHistorial> historial = new ArrayList<>();

    // getters y setters (omitir por brevedad)
    // constructor vacío, equals/hashCode por id
}