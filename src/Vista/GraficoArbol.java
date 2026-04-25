package Vista;

/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 3 - Estructura de Datos
 * Descripción: Clase Producto
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Abril 2026
 * Referencia: https://www.cs.columbia.edu/~allen/S14/NOTES/DisplaySimpleTree.java
 */


import Modelo.Nodo;
import Modelo.Tarjeta;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

// Panel para dibujar el arbol binario de busqueda.
public class GraficoArbol extends JPanel {
    // Nodo raiz del arbol
    private Nodo raiz;

    // Separacion horizontal inicial
    private static final int ESPACIO_X_INICIAL = 140;

    // Separacion vertical entre niveles
    private static final int ESPACIO_Y = 70;

    // Tamaño visual de cada nodo
    private static final int RADIO = 22;

    // Margen superior
    private static final int MARGEN_SUPERIOR = 40;

    // Constructor
    public GraficoArbol() {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setPreferredSize(new Dimension(900, 500));
    }

    // Metodo para asignar la raiz del arbol
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Verificar si hay arbol para dibujar
        if (raiz == null) {
            g.setColor(Color.GRAY);
            g.drawString("No hay tarjetas para graficar.", 20, 25);
            return;
        }

        // Configuracion del dibujo
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("SansSerif", Font.BOLD, 12));

        // Dibujar el arbol desde la raiz
        int centroX = getWidth() / 4;
        dibujarNodo(g2, raiz, centroX, MARGEN_SUPERIOR, ESPACIO_X_INICIAL);

        g2.dispose();
    }

    // Metodo recursivo para dibujar nodos y conexiones
    private void dibujarNodo(Graphics2D g2, Nodo actual, int x, int y, int espacioX) {
        if (actual == null) {
            return;
        }

        // Calcular posiciones de los hijos
        int hijoY = y + ESPACIO_Y;
        int hijoIzqX = x - espacioX;
        int hijoDerX = x + espacioX;

        // Dibujar linea al hijo izquierdo
        if (actual.izquierdo != null) {
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(x, y, hijoIzqX, hijoY);
            dibujarNodo(g2, actual.izquierdo, hijoIzqX, hijoY, Math.max(35, espacioX / 2));
        }

        // Dibujar linea al hijo derecho
        if (actual.derecho != null) {
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(x, y, hijoDerX, hijoY);
            dibujarNodo(g2, actual.derecho, hijoDerX, hijoY, Math.max(35, espacioX / 2));
        }

        // Dibujar nodo
        g2.setColor(new Color(240, 240, 255));
        g2.fillOval(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2);

        g2.setColor(Color.BLACK);
        g2.drawOval(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2);

        // Dibujar el id dentro del nodo
        Tarjeta tarjeta = actual.data;
        String texto = String.valueOf(tarjeta.getId());
        int anchoTexto = g2.getFontMetrics().stringWidth(texto);
        int altoTexto = g2.getFontMetrics().getAscent();
        g2.drawString(texto, x - (anchoTexto / 2), y + (altoTexto / 3));
    }
}