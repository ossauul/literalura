package com.challenge.literalura.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int fechaNacimiento;
    private int fechaFallecimiento;
    @ManyToMany(mappedBy = "autor")
    private List<Libro> libro;


    public Autor(){}

    public Autor(String nombre, int fechaNacimiento, int fechaFallecimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Autor(DatosAutor datosAutor)
    {
        this.nombre=datosAutor.nombre();
        try{
            this.fechaFallecimiento = Integer.parseInt(String.valueOf(datosAutor.fechaFallecimiento()));
        } catch (Exception e){
            this.fechaFallecimiento = 0;
        }
        try{
            this.fechaNacimiento = Integer.parseInt(String.valueOf(datosAutor.fechaNacimiento()));
        } catch (Exception e){
            this.fechaNacimiento = 0;
        }
    }

   public Autor(List<DatosAutor> datosAutor)
    {
        datosAutor.stream().forEach(d-> new Autor(d.nombre(),d.fechaNacimiento(),d.fechaFallecimiento()));
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

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(int fechaFallecimiento) {
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
                "\nLibro(s): " + libro.stream().map(l -> l.getTitulo()).collect(Collectors.toList());
    }
}
