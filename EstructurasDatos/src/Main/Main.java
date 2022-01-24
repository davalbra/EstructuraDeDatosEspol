/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import TDAABB.BTSTree;
import TDABT.BinaryNode;
import TDABT.BinaryTree;
import TDABalancedTree.BalancedTree;
import TDAGrafo.GraphAM;
import TDAGrafo.Personas;
import TDAHeap.Heap;
import TDAHuffman.BinaryTreeHM;
import TDAHuffman.HuffmanInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author david
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
    
    public static void BinaryTree() {
        BinaryTree<Integer> treeisllefty = new BinaryTree(0);
        treeisllefty.setLeft(new BinaryTree(1));
        treeisllefty.setRight(new BinaryTree(2));
        treeisllefty.getLeft().setLeft(new BinaryTree(3));
        treeisllefty.getLeft().getLeft().setLeft(new BinaryTree(3));
        treeisllefty.getLeft().getLeft().getLeft().setLeft(new BinaryTree(3));
        treeisllefty.getLeft().setRight(new BinaryTree(4));
        treeisllefty.getRight().setLeft(new BinaryTree(5));
        treeisllefty.getRight().getLeft().setLeft(new BinaryTree(5));
        treeisllefty.getRight().setRight(new BinaryTree(6));

        //treeisllefty.getRight().getLeft().getLeft().setLeft(new BinaryTree(5));
        //tree
        BinaryTree<Integer> tree = new BinaryTree(0);
        tree.setLeft(new BinaryTree(1));
        tree.setRight(new BinaryTree(2));
        tree.getLeft().setLeft(new BinaryTree(3));
        tree.getLeft().setRight(new BinaryTree(4));
        tree.getRight().setLeft(new BinaryTree(5));
        tree.getRight().setRight(new BinaryTree(6));
        tree.getRight().getRight().setRight(new BinaryTree(7));

        //copia del arbol
        BinaryTree<Integer> treecopy = new BinaryTree(0);
        treecopy.setLeft(new BinaryTree(1));
        treecopy.setRight(new BinaryTree(2));
        treecopy.getLeft().setLeft(new BinaryTree(3));
        treecopy.getLeft().setRight(new BinaryTree(4));
        treecopy.getRight().setLeft(new BinaryTree(5));
        treecopy.getRight().setRight(new BinaryTree(6));
        treecopy.getRight().getRight().setRight(new BinaryTree(7));

        //tree.getRight().getRight().getRight().setRight(new BinaryTree(9));
        BinaryTree<Integer> tree2 = new BinaryTree<>(0);
        tree2.setRight(new BinaryTree(1));
        tree2.getRight().setRight(new BinaryTree(1));
        tree2.getRight().getRight().setRight(new BinaryTree(1));
        tree2.getRight().getRight().getRight().setRight(new BinaryTree(1));
        tree2.getRight().getRight().getRight().getRight().setRight(new BinaryTree(1));

        //*********************Comparator***********************
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        //*********************1***********************
        System.out.println("padre de los metodos: " + tree.getRight().getRoot());
        BinaryNode bt1 = tree.findParentRecursive(tree.getRight().getRight().getRoot(), cmp);
        System.out.println("propuesta recursiva de findParent: " + bt1);

        BinaryNode bt2 = tree.findParentIterative(tree.getRight().getRight().getRoot(), cmp);
        System.out.println("propuesta iterativa de findParent: " + bt2);
        //*********************2***********************
        int bt3 = tree.countLevelsRecursive();
        System.out.println("propuesta recursiva de countLevels: " + bt3);

        int bt4 = tree.countLevelsIterative();
        System.out.println("propuesta iterativa de countLevels: " + bt4);
        //*********************3***********************
        boolean bt5 = tree.isLeftyRecursive();
        System.out.println("propuesta recursiva de isLefty: " + bt5);

        boolean bt6 = tree.isLeftyIterative();
        System.out.println("propuesta iterativa de islefty: " + bt6);
        //*********************4***********************
        boolean bt7 = tree.isIdenticalRecursive(treecopy);
        System.out.println("propuesta recursiva de isIdentical: " + bt7);

        boolean bt8 = tree.isIdenticalIterative(treecopy);
        System.out.println("propuesta iterativa de isIdentical: " + bt8);
        //*********************5***********************
        System.out.println("recursivo de largestValueOfEachLevel: ");
        BinaryTree.largestValueOfEachLevelRecursive(tree);
        System.out.println("iterativo de largestValueOfEachLevel: ");
        BinaryTree.largestValueOfEachLevelIterative(tree);
        //*********************6***********************  
        int bt9 = tree.countNodesWithOnlyChildRecursive();
        System.out.println("propuesta recursiva de countNodesWithOnlyChild: " + bt9);

        int bt10 = tree.countNodesWithOnlyChildIterative();
        System.out.println("propuesta iterativa de countNodesWithOnlyChild: " + bt10);
        //*********************7***********************
        boolean bt11 = tree.isHeightBalancedRecursive();
        System.out.println("propuesta recursiva de countNodesWithOnlyChild: " + bt11);

        boolean bt12 = tree.isHeightBalancedIterative();
        System.out.println("propuesta iterativa de countNodesWithOnlyChild: " + bt12);
    }
    public static void BalancedTree(){
    
        BalancedTree<Integer, String> arbol = new BalancedTree<>();
        arbol.setCmp((a, b) -> {
            return a.compareTo(b);
        });
        //danio derecho
//        arbol.insert(new BalancedTree<>(11, "11"));
//        arbol.insert(new BalancedTree<>(8, "8"));
//        arbol.insert(new BalancedTree<>(14, "14"));
//        arbol.insert(new BalancedTree<>(10, "10"));
//        arbol.insert(new BalancedTree<>(9, "9"));
//        arbol.insert(new BalancedTree<>(4, "7"));
//        arbol.insert(new BalancedTree<>(16, "16"));
//        arbol.insert(new BalancedTree<>(2, "2"));
//        arbol.insertWithRotacion(new BalancedTree<>(17, "17"));
        //danio izq
        arbol.insert(new BalancedTree<>(11, "11"));
        arbol.insert(new BalancedTree<>(8, "8"));
        arbol.insert(new BalancedTree<>(14, "14"));
        arbol.insert(new BalancedTree<>(10, "10"));
        arbol.insert(new BalancedTree<>(9, "9"));
        arbol.insert(new BalancedTree<>(4, "7"));
        arbol.insert(new BalancedTree<>(16, "16"));
        arbol.insert(new BalancedTree<>(2, "2"));
        arbol.recursivepreOrden();
        arbol.insertWithRotacion(new BalancedTree<>(1, "17"));
        System.out.println(arbol.isBalanced());
        //arbol.insertWithRotacion(new BalancedTree<>(1, "1"));
        //arbol.insert(new BalancedTree<>(1, "2"));
        //System.out.println(arbol.isBalanced());
        arbol.recursivepreOrden();
//        arbol.insert(new BalancedTree<>(3, "3"));
//        arbol.insert(new BalancedTree<>(5, "5"));

//        System.out.println("main");
//        System.out.println(arbol.getLeft().getRoot().getKey());
//        System.out.println(arbol.getRight().getRoot().getKey());
//        System.out.println(arbol.getLeft().getRight().getRoot().getKey());
//        System.out.println(arbol.isHeightBalancedRecursive());
//        System.out.println(arbol.getRight().countLevelsRecursive());
    }
    public static void StaticGrafo(){
    
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
    public static void TDAHeap(){
    
        Heap<Personas> ah = new Heap<>(false, (a, b) -> {
            return a.getEdad() - b.getEdad();
        });
        ah.insert(new Personas("juan",1));
        ah.insert(new Personas("paula",2));
        ah.insert(new Personas("maria",3));
        ah.insert(new Personas("mercedes",4 ));
        ah.insert(new Personas("toral",5 ));
        ah.insert(new Personas("delita",6));
        ah.imprimirArreglo();
        ah.desencolar();
        ah.imprimirArreglo();
    }
    public static void HuffmanInfo(){
    
        String cadena = leerArchivo("src/Texto/tex.txt");
        System.out.println(cadena);
        System.out.println("------------------1------------------");
        Map<String, Integer> frecuencia = HuffmanInfo.getFrequencies(cadena);
        System.out.println("impresion del mapa principal: ");
        principalMap(frecuencia);
        System.out.println("----------------2----------------");
        BinaryTreeHM<HuffmanInfo> arbolhf = HuffmanInfo.buildHuffmanTree(frecuencia);
        System.out.println(arbolhf.getRoot().getContent().getCaracter());
        System.out.println("----------------3----------------");
        Map<String, String> mapa1 = new HashMap<>();
        Map<String, String> mapa2 = new HashMap<>();
        HuffmanInfo.getHuffmanCodes(arbolhf, new ArrayList<>(frecuencia.keySet()), mapa1, mapa2);
        System.out.println("size del mapa 1: " + mapa1.size() + " size del mapa 2: " + mapa2.size());
        System.out.println("impresion del mapa 1: ");
        imprimirMapa(mapa1);
        System.out.println("impresion del mapa 2: ");
        imprimirMapa(mapa2);
        System.out.println("------------------4------------------");
        System.out.println(HuffmanInfo.encode(mapa1, cadena));
        System.out.println("------------------5------------------");
        System.out.println(HuffmanInfo.decode(mapa2, HuffmanInfo.encode(mapa1, cadena)));
        System.out.println("------------------6------------------");
        
        escribirArchivo("src/Texto/tex1.txt", HuffmanInfo.encode(mapa1,cadena));
        System.out.println("------------------7------------------");
        escribirArchivo("src/Texto/tex2.txt", HuffmanInfo.decode(mapa2, HuffmanInfo.encode(mapa1, cadena)));
    }

    public static void imprimirMapa(Map<String, String> map) {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String valor = it.next();
            System.out.println("clave: " + valor + " valor: " + map.get(valor));
        }
    }

    public static void principalMap(Map<String, Integer> map) {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String valor = it.next();
            System.out.println("clave: " + valor + " valor: " + map.get(valor));
        }
    }

    public static String leerArchivo(String nombre) {
        String texto = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(nombre));
            String temp = "";
            String read;
            while ((read = bf.readLine()) != null) {
                temp = temp + " " + read;
            }
            texto = temp;
            bf.close();
        } catch (Exception e) {
            System.out.println("error al leer");
        }
        return texto;
    }

    public static void escribirArchivo(String nombre, String cadena) {

        try {
            FileWriter fw = new FileWriter(nombre);
            fw.write(cadena);
            fw.close();
        } catch (Exception e) {
            System.out.println("error al escribir ");
        }
    }
    public static void binarySearchTree(){


    }
}
