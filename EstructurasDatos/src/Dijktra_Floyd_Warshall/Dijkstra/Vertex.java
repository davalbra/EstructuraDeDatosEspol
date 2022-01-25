/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijktra_Floyd_Warshall.Dijkstra;
import TDADinamicGrafo.*;
import java.util.LinkedList;

/**
 *
 * @author david
 */
class Vertex<V,E> {
    private V content;
    private LinkedList<Edge<E,V>> edges;
    private boolean isVisited;

    public Vertex(V content) {
        this.content = content;
        edges =new LinkedList<>();
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public LinkedList<Edge<E, V>> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Edge<E, V>> edges) {
        this.edges = edges;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
    
    
}
