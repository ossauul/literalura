package com.challenge.literalura.model;

public enum Lenguajes {
    INGLES("en"),
    FRANCES("fr"),
    FINLANDES("fi");

    private String lenguajeAPI;

    Lenguajes(String lenguajeAPI) {
        this.lenguajeAPI = lenguajeAPI;
    }
    public static Lenguajes fromString(String text) {
        for (Lenguajes lenguaje : Lenguajes.values()) {
            if (lenguaje.lenguajeAPI.equalsIgnoreCase(text)) {
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Lenguaje no encontrado: " + text);
    }


}
