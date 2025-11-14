package com.ganado.inventario.repository;

import com.ganado.inventario.model.AnimalHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimalHistorialRepository extends JpaRepository<AnimalHistorial, Long> {
    List<AnimalHistorial> findByAnimalIdOrderByFechaDesc(Long animalId);
}
