/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author david
 */
public class AVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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

}
