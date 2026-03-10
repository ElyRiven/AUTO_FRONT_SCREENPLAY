package org.example.utilidades;

/**
 * Constantes globales del proyecto de automatización.
 * Centraliza URLs y valores reutilizables en todos los tests.
 */
public final class Constantes {

    private Constantes() {}

    public static final String URL_BASE           = "http://localhost:3000";
    public static final String URL_REGISTRO       = URL_BASE + "/register";
    public static final String URL_DASHBOARD      = URL_BASE + "/dashboard";
}
