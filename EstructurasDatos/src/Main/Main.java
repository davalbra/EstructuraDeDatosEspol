/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import TDABT.BinaryNode;
import TDABT.BinaryTree;
import java.util.Comparator;

/**
 *
 * @author david
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        Comparator<Integer> cmp=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };
        //*********************1***********************
        System.out.println("padre de los metodos: " + tree.getRight().getRoot());
        BinaryNode bt1 = tree.findParentRecursive(tree.getRight().getRight().getRoot(),cmp);
        System.out.println("propuesta recursiva de findParent: " + bt1);

        BinaryNode bt2 = tree.findParentIterative(tree.getRight().getRight().getRoot(),cmp);
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

    
}
