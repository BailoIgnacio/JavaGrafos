package Implementaciones;
import Interfaces.GrafosTDA;

public class GrafoEstatico implements GrafosTDA {
    int indice = 0;
    int[] grafo = new int[5];
    int[][] arista = new int[5][5];

    public void agregarArista(int principio, int fin, int pesoArista){
        int a = buscarIndice(principio);
        int b = buscarIndice(fin);
        arista[a][b] = pesoArista;   

    }
    public void agregarNodo(int valor){
        grafo[indice] = valor;
        indice ++;
    }
    public void eliminarNodo(int valor) {
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

    public void eliminarArista(int principio, int fin){
        int a = buscarIndice(principio);
        int b = buscarIndice(fin);
        if(a != -1 && b != -1){
            arista[a][b] = 0;
        }
    }

   public void djkstra(int principio, int fin) {
    int[] distancias = new int[5];
    boolean[] visitado = new boolean[5];
    int[] padre = new int[5];  // Para reconstruir el camino

    for (int i = 0; i < 5; i++) {
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

    for (int i = 0; i < 5; i++) {
        int nodoMinDist = -1;
        int minDist = 9999;

        for (int j = 0; j < 5; j++) {
            if (!visitado[j] && distancias[j] < minDist && grafo[j] != 0) {
                minDist = distancias[j];
                nodoMinDist = j;
            }
        }

        if (nodoMinDist == -1) break;

        visitado[nodoMinDist] = true;

        for (int j = 0; j < 5; j++) {
            if (arista[nodoMinDist][j] != 0 && grafo[j] != 0) {
                int nuevaDist = distancias[nodoMinDist] + arista[nodoMinDist][j];
                if (nuevaDist < distancias[j]) {
                    distancias[j] = nuevaDist;
                    padre[j] = nodoMinDist;  // Guardamos de dónde venimos
                }
            }
        }
    }

    // Reconstruir camino desde fin hasta principio
    if (distancias[indexFin] == 9999) {
        System.out.println("No existe un camino desde " + principio + " hasta " + fin);
        return;
    }

    System.out.println("El camino mas corto desde " + principio + " hasta " + fin + " es: ");

    int nodoActual = indexFin;
    String camino = "";

    while (nodoActual != -1) {
        camino = grafo[nodoActual] + (camino.isEmpty() ? "" : " -> ") + camino;
        nodoActual = padre[nodoActual];
    }

    System.out.println(camino);
    System.out.println("Costo total: " + distancias[indexFin]);
}

    public void imprimirGrafo() {
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

    private int buscarIndice(int valor){
        for(int i=0; i< indice; i++){
            if(grafo[i] == valor){
                return i;
            }
        }
        return -1;

    }

}
