package Implementaciones;
import Interfaces.GrafosTDA;

public class GrafoEstatico implements GrafosTDA {
    int indice = 0;
    int[] nodos = new int[8];
    int[][] arista = new int[8][8];

    public void agregarArista(int principio, int fin, int pesoArista){  // O(n)
        int a = buscarIndice(principio);
        int b = buscarIndice(fin);
        arista[a][b] = pesoArista;
    

    }
    public void agregarNodo(int valor){ // 0(1)
        nodos[indice] = valor;
        indice ++;
    }
    public void eliminarNodo(int valor) { // O(n**2)
        for (int i = 0; i < indice; i++) {
            if (nodos[i] == valor) {
                nodos[i] = 0;
                for (int j = 0; j < indice; j++) {
                    arista[i][j] = 0;
                    arista[j][i] = 0;
                }
              
                for (int k = i; k < indice - 1; k++) {
                    nodos[k] = nodos[k + 1];
                    for (int m = 0; m < indice; m++) {
                        arista[k][m] = arista[k + 1][m];
                        arista[m][k] = arista[m][k + 1];
                    }
                }
                
                nodos[indice - 1] = 0;
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

   public void djkstra(int origen) { // O(n**2)
    int[] distancias = new int[8];
    boolean[] visitado = new boolean[8];
    int[] padre = new int[8];

    for (int i = 0; i < 8; i++) {
        distancias[i] = 9999;
        visitado[i] = false;
        padre[i] = -1;
    }

    int indexOrigen = buscarIndice(origen);

    if (indexOrigen == -1) {
        System.out.println("El nodo origen no existe en el grafo.");
        return;
    }

    distancias[indexOrigen] = 0;

    for (int i = 0; i < 8; i++) {
        int indiceNodo = -1;
        int minPeso = 9999;

        for (int j = 0; j < 8; j++) {
            if (!visitado[j] && distancias[j] < minPeso && nodos[j] != 0) {
                minPeso = distancias[j];
                indiceNodo = j;
            }
        }

        if (indiceNodo == -1) break;

        visitado[indiceNodo] = true;

        for (int j = 0; j < 8; j++) {
            if (arista[indiceNodo][j] != 0 && nodos[j] != 0) {
                int nuevaDist = distancias[indiceNodo] + arista[indiceNodo][j];
                if (nuevaDist < distancias[j]) {
                    distancias[j] = nuevaDist;
                    padre[j] = indiceNodo;
                }
            }
        }
    }

    System.out.println("Distancias desde el nodo " + origen + " a los demás nodos:");
    for (int i = 0; i < indice; i++) {
        if (nodos[i] != 0 && i != indexOrigen) {
            if (distancias[i] == 9999) {
                System.out.println("No hay camino hacia " + nodos[i]);
            } else {
                System.out.print("Camino a " + nodos[i] + ": ");
                imprimirCamino(indexOrigen, i, padre);
                System.out.println(" (Costo: " + distancias[i] + ")");
            }
        }
    }
}
    public void imprimirGrafo() { // O(n**2)
        System.out.println("Nodos:");
        for (int i = 0; i < indice; i++) {
            if (nodos[i] != 0) {
                System.out.println("Nodo " + i + ": " + nodos[i]);
                
            }
        }
    
        System.out.println("\nAristas:");
        for (int i = 0; i < indice; i++) {
            if (nodos[i] != 0) {
                for (int j = 0; j < indice; j++) {
                    if (arista[i][j] != 0) {
                        System.out.println("Desde " + nodos[i] + " hasta " + nodos[j] + " con peso " + arista[i][j]);
                    }
                }
            }
        }
    }

    private int buscarIndice(int valor){ // O(n)
        for(int i=0; i< indice; i++){
            if(nodos[i] == valor){
                return i;
            }
        }
        return -1;

    }

    private void imprimirCamino(int principio, int fin, int[] padre) { // O(n)
    if (fin == principio) {
        System.out.print(nodos[principio]);
    } else if (padre[fin] == -1) {
        System.out.print("No hay camino");
    } else {
        imprimirCamino(principio, padre[fin], padre);
        System.out.print(" --> " + nodos[fin]);
    }
}

}
