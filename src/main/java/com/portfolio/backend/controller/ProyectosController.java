package com.portfolio.backend.controller;


import com.portfolio.backend.entity.Proyectos;
import com.portfolio.backend.repository.ProyectosRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
//@RequestMapping("/api")
public class ProyectosController {

    private final ProyectosRepository proyectosRepository;

    public ProyectosController(final ProyectosRepository proyectosRepository) {
        this.proyectosRepository = proyectosRepository;

    }

    @GetMapping("/api/proyectos")
    public Iterable<Proyectos> getAllProyectos() {
        return this.proyectosRepository.findAll();
    }

    @GetMapping("/api/proyectos/{id}")
    public Optional<Proyectos> getProyectosById(@PathVariable("id") Integer id) {
        return this.proyectosRepository.findById(id);
    }

    @PostMapping("/editor/proyectos")
    @PreAuthorize("hasRole('ADMIN')")
    public Proyectos createNewProyectos(@RequestBody Proyectos proyectos) {
        Proyectos newProyectos = this.proyectosRepository.save(proyectos);
        return newProyectos;
    }

    @PutMapping("/editor/proyectos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Proyectos updateProyecto(
            @PathVariable("id") Integer id,
            @RequestBody Proyectos pro
    ) {
        Optional<Proyectos> proyectosToUpdateOptional = this.proyectosRepository.findById(id);
        if (!proyectosToUpdateOptional.isPresent()) {
            return null;
        }
        Proyectos proyectosToUpdate = proyectosToUpdateOptional.get();

        if (pro.getTitulo() != null) {
            proyectosToUpdate.setTitulo(pro.getTitulo());
        }
        if (pro.getDescripcion() != null) {
            proyectosToUpdate.setDescripcion(pro.getDescripcion());
        }
        if (pro.getImgUrl() != null) {
            proyectosToUpdate.setImgUrl(pro.getImgUrl());
        }
        if (pro.getTecnologias() != null) {
            proyectosToUpdate.setTitulo(pro.getTecnologias());
        }

        Proyectos updatedProyectos = this.proyectosRepository.save(proyectosToUpdate);
        return updatedProyectos;
    }

    @DeleteMapping("/editor/proyectos/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public Proyectos deleteProyectos(@PathVariable("id") Integer id){
        Optional<Proyectos> proyectosToDeleteOptional = this.proyectosRepository.findById(id);

        if(!proyectosToDeleteOptional.isPresent()){
            return null;
        }

        Proyectos proyectosToDelete = proyectosToDeleteOptional.get();

        this.proyectosRepository.delete(proyectosToDelete);
        return proyectosToDelete;
    }
}

