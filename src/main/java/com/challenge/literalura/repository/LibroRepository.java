package com.challenge.literalura.repository;

import com.challenge.literalura.model.Autor;
import com.challenge.literalura.model.Lenguajes;
import com.challenge.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    //Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    @Query("SELECT l.titulo FROM Libro l WHERE LOWER(l.titulo) = LOWER(:titulo)")
    Optional<Libro> findByTituloEqualsIgnoreCase(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l JOIN FETCH l.autor WHERE l.idiomas = %:idiomas%")
    List<Libro> busquedaPorIdioma(Lenguajes idiomas);

    @Query("SELECT l FROM Libro l JOIN FETCH l.autor")
    List<Libro> busquedasLibros();

    @Query("SELECT l FROM Libro l JOIN FETCH l.autor order by l.descargas DESC LIMIT 10")
    List<Libro> topDiezLibros();







}
