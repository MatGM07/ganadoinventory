package com.ganado.inventario.service;
import com.ganado.inventario.dto.AnimalRequestDTO;
import com.ganado.inventario.dto.AnimalResponseDTO;
import com.ganado.inventario.mapper.AnimalMapper;
import com.ganado.inventario.model.Animal;
import com.ganado.inventario.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @Override
    public AnimalResponseDTO crear(AnimalRequestDTO dto) {
        Animal animal = animalMapper.toEntity(dto);
        return animalMapper.toResponse(animalRepository.save(animal));
    }

    @Override
    public AnimalResponseDTO obtenerPorId(UUID id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con ID: " + id));

        return animalMapper.toResponse(animal);
    }

    @Override
    public List<AnimalResponseDTO> listarTodos() {
        return animalRepository.findAll()
                .stream()
                .map(animalMapper::toResponse)
                .toList();
    }

    @Override
    public AnimalResponseDTO actualizar(UUID id, AnimalRequestDTO dto) {
        Animal existente = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con ID: " + id));

        animalMapper.updateEntity(existente, dto);

        return animalMapper.toResponse(animalRepository.save(existente));
    }

    @Override
    public void eliminar(UUID id) {
        Animal existente = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con ID: " + id));

        animalRepository.delete(existente);
    }
}