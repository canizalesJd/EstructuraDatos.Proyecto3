package Controlador;
import Modelo.ArbolBinarioBusqueda;
import Modelo.Tarjeta;
import java.util.InputMismatchException;

/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 3 - Estructura de Datos
 * Descripción: Clase Producto
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Abril 2026
 *
 */
public class Controlador {
    // Propiedades de la clase
    private final ArbolBinarioBusqueda arbol;
    
    // Constructor
    public Controlador() {
        this.arbol = new ArbolBinarioBusqueda();
    }
    
    // Metodo para agregar una tarjeta
    public void agregarTarjeta(String idTexto, String descripcion, String categoria) {
        // Validar que los campos no esten vacios
        if (idTexto == null || idTexto.trim().isEmpty()) {
            throw new IllegalArgumentException("El Id es obligatorio.");
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion es obligatoria.");
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoria es obligatoria.");
        }

        // Convertir el id a numero
        int id;
        try {
            id = Integer.parseInt(idTexto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El Id debe ser un numero entero valido.");
        }

        // Verificar que el id sea positivo
        if (id <= 0) {
            throw new IllegalArgumentException("El Id debe ser un numero entero positivo.");
        }

        // Crear la tarjeta
        Tarjeta tarjeta = new Tarjeta(id, descripcion.trim(), categoria.trim());

        // Intentar insertar en el arbol
        boolean insertado = arbol.insertar(tarjeta);

        // Si no se inserto, mostrar error
        if (!insertado) {
            throw new IllegalArgumentException("No se pudo insertar la tarjeta. Verifique que el Id no exista.");
        }
    }
    
    // Metodo para buscar una tarjeta
    public String buscarTarjeta(String idTexto) {
        // Validar que el id no este vacio
        if (idTexto == null || idTexto.trim().isEmpty()) {
            throw new IllegalArgumentException("El Id es obligatorio.");
        }

        // Convertir el id a numero
        int id;
        try {
            id = Integer.parseInt(idTexto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El Id debe ser un numero entero valido.");
        }

        // Verificar que el id sea positivo
        if (id <= 0) {
            throw new IllegalArgumentException("El Id debe ser un numero entero positivo.");
        }

        // Buscar la tarjeta
        Tarjeta encontrada = arbol.buscar(id);

        // Verificar si existe
        if (encontrada == null) {
            return "No se encontro una tarjeta con ese Id.";
        }

        // Retornar los datos completos
        return "Id: " + encontrada.getId()
                + " | Descripcion: " + encontrada.getDescripcion()
                + " | Categoria: " + encontrada.getCategoria();
    }
    
    // Metodo para eliminar una tarjeta
    public String eliminarTarjeta(String idTexto) {
        // Validar que el id no este vacio
        if (idTexto == null || idTexto.trim().isEmpty()) {
            throw new IllegalArgumentException("El Id es obligatorio.");
        }

        // Convertir el id a numero
        int id;
        try {
            id = Integer.parseInt(idTexto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El Id debe ser un numero entero valido.");
        }

        // Verificar que el id sea positivo
        if (id <= 0) {
            throw new IllegalArgumentException("El Id debe ser un numero entero positivo.");
        }

        // Solicitar eliminacion al arbol
        String resultado = arbol.eliminar(id);

        // Traducir el resultado a mensaje amigable
        switch (resultado) {
            case "ELIMINADO":
                return "La tarjeta se elimino correctamente.";
            case "ELIMINADO_SOLO_IZQUIERDO":
                return "La tarjeta se elimino y su subarbol izquierdo ocupo su lugar.";
            case "NO_ENCONTRADO":
                return "No se encontro una tarjeta con ese Id.";
            case "NO_PERMITIDO_CIVILES":
                return "No se permite eliminar tarjetas de la categoria Civiles.";
            case "NO_PERMITIDO_DOS_HIJOS":
                return "No se permite eliminar un nodo con dos hijos.";
            case "NO_PERMITIDO_SOLO_DERECHO":
                return "No se permite eliminar un nodo que solo tiene subarbol derecho.";
            case "ID_INVALIDO":
                throw new IllegalArgumentException("El Id debe ser un numero entero positivo.");
            default:
                return "No se pudo eliminar la tarjeta.";
        }
    }
    
    // Metodo para obtener el recorrido preorden
    public String obtenerPreorden() {
        return arbol.preorden();
    }

    // Metodo para obtener el recorrido inorden
    public String obtenerInorden() {
        return arbol.inorden();
    }

    // Metodo para obtener el recorrido postorden
    public String obtenerPostorden() {
        return arbol.postorden();
    }

    // Metodo para contar tarjetas de super heroes y super villanos
    public String contarCategoriasSuper() {
        return "Cantidad de tarjetas de Súper héroes o Súper villanos: "
                + arbol.contarCategoriasSuper();
    }

    // Metodo para listar hojas de frases iconicas
    public String listarFrasesIconicas() {
        String texto = arbol.listarFrasesIconicas();

        if (texto == null || texto.trim().isEmpty()) {
            return "No hay tarjetas de la categoria Frases icónicas en nodos hoja.";
        }

        return texto;
    }
    
    // Metodo para obtener la tarjeta con menor y mayor id
    public String obtenerMayorYMenor() {
        return arbol.obtenerMayorYMenor();
    }
    
    // Metodo para obtener la consulta adicional'
    public String obtenerConsultaAdicional() {
        StringBuilder sb = new StringBuilder();

        sb.append("Total de tarjetas de Súper héroes o Súper villanos: ")
          .append(arbol.contarCategoriasSuper())
          .append("\n\n");

        sb.append("Tarjetas de Frases icónicas en nodos hoja:\n");
        String frases = arbol.listarFrasesIconicas();
        if (frases == null || frases.trim().isEmpty()) {
            sb.append("No hay tarjetas registradas.");
        } else {
            sb.append(frases);
        }

        sb.append("\n\n");
        sb.append(arbol.obtenerMayorYMenor());

        return sb.toString();
    }
}
