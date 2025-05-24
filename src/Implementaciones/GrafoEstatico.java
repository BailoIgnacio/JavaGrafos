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
