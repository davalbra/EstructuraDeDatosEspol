/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijktra_Floyd_Warshall.Dijkstra;

import TDADinamicGrafo.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author david
 */
public class GraphALDijkstra<V, E> {

    private LinkedList<VertexDijkstra<V, E>> vertices;
    private boolean directed;
    private Comparator<V> cmp;

    public GraphALDijkstra(boolean directed, Comparator<V> cmp) {
        this.vertices = new LinkedList<>();
        this.directed = directed;
        this.cmp = cmp;
    }

    //Dijkstra
    public Map<V, Integer> DijkstraAlgorith(V contentido) {
        setearNovisitado();
        if (contentido == null) {
            return null;
        }
        VertexDijkstra<V, E> verte = buscarVertice(contentido);
        if (verte == null) {
            return null;
        }
        Map<V, Integer> mapa = new HashMap<>();
        Iterator<EdgeDijkstra<E, V>> itE = verte.getEdges().iterator();
        PriorityQueue<EdgeDijkstra<E, V>> pqv = new PriorityQueue<>((a, b) -> {
            return a.getWeight() - b.getWeight();
        });
        while (itE.hasNext()) {
            pqv.add(itE.next());
        }
        while (!pqv.isEmpty()) {
            EdgeDijkstra<E, V> e = pqv.poll();
            System.out.println("ojito con esto: " + e.getWeight());
            if (mapa.containsKey(e.getTarget().getContent())) {
                if (e.getWeight() + e.getSource().getDistanciaAcumulativa() < mapa.get(e.getTarget().getContent())) {
                    VertexDijkstra<V, E> target = e.getTarget();
                    target.setDistanciaAcumulativa(e.getWeight() + e.getSource().getDistanciaAcumulativa());
                    System.out.println("mira la acomulacion: " + target.getContent() + " peso: " + target.getDistanciaAcumulativa() + " cosa previa en el mapa:" + mapa.get(e.getTarget().getContent()));
                    mapa.replace(target.getContent(), target.getDistanciaAcumulativa());

                }
            } else {
                VertexDijkstra<V, E> target = e.getTarget();
                target.setDistanciaAcumulativa(e.getSource().getDistanciaAcumulativa() + e.getWeight());
                System.out.println("source: " + e.getSource().getContent() + " valorAcomulado: " + e.getSource().getDistanciaAcumulativa() + " valor: " + target.getContent() + " valorAcomulado: " + e.getTarget().getDistanciaAcumulativa());
                mapa.put(target.getContent(), target.getDistanciaAcumulativa());
                e.getSource().setIsVisited(true);
                for (EdgeDijkstra<E, V> edg : e.getTarget().getEdges()) {
                    if (!edg.getSource().isIsVisited()) {
                        pqv.add(edg);
                    }
                }
            }
        }

        mapa.put(contentido, 0);
        return mapa;
    }
    //Dijkstra

    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    public boolean addVertex(V content) {
        if (content == null || buscarVertice(content) != null) {
            return false;
        }
        VertexDijkstra<V, E> newVertex = new VertexDijkstra<>(content);
        this.vertices.add(newVertex);
        return true;
    }

    public boolean conect(V content1, V content2, int weight, E data) {
        if (content1 == null || content2 == null) {
            return false;
        }
        VertexDijkstra<V, E> vertex1 = buscarVertice(content1);
        VertexDijkstra<V, E> vertex2 = buscarVertice(content2);
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        EdgeDijkstra<E, V> edg = new EdgeDijkstra<>(vertex1, vertex2, weight, data);
        if (vertex1.getEdges().contains(edg)) {
            return false;
        }
        vertex1.getEdges().add(edg);
        if (!directed) {
            vertex2.getEdges().add(new EdgeDijkstra<>(vertex2, vertex1, weight, data));

        }
        return true;

    }

    private VertexDijkstra<V, E> buscarVertice(V content) {
        for (VertexDijkstra<V, E> v : vertices) {
            if (cmp.compare(content, v.getContent()) == 0) {
                return v;
            }
        }
        return null;

    }

    public int getOutDegree(V dato) {
        if (dato == null) {
            return -1;
        }
        VertexDijkstra<V, E> v = buscarVertice(dato);
        if (v == null) {
            return -1;
        }
        return v.getEdges().size();
    }

    public int getInDegree(V dato) {
        VertexDijkstra<V, E> v = buscarVertice(dato);
        if (v == null) {
            return -1;
        }
        int contador = 0;
        for (VertexDijkstra<V, E> vertex : vertices) {
            for (EdgeDijkstra<E, V> edge : vertex.getEdges()) {
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
        VertexDijkstra<V, E> v = buscarVertice(dato);
        if (v == null) {
            return false;
        }
        for (VertexDijkstra<V, E> verte : vertices) {
            Iterator<EdgeDijkstra<E, V>> it = verte.getEdges().iterator();
            while (it.hasNext()) {
                EdgeDijkstra<E, V> edge = it.next();
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

        VertexDijkstra<V, E> vo = buscarVertice(origen);
        VertexDijkstra<V, E> vd = buscarVertice(destino);

        if (vo == null || vd == null) {
            return false;
        }
        Iterator<EdgeDijkstra<E, V>> it = vo.getEdges().iterator();
        while (it.hasNext()) {
            EdgeDijkstra<E, V> edge = it.next();
            if (cmp.compare(edge.getTarget().getContent(), origen) == 0) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    private boolean containsVertex(VertexDijkstra<V, E> v) {
        for (VertexDijkstra<V, E> vertice : vertices) {
            if (cmp.compare(v.getContent(), vertice.getContent()) == 0) {
                return true;
            }

        }
        return false;
    }

    public List<VertexDijkstra<V, E>> recorrerEnAnchuraByV(V partida) {
        if (partida == null) {
            return null;
        }
        VertexDijkstra<V, E> v = buscarVertice(partida);
        if (v == null) {
            return null;
        }
        return recorrerEnAnchura(v);
    }

    public List<VertexDijkstra<V, E>> recorrerEnAnchura(VertexDijkstra<V, E> partida) {
        setearNovisitado();
        if (partida == null) {
            return null;
        }
        if (this.buscarVertice(partida.getContent()) == null) {
            return null;
        }
        List<VertexDijkstra<V, E>> recorrido = new LinkedList<>();
        Queue<VertexDijkstra<V, E>> q = new LinkedList<>();
        q.add(partida);
        partida.setIsVisited(true);
        while (!q.isEmpty()) {
            VertexDijkstra<V, E> v = q.remove();
            recorrido.add(v);
            List<EdgeDijkstra<E, V>> edges = v.getEdges();
            for (EdgeDijkstra<E, V> e : edges) {
                VertexDijkstra<V, E> target = e.getTarget();
                if (!target.isIsVisited()) {
                    q.add(target);
                    target.setIsVisited(true);
                }
            }
        }

        return recorrido;
    }

    public List<VertexDijkstra<V, E>> recorrerEnProfundidadByV(V partida) {
        if (partida == null) {
            return null;
        }
        VertexDijkstra<V, E> v = buscarVertice(partida);
        if (v == null) {
            return null;
        }
        return recorreEnProfundidad(v);
    }

    public List<VertexDijkstra<V, E>> recorreEnProfundidad(VertexDijkstra<V, E> partida) {
        setearNovisitado();
        if (partida == null) {
            return null;
        }
        if (this.buscarVertice(partida.getContent()) == null) {
            return null;
        }
        List<VertexDijkstra<V, E>> recorrido = new LinkedList<>();
        Stack<VertexDijkstra<V, E>> s = new Stack<>();
        s.push(partida);
        partida.setIsVisited(true);
        while (!s.isEmpty()) {
            VertexDijkstra<V, E> v = s.pop();
            recorrido.add(v);
            List<EdgeDijkstra<E, V>> edges = v.getEdges();
            for (EdgeDijkstra<E, V> e : edges) {
                VertexDijkstra<V, E> target = e.getTarget();
                if (!target.isIsVisited()) {
                    s.push(target);
                    target.setIsVisited(true);
                }
            }
        }

        return recorrido;

    }

    public List<List<VertexDijkstra<V, E>>> getConnectedComponents() {
        this.setearNovisitado();
        List<List<VertexDijkstra<V, E>>> result = new LinkedList<>();
        for (VertexDijkstra<V, E> v : vertices) {
            if (!v.isIsVisited()) {
                result.add(recorreEnProfundidad(v));
            }
        }
        return result;
    }

    public void setearNovisitado() {
        for (VertexDijkstra<V, E> vertice : vertices) {
            vertice.setIsVisited(false);
        }
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder();
        v.append(" v={");

        StringBuilder a = new StringBuilder();
        a.append(" a={");

        for (VertexDijkstra<V, E> vertex : vertices) {
            v.append(vertex.getContent());
            v.append(", ");
            for (EdgeDijkstra<E, V> e : vertex.getEdges()) {
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
