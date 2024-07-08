package com.challenge.literalura.repository;

import com.challenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a JOIN FETCH a.libro")
    List<Autor> busquedaAutores();

    @Query("SELECT a FROM Autor a JOIN FETCH a.libro l WHERE a.fechaFallecimiento <= :anio")
    List<Autor> busquedaAutoresAnio(int anio);

    @Query("SELECT a FROM Autor a JOIN FETCH a.libro l WHERE a.fechaFallecimiento >= :anio AND a.fechaFallecimiento <= :anio2")
    List<Autor> busquedaAutoresAnios(@Param("anio") int anio, @Param("anio2") int anio2);

    @Query("SELECT a FROM Autor a JOIN FETCH a.libro WHERE a.nombre ILIKE %:nombre%")
    List<Autor> busquedaNombre(String nombre);

    @Query("SELECT a FROM Autor a JOIN FETCH a.libro WHERE a.fechaNacimiento = :fecha")
    List<Autor> busquedaPorNacimiento(int fecha);

    @Query("SELECT a FROM Autor a JOIN FETCH a.libro WHERE a.fechaFallecimiento = :fecha")
    List<Autor> busquedaPorFallecimiento(int fecha);

}
