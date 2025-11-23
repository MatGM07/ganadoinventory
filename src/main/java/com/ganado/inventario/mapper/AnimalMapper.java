package com.ganado.inventario.mapper;


import com.ganado.inventario.dto.AnimalRequestDTO;
import com.ganado.inventario.dto.AnimalResponseDTO;
import com.ganado.inventario.model.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public Animal toEntity(AnimalRequestDTO dto) {
        Animal animal = new Animal();
        animal.setEspecie(dto.getEspecie());
        animal.setRaza(dto.getRaza());
        animal.setSexo(dto.getSexo());
        animal.setFechaNacimiento(dto.getFechaNacimiento());
        animal.setPeso(dto.getPeso());
        animal.setUbicacion(dto.getUbicacion());
        return animal;
    }

    public AnimalResponseDTO toResponse(Animal entity) {
        AnimalResponseDTO dto = new AnimalResponseDTO();
        dto.setId(entity.getId());
        dto.setEspecie(entity.getEspecie());
        dto.setRaza(entity.getRaza());
        dto.setSexo(entity.getSexo());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setPeso(entity.getPeso());
        dto.setUbicacion(entity.getUbicacion());
        return dto;
    }

    public void updateEntity(Animal entity, AnimalRequestDTO dto) {
        entity.setEspecie(dto.getEspecie());
        entity.setRaza(dto.getRaza());
        entity.setSexo(dto.getSexo());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setPeso(dto.getPeso());
        entity.setUbicacion(dto.getUbicacion());
    }
}
