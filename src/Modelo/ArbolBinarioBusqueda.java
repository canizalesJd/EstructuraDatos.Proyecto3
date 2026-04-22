package Modelo;
/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 3 - Estructura de Datos
 * Descripción: Clase Producto
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Abril 2026
 *
 * Referencias
 * [1] https://www.geeksforgeeks.org/java/java-program-to-construct-a-binary-search-tree/
 * [2] https://stackoverflow.com/questions/19279586/best-way-to-modify-an-existing-string-stringbuilder-or-convert-to-char-array-an
 */

public class ArbolBinarioBusqueda {
    // Propiedades de la clase
    private Nodo raiz;

    // Metodos
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }
    
    // Metodos hechos con referencia en el articulo 
    // Referencia [1]
    
    // Metodo para instertar en el arbol
    public boolean insertar(Tarjeta tarjeta) {
        // La tarjeta debe ser valiad
        if (tarjeta == null) {
            return false;
        }
        // El id debe ser mayor a 0
        if (tarjeta.getId() <= 0) {
            return false;
        }
        
        // Crear el nodo
        Nodo nuevo = new Nodo(tarjeta);
        
        // Si esta vacio, insertar como raiz
        if (raiz == null) {
            raiz = nuevo;
            return true; // Retorna verdarero, ya que se inserto de forma correcta
        }
        
        // Llamar a la funcion recursiva para insertar el nodo
        return insertarRec(raiz, nuevo);
    }
    
    // Metodo recursivo para insertar nodos
    public boolean insertarRec(Nodo actual, Nodo nuevo) {
        // revisar que no sea duplicado        
        if (nuevo.data.getId() == actual.data.getId()) {
            return false;
        }
        
        // revisar que sea menor al padre
        if (nuevo.data.getId() < actual.data.getId()) {
            // si el nodo izquierdo esta vacio, insertar
            if (actual.izquierdo == null) {
                actual.izquierdo = nuevo;
                return true;
            } else {
                // si el nodo izquierdo esta lleno, moverse al subarbol izq y 
                // llamar la funcion nuevamente
                return insertarRec(actual.izquierdo, nuevo);
            }
        } else {
            // si es mayor que el padre, debe ir del subarbol derecho
            if (actual.derecho == null) {
                // si esta vacio, insertar
                actual.derecho = nuevo;
                return true;
            } else {
                // si no esta vacio, recursar desde el nodo derecho
                return insertarRec(actual.derecho, nuevo);
            }
        }
    }
    
    // Metodo para buscar 
    public Tarjeta buscar(int id) {
        Nodo encontrado = buscarNodo(raiz, id);
        // Verificar que no sea nulo
        if (encontrado != null) {
            return encontrado.data;
        }
        return null;
    }
    
    // Metodo recursivo de busqueda
    private Nodo buscarNodo(Nodo actual, int id) {
        // Verificar que no sea nulo
        if (actual == null) {
            return null;
        }
        // si coinciden los Ids, ha sido encontrado
        if (id == actual.data.getId()) {
            return actual;
        }
        // si el id es menor al actual, moverse al nodo izquierdo
        if (id < actual.data.getId()) {
            return buscarNodo(actual.izquierdo, id);
        }
        // caso contrario, buscar en el nodo derecho
        return buscarNodo(actual.derecho, id);
    }
    
    // Metodo para eliminar
    public String eliminar(int id) {
        // verificar que el ID sea mayor a 0
        if (id <= 0) {
            return "ID_INVALIDO";
        }
        // encontrar el nodo
        Nodo objetivo = buscarNodo(raiz, id);
        // verificar que no sea nulo
        if (objetivo == null) {
            return "NO_ENCONTRADO";
        }
        // No se puede eliminar nodos de la categoria "Civiles"
        if ("Civiles".equalsIgnoreCase(objetivo.data.getCategoria())) {
            return "NO_PERMITIDO_CIVILES";
        }
        // variables para verificar si tiene hijos izq o der
        boolean tieneIzquierdo = objetivo.izquierdo != null;
        boolean tieneDerecho = objetivo.derecho != null;
        // Si tiene ambos hijos, no se puede eliminar
        if (tieneIzquierdo && tieneDerecho) {
            return "NO_PERMITIDO_DOS_HIJOS";
        }
        // Si solo tiene subarbol derecho, no se puede eliminar
        if (!tieneIzquierdo && tieneDerecho) {
            return "NO_PERMITIDO_SOLO_DERECHO";
        }
        // Si llega aqui, el nodo es hoja o solo tiene subarbol izquierdo
        raiz = eliminarNodo(raiz, id);
        // Se devuelve el tipo de eliminacion realizada
        if (tieneIzquierdo && !tieneDerecho) {
            return "ELIMINADO_SOLO_IZQUIERDO";
        }
        return "ELIMINADO";
    }
    
    // Metodo recursivo para eliminar un nodo
    private Nodo eliminarNodo(Nodo actual, int id) {
        // Si el nodo es nulo, no hacer nada
        if (actual == null) {
            return null;
        }

        // Buscar en el subarbol izquierdo
        if (id < actual.data.getId()) {
            actual.izquierdo = eliminarNodo(actual.izquierdo, id);
        }
        // Buscar en el subarbol derecho
        else if (id > actual.data.getId()) {
            actual.derecho = eliminarNodo(actual.derecho, id);
        }
        // Si se encontro el nodo
        else {
            // Si es hoja, se elimina
            if (actual.izquierdo == null && actual.derecho == null) {
                return null;
            }

            // Si solo tiene subarbol izquierdo, sube
            if (actual.izquierdo != null && actual.derecho == null) {
                return actual.izquierdo;
            }
        }

        return actual;
    }
    
    // Metodos para recorrer el arbol
    
    // Metodo para recorrer en preorden
    public String preorden() {
        StringBuilder sb = new StringBuilder(); // Referencia: [2]
        preordenRec(raiz, sb);
        return sb.toString();
    }

    // Metodo recursivo para preorden
    private void preordenRec(Nodo actual, StringBuilder sb) {
        if (actual == null) {
            return;
        }
        // Formar string con los items del arbol
        sb.append(actual.data.getId()).append("-");
        // Recorrer subarbol izq
        preordenRec(actual.izquierdo, sb);
        // Recorrer subarbol der
        preordenRec(actual.derecho, sb);
    }

    // Metodo para recorrer en inorden
    public String inorden() {
        StringBuilder sb = new StringBuilder();
        inordenRec(raiz, sb);
        return sb.toString();
    }

    // Metodo recursivo para inorden
    private void inordenRec(Nodo actual, StringBuilder sb) {
        if (actual == null) {
            return;
        }
        // Recorrer subarbol izq
        inordenRec(actual.izquierdo, sb);
        // Formar string con los items del arbol
        sb.append(actual.data.getId()).append("-");
        inordenRec(actual.derecho, sb);
    }

    // Metodo para recorrer en postorden
    public String postorden() {
        StringBuilder sb = new StringBuilder();
        postordenRec(raiz, sb);
        return sb.toString();
    }

    // Metodo recursivo para postorden
    private void postordenRec(Nodo actual, StringBuilder sb) {
        if (actual == null) {
            return;
        }
        // Recorrer subarbol izq
        postordenRec(actual.izquierdo, sb);
        // Recorrer subarbol der
        postordenRec(actual.derecho, sb);
        // Formar string con los items del arbol
        sb.append(actual.data.getId()).append("-");
    }
}
