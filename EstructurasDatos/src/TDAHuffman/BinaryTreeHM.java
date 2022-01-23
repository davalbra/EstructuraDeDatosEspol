/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codehm;

/**
 *
 * @author david
 */
public class BinaryTreeHM<T> {

    private BinaryNode<T> root;

    public BinaryTreeHM() {
        this.root = new BinaryNode<>();
    }

    public BinaryTreeHM(BinaryNode<T> root) {
        this.root = root;
    }

    public BinaryTreeHM(T content) {
        this.root = new BinaryNode<>(content);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTreeHM<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTreeHM<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTreeHM<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTreeHM<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }
}
