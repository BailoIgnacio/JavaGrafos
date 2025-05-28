package Implementaciones;
import Interfaces.GrafosTDA;

public class GrafoEstatico implements GrafosTDA {
    int indice = 0;
    int[] grafo = new int[8];
    int[][] arista = new int[8][8];

    public void agregarArista(int principio, int fin, int pesoArista){  // O(n)
        int a = buscarIndice(principio);
        int b = buscarIndice(fin);
        arista[a][b] = pesoArista;
        if(arista[a][b] < 0){
            System.out.println("El peso de la arista no puede ser negativo");
            System.out.println(grafo[10]);
        } 

    }
    public void agregarNodo(int valor){ // 0(1)
        grafo[indice] = valor;
        indice ++;
    }
    public void eliminarNodo(int valor) { // O(n**2)
        for (int i = 0; i < indice; i++) {
            if (grafo[i] == valor) {
                grafo[i] = 0;
                for (int j = 0; j < indice; j++) {
                    arista[i][j] = 0;
                    arista[j][i] = 0;
                }
              
                for (int k = i; k < indice - 1; k++) {
                    grafo[k] = grafo[k + 1];
                    for (int m = 0; m < indice; m++) {
                        arista[k][m] = arista[k + 1][m];
                        arista[m][k] = arista[m][k + 1];
                    }
                }
                
                grafo[indice - 1] = 0;
                for (int n = 0; n < indice; n++) {
                    arista[indice - 1][n] = 0;
                    arista[n][indice - 1] = 0;
                }
                indice--; 
                break; 
            }
        }
    }

    public void eliminarArista(int principio, int fin){ // O(n)
        int a = buscarIndice(principio);
        int b = buscarIndice(fin);
        if(a != -1 && b != -1){
            arista[a][b] = 0;
            arista[b][a] = 0;
        }
    }

   public void djkstra(int principio, int fin) { // O(n**2)
    int[] distancias = new int[8];
    boolean[] visitado = new boolean[8];
    int[] padre = new int[8];

    for (int i = 0; i < 8; i++) {
        distancias[i] = 9999;
        visitado[i] = false;
        padre[i] = -1;
    }

    int indexPrincipio = buscarIndice(principio);
    int indexFin = buscarIndice(fin);

    if (indexPrincipio == -1 || indexFin == -1) {
        System.out.println("Uno de los nodos no existen en el grafo.");
        return; 
    }

    distancias[indexPrincipio] = 0;

    for (int i = 0; i < 8; i++) {
        int indiceNodo = -1;
        int minPeso = 9999;

        for (int j = 0; j < 8; j++) {
            if (!visitado[j] && distancias[j] < minPeso && grafo[j] != 0) {
                minPeso = distancias[j];
                indiceNodo = j;
            }
        }

        if (indiceNodo == -1) {break;}
  

        visitado[indiceNodo] = true; 

        for (int j = 0; j < 8; j++) {
            if (arista[indiceNodo][j] != 0 && grafo[j] != 0) {  
                int nuevaDist = distancias[indiceNodo] + arista[indiceNodo][j];
                if (nuevaDist < distancias[j]) { 
                    distancias[j] = nuevaDist;  
                    padre[j] = indiceNodo;  
                }
            }
        }
    }

    if (distancias[indexFin] == 9999) {
        System.out.println("No existe un camino desde " + principio + " hasta " + fin);
        return;
    }

    System.out.println("El camino mas corto desde " + principio + " hasta " + fin + " es: ");

    int nodoActual = indexFin;
    String camino = "";
    Boolean a = true;

    while (nodoActual != -1) {
        if (a){
            camino = camino + grafo[nodoActual];
            a = false;

        } else {
            camino = grafo[nodoActual] + " --> "+ camino;
        }
        nodoActual = padre[nodoActual];
    }

    System.out.println(camino);
    System.out.println("Costo total: " + distancias[indexFin]);
}

    public void imprimirGrafo() { // O(n**2)
        System.out.println("Nodos:");
        for (int i = 0; i < indice; i++) {
            if (grafo[i] != 0) {
                System.out.println("Nodo " + i + ": " + grafo[i]);
                
            }
        }
    
        System.out.println("\nAristas:");
        for (int i = 0; i < indice; i++) {
            if (grafo[i] != 0) {
                for (int j = 0; j < indice; j++) {
                    if (arista[i][j] != 0) {
                        System.out.println("Desde " + grafo[i] + " hasta " + grafo[j] + " con peso " + arista[i][j]);
                    }
                }
            }
        }
    }

    private int buscarIndice(int valor){ // O(n)
        for(int i=0; i< indice; i++){
            if(grafo[i] == valor){
                return i;
            }
        }
        return -1;

    }

}
