/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

public class BalancedNode<K, V> {

    private K key;
    private V content;
    private BalancedTree<K, V> left;
    private BalancedTree<K, V> right;

    public BalancedNode() {
        this(null, null, null, null);
    }

    public BalancedNode(K key, V content) {
        this(key, content, null, null);
    }

    public BalancedNode(K key, V content, BalancedTree<K, V> left, BalancedTree<K, V> right) {
        this.key = key;
        this.content = content;
        this.left = left;
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public BalancedTree<K, V> getLeft() {
        return left;
    }

    public void setLeft(BalancedTree<K, V> left) {
        this.left = left;
    }

    public BalancedTree<K, V> getRight() {
        return right;
    }

    public void setRight(BalancedTree<K, V> right) {
        this.right = right;
    }

    public boolean hasChildren() {
        return (left != null || right != null);
    }

    public boolean hasLeft() {
        return (left != null);
    }

    public boolean hasRight() {
        return (right != null);
    }

    @Override
    public String toString() {
        return content.toString();
    }

}
