package com.challenge.literalura.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;
    @ManyToMany(mappedBy = "autor")
    private List<Libro> libro;

    public Autor(){}
    public Autor(DatosAutor datosAutor)
    {
        this.nombre=datosAutor.fechaFallecimiento();
        try{
            this.fechaFallecimiento = LocalDate.parse(datosAutor.fechaFallecimiento());
        } catch (DateTimeParseException e){
            this.fechaFallecimiento = null;
        }
        try{
            this.fechaNacimiento = LocalDate.parse(datosAutor.fechaNacimiento());
        } catch (DateTimeParseException e){
            this.fechaNacimiento = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(LocalDate fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    @Override
    public String toString()
    {
        return "------Autor------\n" +
                "Nombre: "+ nombre +
                "\nFecha nacimiento: " + fechaNacimiento +
                "\nFecha fallecimiento: " + fechaFallecimiento +
                "\n Libro(s): " + libro;
    }
}
