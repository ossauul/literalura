package com.challenge.literalura.principal;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.challenge.literalura.model.*;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;
import com.challenge.literalura.service.ConsumoAPI;
import com.challenge.literalura.service.ConvierteDatos;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA_LIBRO = "?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio_libro;
    private AutorRepository repositorio_autor;
    private Optional<Libro> libroValidacion;
    private List<Libro> libros;
    private List<Autor> autores;
    private Libro libro = new Libro();

    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor) {
        this.repositorio_libro = repositoryLibro;
        this.repositorio_autor = repositoryAutor;
    }

    public void muestraElMenu() {
        try {
            var opcion = -1;
            while (opcion != 11) {
                var menu = """
                        --------------------------------------------
                        ------------>  Menu Principal  <------------ 
                        --------------------------------------------        
                            1 - Buscar Libro en internet    
                            2 - Mostrar todos los libros guardados
                            3 - Mostrar libros en un idioma
                            4 - Mostrar todos los autores 
                            5 - Mostrar los autores vivos en un determinado año 
                            6 - Mostrar los autores vivos entre dos años determinados
                            7 - Mostrar top 10 libros descargados
                            8 - Busqueda de autor por nombre
                            9 - Busqueda de autor por año de nacimiento
                           10 - Busqueda de autor por año de fallecimiento
                           11 - Salir          
                           
                               """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        busquedaLibros();
                        break;
                    case 3:
                        busquedaLibrosIdioma();
                        break;
                    case 4:
                        busquedaAutores();
                        break;
                    case 5:
                        busquedaAutoresAnio();
                        break;
                    case 6:
                        busquedaAutoresEntreAnio();
                        break;
                    case 7:
                        topDiezLibros();
                        break;
                    case 8:
                        busquedaNombreAutor();
                        break;
                    case 9:
                        busquedaAnioNacimiento();
                        break;
                    case 10:
                        busquedaAnioFallecimiento();
                        break;
                    case 11:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }
        }catch (Exception e){
            System.out.println("Fallo critico cerrando...");
        }

    }

    public DatosLibro datosLibro() {
        System.out.println("Escribe el nombre del libro a buscar que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerLibros(URL_BASE + BUSQUEDA_LIBRO + nombreLibro.replace(" ", "+"));
        //System.out.println(URL_BASE + BUSQUEDA_LIBRO + nombreLibro.replace(" ", "+"));
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
            System.out.println("Libro insertado en la base de datos:");
            System.out.println(libro.toString1());
        }

    }
    public void busquedaLibros()
    {
        libros = repositorio_libro.busquedasLibros();
        libros.forEach(l -> System.out.println(l.toString1()));
    }
    public void busquedaLibrosIdioma()
    {
        try {
            System.out.print("Escribe el idioma de los libros que deseas buscar:\nINGLES\nFRANCES\nFINLANDES\nESPAÑOL\n");
            var idioma = teclado.nextLine().toUpperCase();
            var lenguaje = Lenguajes.fromEspaniol(idioma);
            libros = repositorio_libro.busquedaPorIdioma(lenguaje);
            System.out.printf("Libros con el idioma %s: %s \n", lenguaje, libros.size());
            libros.forEach(l -> System.out.println(l.toString1()));
        }catch (Exception e )
        {
            System.out.println("Error, intenta de nuevo "+ e);
        }


    }
    public void busquedaAutores()
    {
        autores = repositorio_autor.busquedaAutores();
        autores.forEach(a -> System.out.println(a.toString()));
    }
    public void busquedaAutoresAnio()
    {
        try {
            System.out.println("Escribe el año del cual deseas saber que autores estaban vivos");
            System.out.println("*Si deseas ingresar años antes de 500 a.C, ingresalos de manera negativa -499");
            var anio = teclado.nextInt();
            teclado.nextLine();
            autores = repositorio_autor.busquedaAutoresAnio(anio);
            System.out.printf("Autores vivos hasta la fecha %s: %s \n", anio, autores.size());
            autores.forEach(a -> System.out.println(a.toString()));
        }catch(NumberFormatException e)
        {
            System.out.println("Error, ingresa una fecha correcta");
        }catch (Exception e)
        {
            System.out.println("Error, intenta de nuevo");
        }

    }
    public void busquedaAutoresEntreAnio()
    {
        try {
            System.out.println("Escribe el primer año del cual deseas saber que autores estaban vivos");
            System.out.println("*Si deseas ingresar años antes de 500 a.C, ingresalos de manera negativa -499");
            var anio = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Escribe el segundo año del cual deseas saber que autores estaban vivos");
            var anio2 = teclado.nextInt();
            teclado.nextLine();
            autores = repositorio_autor.busquedaAutoresAnios(anio, anio2);
            System.out.printf("Autores vivos entre las fechas %s y %s: %s", anio, anio2, autores.size());
            autores.forEach(a -> System.out.println(a.toString()));
        }catch(NumberFormatException e)
        {
            System.out.println("Error, ingresa una fecha correcta");
        }catch (Exception e)
        {
            System.out.println("Error, intenta de nuevo");
        }

    }
    public void topDiezLibros()
    {
        libros = repositorio_libro.topDiezLibros();
        libros.forEach(l -> System.out.println(l.toString1()));
    }
    public void busquedaNombreAutor()
    {
        try{
            System.out.println("Ingresa el nombre del autor que deseas buscar");
            var nombre = teclado.nextLine();
            autores = repositorio_autor.busquedaNombre(nombre);
            autores.forEach(a -> System.out.println(a.toString()));
        }catch (Exception e)
        {
            System.out.println("Error, vuelve a intentarlo");
        }

    }
    public void busquedaAnioNacimiento()
    {
        try{
            System.out.println("Ingresa el año del autor que deseas buscar");
            var anio = teclado.nextInt();
            autores = repositorio_autor.busquedaPorNacimiento(anio);
            autores.forEach(a -> System.out.println(a.toString()));
        }catch (NumberFormatException n)
        {
            System.out.println("Ingresa un numero valido");
        }catch (Exception e)
        {
            System.out.println("Error, vuelve a intentarlo");
        }

    }
    public void busquedaAnioFallecimiento()
    {
        try{
            System.out.println("Ingresa el año del autor que deseas buscar");
            var anio = teclado.nextInt();
            autores = repositorio_autor.busquedaPorFallecimiento(anio);
            autores.forEach(a -> System.out.println(a.toString()));
        }catch (NumberFormatException n)
        {
            System.out.println("Ingresa un numero valido");
        }catch (Exception e)
        {
            System.out.println("Error, vuelve a intentarlo");
        }

    }

}
