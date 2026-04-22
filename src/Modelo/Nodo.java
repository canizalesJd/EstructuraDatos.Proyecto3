package Modelo;
/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 3 - Estructura de Datos
 * Descripción: Clase Producto
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Abril 2026
 */
public class Nodo {
    // Atributos de la clase
    public Tarjeta data;
    public Nodo izquierdo;
    public Nodo derecho;
    
    // Constructor
    public Nodo(Tarjeta data) {
        this.data = data;
        this.izquierdo = null;
        this.derecho = null;
    }
}