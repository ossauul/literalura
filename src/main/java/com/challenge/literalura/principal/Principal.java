package com.challenge.literalura.principal;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.challenge.literalura.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA_LIBRO = "?search=";
    private final String BUSQUEDA_INICIO_FECHA1 = "author_year_start=";
    private final String BUSQUEDA_FIN_FECHA = "author_year_end=";

//Cambiar void a clase de Datos
    public void getDatosSerie() {
        //System.out.println("Escribe el nombre del libro a buscar que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerLibros(URL_BASE + BUSQUEDA_LIBRO + nombreLibro.replace(" ", "+"));
        System.out.println(URL_BASE + BUSQUEDA_LIBRO + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        //DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        //return datos;
    }


}
