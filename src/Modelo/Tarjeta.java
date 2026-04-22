package Modelo;

/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 3 - Estructura de Datos
 * Descripción: Clase Producto
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Abril 2026
 */

public class Tarjeta {
    // Atributos de la clase
    private int id;
    private String descripcion;
    private String categoria;
    
    // Tipo de reposteria
    public static final String[] CATEGORIA = {
        "Civiles",
        "Equipos",
        "Súper héroes",
        "Objetos",
        "Súper villanos",
        "Paneles",
        "Cara a cara",
        "Frases icónicas",
        "Película especial"
    };
    
    
    // Constructor vacio
    public Tarjeta() {
    }
    
    // Constructor sobrecargado
    public Tarjeta(int id, String descripcion, String categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }
    
    // Representacion Visual
    @Override
    public String toString() {
        return "Tarjeta(id: " + this.id + " | " + 
                "descripcion: " + this.descripcion + " | " +
                "categoria: " + this.categoria + ")";
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
