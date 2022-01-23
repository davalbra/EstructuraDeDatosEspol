/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAHuffman;

/**
 *
 * @author david
 */
public class BinaryNode<T> {

    private T content;
    private BinaryTreeHM<T> left;
    private BinaryTreeHM<T> right;

    public BinaryNode() {
        this(null, null, null);
    }

    public BinaryNode(T content) {
        this(content, null, null);
    }

    public BinaryNode(T content, BinaryTreeHM<T> left, BinaryTreeHM<T> right) {
        this.content = content;
        this.left = left;
        this.right = right;

    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public BinaryTreeHM<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeHM<T> left) {
        this.left = left;
    }

    public BinaryTreeHM<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeHM<T> right) {
        this.right = right;
    }


    
}
