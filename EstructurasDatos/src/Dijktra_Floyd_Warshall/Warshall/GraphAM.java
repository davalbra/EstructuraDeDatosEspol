/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijktra_Floyd_Warshall.Warshall;

import TDAGrafo.*;
import java.util.Comparator;

/**
 *
 * @author david
 */
public class GraphAM<V> {

    private V[] vertices;
    private int[][] adjacencyMatrix;
    private Comparator<V> cmp;
    private int effectiveSize = 0;
    private int capacity = 10;
    private boolean isDirected;

    public GraphAM(boolean isDirected, Comparator<V> cmp) {
        this.cmp = cmp;
        vertices = (V[]) new Object[capacity];
        adjacencyMatrix = new int[capacity][capacity];
        this.isDirected = isDirected;
        initAdjacencyMatrix();
    }

    public void printArreglo() {
        for (int i = 0; i < effectiveSize; i++) {
            System.out.print(" " + vertices[i]);
        }
        System.out.println("");
    }

    public boolean addVertex(V contenido) {
        if (contenido == null || findIndexOfVertex(contenido) != -1) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        vertices[effectiveSize++] = contenido;
        return true;
    }

    public boolean removeVertex(V contenido) {
        int index = findIndexOfVertex(contenido);
        for (int i = 0; i < effectiveSize; i++) {
            if (adjacencyMatrix[index][i] != -1) {
                borrarArco(contenido, vertices[i]);
            }
        }
        removeIndex(index);
        return false;
    }
    private void removeIndex(int index) {
        for (int i = index; i < effectiveSize - 1; i++) {
            vertices[i] = vertices[i + 1];
        }
        effectiveSize--;
    }
    public boolean borrarArco(V v1, V v2) {
        if (v1 == null || v2 == null) {
            return false;
        }
        int a = findIndexOfVertex(v1);
        int b = findIndexOfVertex(v2);
        if (a == -1 || b == -1) {
            return false;
        }
        if (adjacencyMatrix[a][b] == 1) {
            adjacencyMatrix[a][b] = -1;
            adjacencyMatrix[b][a] = -1;
        }
        return true;
    }

    public boolean isAdyacente(V v1, V v2) {
        if (v1 == null || v2 == null) {
            return false;
        }
        int a = findIndexOfVertex(v1);
        int b = findIndexOfVertex(v2);
        if (a == -1 || b == -1) {
            return false;
        }
        return adjacencyMatrix[a][b]==1;
    }

    public boolean connect(V v1, V v2) {
        if (v1 == null || v2 == null) {

            return false;
        }
        int index1 = findIndexOfVertex(v1);
        int index2 = findIndexOfVertex(v2);
        if (index1 == -1 && index2 == -1) {
            return false;
        }
        adjacencyMatrix[index1][index2] = 1;
        adjacencyMatrix[index2][index1] = 1;
        return true;
    }

    private void initAdjacencyMatrix() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                adjacencyMatrix[i][j] = -1;
            }
        }

    }

    private int findIndexOfVertex(V v) {
        for (int i = 0; i < effectiveSize; i++) {
            if (cmp.compare(vertices[i], v) == 0) {
                return i;
            }
        }
        return -1;
    }

    private boolean isFull() {
        return effectiveSize == capacity;
    }

    private void addCapacity() {
        this.capacity = this.capacity * 2;
        V[] tmp = (V[]) (new Object[capacity]);
        for (int i = 0; i < this.effectiveSize; i++) {
            tmp[i] = this.vertices[i];
        }
        this.vertices = tmp;

        int[][] tmpM = new int[capacity][capacity];
        for (int i = 0; i < effectiveSize; i++) {
            for (int j = 0; j < effectiveSize; j++) {
                tmpM[i][j] = this.adjacencyMatrix[i][j];
            }
        }
        adjacencyMatrix = tmpM;
    }

    public void printAdjacencyMatrix() {
        for (int i = 0; i < effectiveSize; i++) {
            for (int j = 0; j < effectiveSize; j++) {
                System.out.print(" " + adjacencyMatrix[i][j]);
            }
            System.out.println("");
        }
    }

    public V getV(int index) {
        return vertices[index];
    }

    public V[] getVertices() {
        return vertices;
    }

    public void setVertices(V[] vertices) {
        this.vertices = vertices;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public Comparator<V> getCmp() {
        return cmp;
    }

    public void setCmp(Comparator<V> cmp) {
        this.cmp = cmp;
    }

    public int getEffectiveSize() {
        return effectiveSize;
    }

    public void setEffectiveSize(int effectiveSize) {
        this.effectiveSize = effectiveSize;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isIsDirected() {
        return isDirected;
    }

    public void setIsDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

}
