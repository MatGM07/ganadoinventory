package com.ganado.inventario.repository;

import com.ganado.inventario.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByIdentificacion(String identificacion);
    List<Animal> findByRaza(String raza);
}