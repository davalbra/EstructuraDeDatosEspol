/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAABB;

/**
 *
 * @author david
 */
public class BTSNode<K, V> {

    private K key;
    private V content;
    private BTSTree<K, V> left;
    private BTSTree<K, V> right;

    public BTSNode(K key,V content, BTSTree<K, V> left, BTSTree<K, V> right) {
        this.content = content;
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BTSNode() {
        this(null, null, null, null);
    }

    public BTSNode(K key, V conntent) {
        this(key, conntent, null, null);
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public BTSTree<K, V> getLeft() {
        return left;
    }

    public void setLeft(BTSTree<K, V> left) {
        this.left = left;
    }

    public BTSTree<K, V> getRight() {
        return right;
    }

    public void setRight(BTSTree<K, V> right) {
        this.right = right;
    }

}
