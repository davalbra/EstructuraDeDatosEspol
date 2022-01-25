/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijktra_Floyd_Warshall.Dijkstra;

import TDADinamicGrafo.*;

/**
 *
 * @author david
 */
class Edge<E, V> {

    private Vertex<V, E> source;
    private Vertex<V, E> target;
    private int weight;
    private E data;

    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight, E data) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.data = data;
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight) {
        this(source, target, weight, null);
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target, E data) {
        this(source, target, -1, data);
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target) {
        this(source, target, -1, null);
    }

    public Vertex<V, E> getSource() {
        return source;
    }

    public void setSource(Vertex<V, E> source) {
        this.source = source;
    }

    public Vertex<V, E> getTarget() {
        return target;
    }

    public void setTarget(Vertex<V, E> target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
