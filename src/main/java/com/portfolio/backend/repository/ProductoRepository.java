package com.portfolio.backend.repository;

import com.portfolio.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombreProducto(String np);
    boolean existsByNombreProducto(String np);
}