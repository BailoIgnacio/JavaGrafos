package Metodos;
import Implementaciones.GrafoEstatico;
public class Principal{
public static void main(String[] args) {
    GrafoEstatico grafo = new GrafoEstatico();
    grafo.agregarNodo(1);
    grafo.agregarNodo(2);
    grafo.agregarNodo(3);
    grafo.agregarNodo(4);
    grafo.agregarNodo(5);
    grafo.agregarNodo(6);
    grafo.agregarNodo(7);
    grafo.agregarNodo(8);

    grafo.agregarArista(2, 1, 10);
    grafo.agregarArista(1, 3, 20);
    grafo.agregarArista(3, 4, 1);
    grafo.agregarArista(4, 5, 1);
    grafo.agregarArista(4, 6, 30);
    grafo.agregarArista(8, 1, 3);
    grafo.agregarArista(8, 6, 50);
    grafo.agregarArista(6, 8, 50);
    grafo.agregarArista(7, 8, 5);

    grafo.eliminarNodo(7);
    grafo.eliminarArista(8, 1);
    grafo.eliminarArista(8, 6);
    grafo.imprimirGrafo();
    grafo.djkstra(1);
}

}