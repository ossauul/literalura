package com.challenge.literalura.principal;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.challenge.literalura.model.*;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;
import com.challenge.literalura.service.ConsumoAPI;
import com.challenge.literalura.service.ConvierteDatos;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA_LIBRO = "?search=";
    private final String BUSQUEDA_INICIO_FECHA1 = "author_year_start=";
    private final String BUSQUEDA_FIN_FECHA = "author_year_end=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio_libro;
    private Optional<Libro> libroValidacion;
    private AutorRepository libroAutor;

    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor) {
        this.repositorio_libro = repositoryLibro;
        this.libroAutor = repositoryAutor;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
             --------------------------------------------
             ------------>  Menu Principal  <------------ 
             --------------------------------------------        
                 1 - Buscar Libro en internet    
                 2 - Buscar episodios
                 3 - Mostrar series buscadas
                 0 - Salir          
             
             
                
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }


    public DatosLibro datosLibro() { //libro
        System.out.println("Escribe el nombre del libro a buscar que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerLibros(URL_BASE + BUSQUEDA_LIBRO + nombreLibro.replace(" ", "+"));
        System.out.println(URL_BASE + BUSQUEDA_LIBRO + nombreLibro.replace(" ", "+"));
        //System.out.println(json);
        List<DatosLibro> librosrecaudados = conversor.obtenerDatos(json, DatosResultados.class).libros();
        if(librosrecaudados == null || librosrecaudados.isEmpty())
        {
            System.out.println("Libro no encontrado, intenta de nuevo");
            return null;
        } else {
            DatosLibro libro = librosrecaudados.get(0);
            return libro;
        }
    }
    public void buscarLibroWeb ()
    {
        DatosLibro datos = datosLibro();
        Libro libro = new Libro(datos);
        libroValidacion = repositorio_libro.findByTituloEqualsIgnoreCase(libro.getTitulo());
        if(libroValidacion.isPresent())
        {
            System.out.println("Libro ya presente en la base de datos, buscarlo en el apartado correspondiente");
        }else {
            repositorio_libro.save(libro);
        }

    }



}
