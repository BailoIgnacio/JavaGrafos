package Metodos;
import Implementaciones.GrafoEstatico;
public class Principal{
public static void main(String[] args) {
    GrafoEstatico grafo = new GrafoEstatico();
    grafo.agregarNodo(4);
    grafo.agregarNodo(1);
    grafo.agregarNodo(50);
    grafo.agregarNodo(7);
    grafo.agregarNodo(100);

    grafo.agregarArista(4, 1, 10);
    grafo.agregarArista(50, 4, 7);
    grafo.agregarArista(100, 7, 90);
    grafo.agregarArista(50, 1, 3);
    grafo.agregarArista(7, 4, 43);
    grafo.agregarArista(7, 1, 20);
    grafo.agregarArista(1, 7, 200);

    grafo.eliminarArista(50,1);
    grafo.eliminarNodo(4);
    grafo.imprimirGrafo();
}

}