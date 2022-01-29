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

    public BTSTree(Comparator<K> cmp) {
        this.root = new BTSNode<>();
        this.cmp = cmp;
    }

    public BTSTree(K k, V v) {
        this.root = new BTSNode<>(k, v);
    }

    public BTSTree(BTSNode<K, V> root) {
        this.root = root;
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
        tree.setCmp(cmp);
        if (this.getRoot().getContent() == null) {
            root = tree.getRoot();
        } else if (cmp.compare(this.root.getKey(), tree.getRoot().getKey()) < 0) {
            if (this.getRight() != null) {
                getRight().insertNodeByKEy(tree);
            } else {
                this.setRight(tree);
            }
        } else {
            if (this.getLeft() != null) {
                System.out.println(this.getLeft().getRoot().getKey());
                getLeft().insertNodeByKEy(tree);

            } else {
                this.setLeft(tree);
            }
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

    public V buscarByKey(K k) {
        if (this.isEmpty()) {
            return null;
        }
        if (cmp.compare(this.getRoot().getKey(), k) == 0) {
            return root.getContent();
        } else if (cmp.compare(this.getRoot().getKey(), k) < 0) {
            this.getRight().buscarByKey(k);

        } else {
            this.getLeft().buscarByKey(k);
        }
        return null;
    }

    public Comparator<K> getCmp() {
        return cmp;
    }

    public void setCmp(Comparator<K> cmp) {
        this.cmp = cmp;
    }

}
