package com.ganado.inventario.service;

import com.ganado.inventario.dto.AnimalDTO;
import com.ganado.inventario.dto.CreateAnimalRequest;
import com.ganado.inventario.dto.CreateHistorialRequest;
import com.ganado.inventario.dto.HistorialDTO;
import com.ganado.inventario.model.Animal;
import com.ganado.inventario.model.AnimalHistorial;
import com.ganado.inventario.repository.AnimalHistorialRepository;
import com.ganado.inventario.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalHistorialRepository historialRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository,
                             AnimalHistorialRepository historialRepository) {
        this.animalRepository = animalRepository;
        this.historialRepository = historialRepository;
    }

    @Override
    public AnimalDTO createAnimal(CreateAnimalRequest req) {
        Animal a = new Animal();
        a.setIdentificacion(req.identificacion());
        a.setRaza(req.raza());
        a.setEdad(req.edad());
        a.setPeso(req.peso());
        a.setUbicacion(req.ubicacion());
        a.setFechaNacimiento(req.fechaNacimiento());
        animalRepository.save(a);
        return toDto(a);
    }

    @Override
    public AnimalDTO updateAnimal(Long id, CreateAnimalRequest req) {
        Animal a = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal no encontrado: " + id));
        a.setRaza(req.raza());
        a.setEdad(req.edad());
        a.setPeso(req.peso());
        a.setUbicacion(req.ubicacion());
        a.setFechaNacimiento(req.fechaNacimiento());
        animalRepository.save(a);
        return toDto(a);
    }

    @Override
    @Transactional(readOnly = true)
    public AnimalDTO getAnimal(Long id) {
        Animal a = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal no encontrado: " + id));
        // cargar historial por JPA (ya definido con fetch lazy)
        a.getHistorial().size();
        return toDto(a);
    }

    @Override
    @Transactional(readOnly = true)
    public AnimalDTO getByIdentificacion(String identificacion) {
        Animal a = animalRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new IllegalArgumentException("Animal no encontrado: " + identificacion));
        a.getHistorial().size();
        return toDto(a);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnimalDTO> listAll() {
        return animalRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public HistorialDTO addHistorial(Long animalId, CreateHistorialRequest req) {
        Animal a = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal no encontrado: " + animalId));
        AnimalHistorial h = new AnimalHistorial();
        h.setTipoEvento(req.tipoEvento());
        h.setDescripcion(req.descripcion());
        h.setFecha(LocalDateTime.now());
        h.setAnimal(a);
        historialRepository.save(h);
        a.getHistorial().add(0, h); // opcional: mantener más reciente al inicio
        return toHistDto(h);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialDTO> getHistorial(Long animalId) {
        return historialRepository.findByAnimalIdOrderByFechaDesc(animalId)
                .stream().map(this::toHistDto).collect(Collectors.toList());
    }

    /* ---------------- mapeos simples ---------------- */
    private AnimalDTO toDto(Animal a) {
        List<HistorialDTO> hist = a.getHistorial().stream()
                .map(this::toHistDto).collect(Collectors.toList());
        return new AnimalDTO(
                a.getId(),
                a.getIdentificacion(),
                a.getRaza(),
                a.getEdad(),
                a.getPeso(),
                a.getUbicacion(),
                a.getFechaNacimiento(),
                hist
        );
    }

    private HistorialDTO toHistDto(AnimalHistorial h) {
        return new HistorialDTO(h.getId(), h.getTipoEvento(), h.getDescripcion(), h.getFecha());
    }
}
