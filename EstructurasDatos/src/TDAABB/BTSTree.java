/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAABB;

import java.util.Comparator;

/**
 *
 * @author david
 */
public class BTSTree<K, V> {

    private BTSNode<K, V> root;
    private Comparator<K> cmp;

    public BTSTree(Comparator<BTSNode<K, V>> cmp) {
        this.root = new BTSNode<>();
    }

    public BTSTree(K k, V v) {
        this.root = new BTSNode<>(k, v);
    }

    public BTSTree(BTSNode<K, V> root) {
        this.root = root;
    }

    public BTSTree(K key,V content, Comparator<BTSNode<K, V>> cmp) {
        this.root = new BTSNode<K, V>(key, content);
    }

    public BTSNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(BTSNode<K, V> root) {
        this.root = root;
    }

    public void setLeft(BTSTree<K, V> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BTSTree<K, V> tree) {
        this.root.setRight(tree);
    }

    public BTSTree<K, V> getLeft() {
        return this.root.getLeft();
    }

    public BTSTree<K, V> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public void insertNodeByKEy(BTSTree<K, V> tree) {
        if (this.isEmpty()) {
            root = tree.getRoot();
        } else if (cmp.compare(root.getKey(), tree.getRoot().getKey()) < 0) {

        }
    }

    public void removeNodeByKey(K key) {
        if (this.isEmpty()) {

        } else if (this.isLeaf()) {
            if (cmp.compare(key, this.getRoot().getKey()) == 0) {
                this.root.setLeft(null);
                this.root.setRight(null);
            }
        } else {

            if (cmp.compare(key, this.getRoot().getKey()) > 0) {
                this.getLeft().removeNodeByKey(key);
            }
        }
    }
}
