/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijktra_Floyd_Warshall.Dijkstra;

import TDADinamicGrafo.*;
import java.util.Comparator;
import java.util.Iterator;
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

    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    public boolean addVertex(V content) {
        if (content == null || buscarVertice(content) != null) {
            return false;
        }
        Vertex<V, E> newVertex = new Vertex<>(content);
        this.vertices.add(newVertex);
        return true;
    }

    public boolean conect(V content1, V content2, int weight, E data) {
        if (content1 == null || content2 == null) {
            return false;
        }
        Vertex<V, E> vertex1 = buscarVertice(content1);
        Vertex<V, E> vertex2 = buscarVertice(content2);
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        Edge<E, V> edg = new Edge<>(vertex1, vertex2, weight, data);
        if (vertex1.getEdges().contains(edg)) {
            return false;
        }
        vertex1.getEdges().add(edg);
        if (!directed) {
            vertex2.getEdges().add(new Edge<>(vertex2, vertex1, weight, data));

        }
        return true;

    }

    private Vertex<V, E> buscarVertice(V content) {
        for (Vertex<V, E> v : vertices) {
            if (cmp.compare(content, v.getContent()) == 0) {
                return v;
            }
        }
        return null;

    }

    public int getInDegree(V dato) {
        if (dato == null) {
            return -1;
        }
        Vertex<V, E> v = buscarVertice(dato);
        if (v == null) {
            return -1;
        }
        return v.getEdges().size();
    }

    public int getOutDegree(V dato) {
        Vertex<V, E> v = buscarVertice(dato);
        if (v != null) {
            return -1;
        }
        int contador = 0;
        for (Vertex<V, E> vertex : vertices) {
            for (Edge<E, V> edge : vertex.getEdges()) {
                if (cmp.compare(edge.getTarget().getContent(), v.getContent()) == 0) {
                    contador++;
                }

            }
        }
        return contador;
    }

    public boolean removerVertice(V dato) {
        if (dato == null) {
            return false;
        }
        Vertex<V, E> v = buscarVertice(dato);
        if (v == null) {
            return false;
        }
        for (Vertex<V, E> verte : vertices) {
            Iterator<Edge<E, V>> it = verte.getEdges().iterator();
            while (it.hasNext()) {
                Edge<E, V> edge = it.next();
                if (cmp.compare(edge.getTarget().getContent(), v.getContent()) == 0 || cmp.compare(edge.getSource().getContent(), v.getContent()) == 0) {
                    it.remove();
                }
            }
        }
        v.setContent(null);
        v.setEdges(null);
        vertices.remove(v);
        return true;
    }

    public boolean removerarco(V origen, V destino) {
        if (origen == null || destino == null) {
            return false;
        }

        Vertex<V, E> vo = buscarVertice(origen);
        Vertex<V, E> vd = buscarVertice(destino);

        if (vo == null || vd == null) {
            return false;
        }
        Iterator<Edge<E, V>> it = vo.getEdges().iterator();
        while (it.hasNext()) {
            Edge<E, V> edge = it.next();
            if (cmp.compare(edge.getTarget().getContent(), origen) == 0) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    private boolean containsVertex(Vertex<V, E> v) {
        for (Vertex<V, E> vertice : vertices) {
            if (cmp.compare(v.getContent(), vertice.getContent()) == 0) {
                return true;
            }

        }
        return false;
    }

    public List<Vertex<V, E>> recorrerEnAnchura(Vertex<V, E> partida) {
        if (partida != null) {
            return null;
        }
        if (this.buscarVertice(partida.getContent()) == null) {
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
        if (partida != null) {
            return null;
        }
        if (this.buscarVertice(partida.getContent()) == null) {
            return null;
        }
        List<Vertex<V, E>> recorrido = new LinkedList<>();
        Stack<Vertex<V, E>> s = new Stack<>();
        s.push(partida);
        partida.setIsVisited(true);
        while (!s.isEmpty()) {
            Vertex<V, E> v = s.pop();
            recorrido.add(v);
            List<Edge<E, V>> edges = v.getEdges();
            for (Edge<E, V> e : edges) {
                Vertex<V, E> target = e.getTarget();
                if (!target.isIsVisited()) {
                    s.push(target);
                    target.setIsVisited(true);
                }
            }
        }

        return recorrido;

    }

    public List<List<Vertex<V, E>>> getConnectedComponents() {
        this.setearNovisitado();
        List<List<Vertex<V, E>>> result = new LinkedList<>();
        for (Vertex<V, E> v : vertices) {
            if (!v.isIsVisited()) {
                result.add(recorreEnProfundidad(v));
            }
        }
        return result;
    }
    public void setearNovisitado(){
        for (Vertex<V, E> vertice : vertices) {
            vertice.setIsVisited(false);
        }
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder();
        v.append(" v={");

        StringBuilder a = new StringBuilder();
        a.append(" a={");

        for (Vertex<V, E> vertex : vertices) {
            v.append(vertex.getContent());
            v.append(", ");
            for (Edge<E, V> e : vertex.getEdges()) {
                a.append(e.toString());
                a.append(", ");
            }
        }
        if (!vertices.isEmpty()) {
            v.delete(v.length() - 2, v.length());
        }
        if (a.length() > 4) {
            a.delete(a.length() - 2, a.length());
        }

        v.append("}");
        a.append("}");
        return v.toString() + "\n" + a.toString();
    }

}
