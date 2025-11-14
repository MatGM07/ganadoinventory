package com.ganado.inventario.controller;

import com.ganado.inventario.dto.AnimalDTO;
import com.ganado.inventario.dto.CreateAnimalRequest;
import com.ganado.inventario.dto.CreateHistorialRequest;
import com.ganado.inventario.dto.HistorialDTO;
import com.ganado.inventario.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/animales")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> create(@RequestBody CreateAnimalRequest req) {
        AnimalDTO created = service.createAnimal(req);
        return ResponseEntity.created(URI.create("/api/animales/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDTO> update(@PathVariable Long id, @RequestBody CreateAnimalRequest req) {
        return ResponseEntity.ok(service.updateAnimal(id, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAnimal(id));
    }

    @GetMapping("/identificacion/{ident}")
    public ResponseEntity<AnimalDTO> getByIdent(@PathVariable String ident) {
        return ResponseEntity.ok(service.getByIdentificacion(ident));
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping("/{id}/historial")
    public ResponseEntity<HistorialDTO> addHistorial(@PathVariable Long id, @RequestBody CreateHistorialRequest req) {
        HistorialDTO h = service.addHistorial(id, req);
        return ResponseEntity.created(URI.create("/api/animales/" + id + "/historial/" + h.id())).body(h);
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<List<HistorialDTO>> historial(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHistorial(id));
    }
}
