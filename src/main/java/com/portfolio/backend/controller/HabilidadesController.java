package com.portfolio.backend.controller;


import com.portfolio.backend.entity.Habilidades;
import com.portfolio.backend.repository.HabilidadesRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class HabilidadesController {

    private final HabilidadesRepository habilidadesRepository;

    public HabilidadesController(final HabilidadesRepository habilidadesRepository) {
        this.habilidadesRepository = habilidadesRepository;

    }

    @GetMapping("/habilidades")
    public Iterable<Habilidades> getAllHabilidades() {
        return this.habilidadesRepository.findAll();
    }

    @GetMapping("/habilidades/{id}")
    public Optional<Habilidades> getHabilidadesById(@PathVariable("id") Integer id) {
        return this.habilidadesRepository.findById(id);
    }

    @PostMapping("/habilidades")
    @PreAuthorize("hasRole('ADMIN')")
    public Habilidades createNewHabilidades(@RequestBody Habilidades habilidades) {
        Habilidades newHabilidades = this.habilidadesRepository.save(habilidades);
        return newHabilidades;
    }

    @PutMapping("/habilidades/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Habilidades updateHabilidades(
            @PathVariable("id") Integer id,
            @RequestBody Habilidades hab
    ) {
        Optional<Habilidades> habilidadesToUpdateOptional = this.habilidadesRepository.findById(id);
        if (!habilidadesToUpdateOptional.isPresent()) {
            return null;
        }
        Habilidades habilidadesToUpdate = habilidadesToUpdateOptional.get();
        if (hab.getTitulo() != null) {
            habilidadesToUpdate.setTitulo(hab.getTitulo());
        }
        if (hab.getAvance() != null) {
            habilidadesToUpdate.setAvance(hab.getAvance());
        }

        Habilidades updatedHabilidades = this.habilidadesRepository.save(habilidadesToUpdate);
        return updatedHabilidades;
    }

    @DeleteMapping("/habilidades/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Habilidades deleteHabilidades(@PathVariable("id") Integer id){
        Optional<Habilidades> habilidadesToDeleteOptional = this.habilidadesRepository.findById(id);

        if(!habilidadesToDeleteOptional.isPresent()){
            return null;
        }

        Habilidades habilidadesToDelete = habilidadesToDeleteOptional.get();

        this.habilidadesRepository.delete(habilidadesToDelete);
        return habilidadesToDelete;
    }
}

