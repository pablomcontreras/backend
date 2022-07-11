package com.portfolio.backendCrudyAuth.repository;


import com.portfolio.backendCrudyAuth.entity.Rol;
import com.portfolio.backendCrudyAuth.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}