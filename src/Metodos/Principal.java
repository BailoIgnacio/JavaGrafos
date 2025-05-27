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

    grafo.agregarArista(1, 2, 10);
    grafo.agregarArista(1, 3, 13);
    grafo.agregarArista(2, 4, 5);
    grafo.agregarArista(2, 5, 100);
    grafo.agregarArista(2, 3, 5);
    grafo.agregarArista(3, 4, 1);
    grafo.agregarArista(4, 5, 50);
    grafo.agregarArista(5, 2, 100);

    grafo.eliminarNodo(3);
    grafo.imprimirGrafo();
    grafo.djkstra(1, 5);
}

}