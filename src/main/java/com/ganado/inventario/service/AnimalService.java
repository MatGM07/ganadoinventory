package com.ganado.inventario.service;

import com.ganado.inventario.dto.AnimalDTO;
import com.ganado.inventario.dto.CreateAnimalRequest;
import com.ganado.inventario.dto.CreateHistorialRequest;
import com.ganado.inventario.dto.HistorialDTO;

import java.util.List;

public interface AnimalService {
    AnimalDTO createAnimal(CreateAnimalRequest request);
    AnimalDTO updateAnimal(Long id, CreateAnimalRequest request);
    AnimalDTO getAnimal(Long id);
    AnimalDTO getByIdentificacion(String identificacion);
    List<AnimalDTO> listAll();
    HistorialDTO addHistorial(Long animalId, CreateHistorialRequest request);
    List<HistorialDTO> getHistorial(Long animalId);
}