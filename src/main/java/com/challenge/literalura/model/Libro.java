package com.challenge.literalura.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "Libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;

    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "fk_libro", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_autor", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Autor> autor;
    @Enumerated(EnumType.STRING)
    private Lenguajes idiomas;
    private String genero;
    private Long descargas;


    public Libro() {
    }

    public Libro(DatosLibro datoslibro) {
        this.titulo = datoslibro.titulo();
        this.idiomas = Lenguajes.fromString( datoslibro.idiomas().get(0).split(",")[0].trim());
        this.autor = datoslibro.autor().stream().map(Autor::new).collect(Collectors.toList());
        this.descargas = datoslibro.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor =autor;
    }

    public Lenguajes getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Lenguajes idiomas) {
        this.idiomas = idiomas;
    }

    public void agregarAutor(Autor autor){
        if(this.autor == null){
            this.autor = new ArrayList<>();
        }
        this.autor.add(autor);
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString(){
       return  "------Libro------"+
               "\nTitulo: " + titulo +
               "\nIdioma: " + idiomas +
               "\nDescargas: " + descargas;
    }
    public String toString1(){
        return  "------Libro------"+
                "\nTitulo: " + titulo +
                "\nAutor: " + autor +
                "\nIdioma: " + idiomas +
                "\nDescargas: " + descargas;
    }
}

