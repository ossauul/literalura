package com.challenge.literalura.model;

public enum Lenguajes {
    INGLES("en", "Ingles"),
    FRANCES("fr", "Frances"),
    FINLANDES("fi", "Finlandes"),
    ESPAÑOL("es", "Español");

    private String lenguajeAPI;
    private String lenguajeAPIE;

    Lenguajes(String lenguajeAPI, String lenguajeAPIE) {
        this.lenguajeAPI = lenguajeAPI;
        this.lenguajeAPIE = lenguajeAPIE;
    }
    public static Lenguajes fromString(String text) {
        for (Lenguajes lenguaje : Lenguajes.values()) {
            if (lenguaje.lenguajeAPI.equalsIgnoreCase(text)) {
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Lenguaje no encontrado: " + text);
    }
    public static Lenguajes fromEspaniol(String text) {
        for (Lenguajes lenguajes : Lenguajes.values()) {
            if (lenguajes.lenguajeAPIE.equalsIgnoreCase(text)) {
                return lenguajes;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }


}
