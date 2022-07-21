package com.portfolio.backend.controller;


import com.portfolio.backend.entity.Educacion;
import com.portfolio.backend.repository.EducacionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
//@RequestMapping("/api")
public class EducacionController {

    private final EducacionRepository educacionRepository;

    public EducacionController(final EducacionRepository educacionRepository) {
        this.educacionRepository = educacionRepository;
    }

    @GetMapping("/api/educacion")

    public Iterable<Educacion> getAllEducacion() {
        return this.educacionRepository.findAll();
    }

    @GetMapping("/api/educacion/{id}")
    public Optional<Educacion> getEducacionById(@PathVariable("id") Integer id) {
        return this.educacionRepository.findById(id);
    }

    @PostMapping("/editor/agregar/educacion")
    @PreAuthorize("hasRole('ADMIN')")
    public Educacion createNewEducacion(@RequestBody Educacion educacion) {
        Educacion newEducacion = this.educacionRepository.save(educacion);
        return newEducacion;
    }

    @PutMapping("/editor/editar/educacion/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Educacion updateEducacion(
            @PathVariable("id") Integer id,
            @RequestBody Educacion edu
    ) {
        Optional<Educacion> educacionToUpdateOptional = this.educacionRepository.findById(id);
        if (!educacionToUpdateOptional.isPresent()) {
            return null;
        }
        Educacion educacionToUpdate = educacionToUpdateOptional.get();
        if (edu.getCurso() != null) {
            educacionToUpdate.setCurso(edu.getCurso());
        }
        if (edu.getInstitucion() != null) {
            educacionToUpdate.setInstitucion(edu.getInstitucion());
        }
        if (edu.getImgUrl() != null) {
            educacionToUpdate.setImgUrl(edu.getImgUrl());
        }
        if (edu.getFechaDesde() != null) {
            educacionToUpdate.setFechaDesde(edu.getFechaDesde());
        }
        if (edu.getFechaHasta() != null) {
            educacionToUpdate.setFechaHasta(edu.getFechaHasta());
        }
        if (edu.getDescripcion() != null) {
            educacionToUpdate.setDescripcion(edu.getDescripcion());
        }

        Educacion updatedEducacion = this.educacionRepository.save(educacionToUpdate);
        return updatedEducacion;
    }

    @DeleteMapping("/editor/borrar/educacion/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public Educacion deleteEducacion(@PathVariable("id") Integer id){
        Optional<Educacion> educacionToDeleteOptional = this.educacionRepository.findById(id);

        if(!educacionToDeleteOptional.isPresent()){
            return null;
        }

        Educacion educacionToDelete = educacionToDeleteOptional.get();

        this.educacionRepository.delete(educacionToDelete);
        return educacionToDelete;
    }
}

