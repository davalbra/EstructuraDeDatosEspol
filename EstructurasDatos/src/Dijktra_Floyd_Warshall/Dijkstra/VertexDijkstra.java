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
public class VertexDijkstra<V,E> {
    private V content;
    private LinkedList<EdgeDijkstra<E,V>> edges;
    private boolean isVisited;
    private Integer distanciaAcumulativa=0;
    public VertexDijkstra(V content) {
        this.content = content;
        edges =new LinkedList<>();
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public LinkedList<EdgeDijkstra<E, V>> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<EdgeDijkstra<E, V>> edges) {
        this.edges = edges;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public Integer getDistanciaAcumulativa() {
        return distanciaAcumulativa;
    }

    public void setDistanciaAcumulativa(Integer distanciaAcumulativa) {
        this.distanciaAcumulativa = distanciaAcumulativa;
    }
    public void setDistanciaAcumulativaCreciente(Integer distanciaAcumulativa) {
        this.distanciaAcumulativa +=distanciaAcumulativa;
    }
    
    @Override
    public String toString() {
        return "VertexDijkstra{" + "content=" + content + ", edges=" + edges + ", isVisited=" + isVisited + '}';
    }
    
    
}
