package com.challenge.literalura.repository;

import com.challenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String Autor);

}