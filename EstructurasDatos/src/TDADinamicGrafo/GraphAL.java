/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDADinamicGrafo;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author david
 */
public class GraphAL<V, E> {

    private LinkedList<Vertex<V, E>> vertices;
    private boolean directed;
    private Comparator<V> cmp;

    public GraphAL(boolean directed, Comparator<V> cmp) {
        this.vertices = new LinkedList<>();
        this.directed = directed;
        this.cmp = cmp;
    }

    public boolean addVertex(V content) {
        if (content == null || findVertex(content) != null) {
            return false;
        }
        Vertex<V, E> newVertex = new Vertex<>(content);
        this.vertices.add(newVertex);
        return true;
    }

    public boolean conect(V content1, V content2, int weight, E data) {
        if (content1 == null && content2 == null || (findVertex(content1) == null && findVertex(content2) == null)) {
            return false;
        }
        Vertex<V, E> vertex1 = findVertex(content1);
        Vertex<V, E> vertex2 = findVertex(content2);
        vertex1.getEdges().add(new Edge<>(vertex1, vertex2, weight, data));
        if (!directed) {
            vertex2.getEdges().add(new Edge<>(vertex2, vertex1, weight, data));

        }
        return true;

    }

    private Vertex<V, E> findVertex(V content) {
        for (Vertex<V, E> v : vertices) {
            if (cmp.compare(content, v.getContent()) == 0) {
                return v;
            }
        }
        return null;

    }

    public List<Vertex<V, E>> recorrerEnAnchura(Vertex<V, E> partida) {
        if (partida != null) {
            return null;
        }
        if (this.findVertex(partida.getContent()) == null) {
            return null;
        }
        List<Vertex<V, E>> recorrido = new LinkedList<>();
        Queue<Vertex<V, E>> q = new LinkedList<>();
        q.add(partida);
        partida.setIsVisited(true);
        while (!q.isEmpty()) {
            Vertex<V, E> v = q.remove();
            recorrido.add(v);
            List<Edge<E, V>> edges = v.getEdges();
            for (Edge<E, V> e : edges) {
                Vertex<V, E> target = e.getTarget();
                if (!target.isIsVisited()) {
                    q.add(target);
                    target.setIsVisited(true);
                }
            }
        }

        return recorrido;
    }

    public List<Vertex<V, E>> recorreEnProfundidad(Vertex<V, E> partida) {
        List<Vertex<V, E>> recorrido = new LinkedList<>();
        Stack<Vertex<V, E>> s = new Stack<>();
        s.push(partida);
        partida.setIsVisited(true);
        while (!s.isEmpty()) {
            Vertex<V, E> v = s.pop();
            List<Edge<E, V>> edges = v.getEdges();
            for (Edge<E, V> e : edges) {
                Vertex<V, E> target = e.getTarget();
                if (!target.isIsVisited()) {
                    s.add(target);
                    target.setIsVisited(true);
                }
            }
        }

        return recorrido;

    }

    public List<List<Vertex<V, E>>> getConnectedComponents() {
        List<List<Vertex<V, E>>> result = new LinkedList<>();
        for (Vertex<V, E> v : vertices) {
            if (!v.isIsVisited()) {
                result.add(recorreEnProfundidad(v));
            }
        }
        return result;
    }

}
