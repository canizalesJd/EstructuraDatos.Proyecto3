package Modelo;

/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 3 - Estructura de Datos
 * Descripción: Clase Producto
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Abril 2026
 */

public class ListaProductos {
    // Atributos de la clase
    Nodo head;
    
    // Constructor
    public ListaProductos() {
        this.head = null;
    }
    
    // Insertar de forma ordenada
    public void insertarOrdenado(Tarjeta p) {
        Nodo nuevo = new Nodo(p);

        // Si esta vacia, inserta al inicio de la lista
        if (head == null || p.getId() < head.data.getId()) {
            nuevo.siguiente = head;
            head = nuevo;
            return;
        }

        Nodo actual = head;

        // Recorrer hasta encontrar posicion correcta
        while (actual.siguiente != null &&
               actual.siguiente.data.getId() < p.getId()) {
            actual = actual.siguiente;
        }

        // Insertar en medio o final
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    // Obtener el head
    public Nodo getHead() {
        return head;
    }

    // Metodo para obtener el contador total de productos
    public int contarProductos() {
        int contador = 0;
        Nodo actual = head;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }
    
}
