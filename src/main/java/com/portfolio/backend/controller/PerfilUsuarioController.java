package com.portfolio.backend.controller;


import com.portfolio.backend.entity.PerfilUsuario;
import com.portfolio.backend.repository.PerfilUsuarioRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PerfilUsuarioController {

    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public PerfilUsuarioController(final PerfilUsuarioRepository perfilUsuarioRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;

    }

    @GetMapping("/perfilusuario")
    public Iterable<PerfilUsuario> getAllPerfilUsuario() {
        return this.perfilUsuarioRepository.findAll();
    }

    @GetMapping("/perfilusuario/{id}")
    public Optional<PerfilUsuario> getPerfilUsuarioById(@PathVariable("id") Integer id) {
        return this.perfilUsuarioRepository.findById(id);
    }

    @PostMapping("/perfilusuario")
    @PreAuthorize("hasRole('ADMIN')")
    public PerfilUsuario createNewPerfilUsuario(@RequestBody PerfilUsuario perfilUsuario) {
        PerfilUsuario newPerfilUsuario = this.perfilUsuarioRepository.save(perfilUsuario);
        return newPerfilUsuario;
    }

    @PutMapping("/perfilusuario/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public PerfilUsuario updatePerfilUsuario(
            @PathVariable("id") Integer id,
            @RequestBody PerfilUsuario usr
    ) {
        Optional<PerfilUsuario> perfilUsuarioToUpdateOptional = this.perfilUsuarioRepository.findById(id);
        if (!perfilUsuarioToUpdateOptional.isPresent()) {
            return null;
        }
        PerfilUsuario perfilUsuarioToUpdate = perfilUsuarioToUpdateOptional.get();

        if (usr.getNombre() != null) {
            perfilUsuarioToUpdate.setNombre(usr.getNombre());
        }
        if (usr.getCargo() != null) {
            perfilUsuarioToUpdate.setCargo(usr.getCargo());
        }
        if (usr.getIntro() != null) {
            perfilUsuarioToUpdate.setIntro(usr.getIntro());
        }
        if (usr.getImgUrl() != null) {
            perfilUsuarioToUpdate.setImgUrl(usr.getImgUrl());
        }

        PerfilUsuario updatedPerfilUsuario = this.perfilUsuarioRepository.save(perfilUsuarioToUpdate);
        return updatedPerfilUsuario;
    }

    @DeleteMapping("/perfilusuario/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public PerfilUsuario deletePerfilUsuario(@PathVariable("id") Integer id){
        Optional<PerfilUsuario> perfilUsuarioToDeleteOptional = this.perfilUsuarioRepository.findById(id);

        if(!perfilUsuarioToDeleteOptional.isPresent()){
            return null;
        }

        PerfilUsuario perfilUsuarioToDelete = perfilUsuarioToDeleteOptional.get();

        this.perfilUsuarioRepository.delete(perfilUsuarioToDelete);
        return perfilUsuarioToDelete;
    }
}
