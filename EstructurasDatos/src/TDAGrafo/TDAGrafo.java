/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdagrafo;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author david
 */
public class TDAGrafo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Comparator<Personas> cmp = new Comparator<Personas>() {
            @Override
            public int compare(Personas o1, Personas o2) {
                if (o1.getNombre().equals(o2.getNombre()) && o1.getEdad() == o2.getEdad()) {
                    return 0;
                }
                return -1;
            }
        };
        GraphAM<Personas> grafo = new GraphAM<>(false, cmp);
        grafo.addVertex(new Personas("pepe", 0));
        grafo.addVertex(new Personas("David", 20));
        grafo.addVertex(new Personas("Maria", 22));
        grafo.addVertex(new Personas("Andres", 21));
        grafo.connect(grafo.getV(0), grafo.getV(1));
        System.out.println("booleano comprobar: " + grafo.isAdyacente(grafo.getV(0), grafo.getV(2)));
        grafo.printArreglo();
        grafo.printAdjacencyMatrix();
        grafo.removeVertex(grafo.getV(0));
        System.out.println("********************** ");
        grafo.printArreglo();
        grafo.printAdjacencyMatrix();
        grafo.removeVertex(grafo.getV(2));
        System.out.println("********************** ");
        grafo.printArreglo();
        grafo.printAdjacencyMatrix();
    }

}
