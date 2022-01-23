/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDABalancedTree;

/**
 *
 * @author david
 */
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BalancedTree<K, V> {

    private BalancedNode<K, V> root;
    private Comparator<K> cmp;

    public BalancedTree() {
        this.root = new BalancedNode<>();
    }

    public BalancedTree(BalancedNode<K, V> root, Comparator<K> cmp) {
        this.root = root;
        this.cmp = cmp;
    }

    public boolean hasLeft() {
        return root.hasLeft();
    }

    public boolean hasRight() {
        return root.hasRight();
    }

    public BalancedTree(K key, V content) {
        this.root = new BalancedNode<>(key, content);
    }

    public BalancedNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(BalancedNode<K, V> root) {
        this.root = root;
    }

    public void setLeft(BalancedTree<K, V> tree) {
        this.root.setLeft(tree);
        this.getLeft().setCmp(cmp);
    }

    public void setRight(BalancedTree<K, V> tree) {
        this.root.setRight(tree);
        this.getRight().setCmp(cmp);
    }

    public BalancedTree<K, V> getLeft() {
        return this.root.getLeft();
    }

    public BalancedTree<K, V> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root.getContent() == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public Comparator<K> getCmp() {
        return cmp;
    }

    public void setCmp(Comparator<K> cmp) {
        this.cmp = cmp;
    }

    public void insert(BalancedTree<K, V> newTree) {

        if (this.isEmpty()) {
            this.root = newTree.getRoot();
        } else {

            if (cmp.compare(this.root.getKey(), newTree.getRoot().getKey()) < 0) {
                if (this.getRight() == null) {
                    this.setRight(newTree);
                } else {
                    this.getRight().insert(newTree);
                }
            } else if (cmp.compare(this.root.getKey(), newTree.getRoot().getKey()) > 0) {

                if (this.getLeft() == null) {
                    this.setLeft(newTree);
                } else {
                    this.getRoot().getLeft().insert(newTree);
                }
            }
        }
    }

    public void insertWithRotacion(BalancedTree<K, V> newTree) {
        newTree.setCmp(this.cmp);
        System.out.println(this.isBalanced());
        insert(newTree);
        if (!this.isBalanced()) {
            BalancedTree<K, V> padre = newTree;
            BalancedTree<K, V> hijo = null;

            boolean distancia = true;
            while (distancia && this != padre) {
                hijo = padre;
                padre = findParentIterative(padre);
                System.out.println(padre.factorEquilibrio());
                System.out.println(padre.getRoot().getKey());
                distancia = (Math.abs(padre.factorEquilibrio()) < 2);
            }
            System.out.println("factor del padre: " + padre.factorEquilibrio());
            System.out.println("clave del padre: " + padre.getRoot().getKey());
            System.out.println("clave del hijo: " + hijo.getRoot().getKey());
            System.out.println("la distancia booleana: " + !distancia);
            String tipo = padre.tipoRotacion(newTree);
            switch (tipo) {
                case "rr":
                    padre.simpleRotationLeft();
                case "ll":
                    padre.simpleRotationRight();
            }
        }

    }

    public String tipoRotacion(BalancedTree<K, V> newTree) {
        newTree.setCmp(this.cmp);
        String firstMove = "";
        String seconMove = "";
        if (cmp.compare(this.root.getKey(), newTree.getRoot().getKey()) < 0) {
            firstMove = "r";
            if (cmp.compare(this.getRight().root.getKey(), newTree.getRoot().getKey()) < 0) {
                seconMove = "r";
            } else {
                seconMove = "l";
            }

        } else {
            firstMove = "l";
            if (cmp.compare(this.getLeft().root.getKey(), newTree.getRoot().getKey()) > 0) {
                seconMove = "l";
            } else {
                seconMove = "r";
            }
        }

        return "" + firstMove + seconMove;
    }



    public void simpleRotationRight() {
        BalancedTree<K, V> tmp = null;
        if (getLeft().hasRight()) {
            tmp = getLeft().getRight();
            tmp.setCmp(this.cmp);
            getLeft().setRight(null);
        }
        BalancedNode<K, V> newRoot = getLeft().getRoot();
        BalancedNode<K, V> currentRoot = getRoot();

        currentRoot.setLeft(null);

        newRoot.setRight(new BalancedTree<>(currentRoot, cmp));
        if (tmp != null) {
            newRoot.getRight().insert(tmp);
        }
        setRoot(newRoot);
    }

    public void simpleRotationLeft() {
        BalancedTree<K, V> tmp = null;
        if (getRight().hasLeft()) {
            tmp = getRight().getLeft();
            tmp.setCmp(this.cmp);
            getRight().setLeft(null);
        }
        BalancedNode<K, V> newRoot = getRight().getRoot();
        BalancedNode<K, V> currentRoot = getRoot();

        currentRoot.setRight(null);

        newRoot.setLeft(new BalancedTree<>(currentRoot, cmp));
        if (tmp != null) {
            newRoot.getLeft().insert(tmp);
        }
        setRoot(newRoot);
    }

    public void recursivepreOrden() {
        if (root != null) {
            System.out.print(root.getKey() + ":" + root.getContent() + ":" + this.factorEquilibrio() + "  ");
            if (root.getLeft() != null) {
                root.getLeft().recursivepreOrden();
            }
            if (root.getRight() != null) {
                root.getRight().recursivepreOrden();
            }
        }
    }

    public boolean isBalanced() {
        boolean retorno = true;
        boolean hijoDere = true;
        boolean hijoizq = true;
        if (this.isEmpty()) {
            return true;
        } else {
            if (!(Math.abs(this.factorEquilibrio()) < 2)) {
                retorno = false;
            }
            if (this.getLeft() != null) {
                hijoizq = this.getLeft().isBalanced();
            }
            if (this.getRight() != null) {
                hijoDere = this.getRight().isBalanced();
            }
        }
        return retorno && hijoDere && hijoizq;
    }

    public Integer factorEquilibrio() {
        int left = 0;
        int right = 0;
        if (this.getLeft() != null) {
            left = this.getLeft().countLevelsRecursive();
        }
        if (this.getRight() != null) {
            right = this.getRight().countLevelsRecursive();
        }
        return right - left;
    }

    public boolean isHeightBalancedRecursive() {
        if (isEmpty()) {
            return true;
        }
        if (getLeft() != null && getRight() != null) {
            return getLeft().isHeightBalancedRecursive() && getRight().isHeightBalancedRecursive();
        } else if (getLeft() == null && getRight() == null) {
            return true;
        } else {
            BalancedTree<K, V> hoja = (getLeft() != null) ? getLeft() : getRight();
            return hoja.isLeaf();
        }
    }

    public int countLevelsRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft += this.root.getLeft().countLevelsRecursive() + 1;
            }
            if (this.root.getRight() != null) {
                leavesRight += this.root.getRight().countLevelsRecursive() + 1;
            }
            return (leavesLeft >= leavesRight ? leavesLeft : leavesRight);

        }
    }

    public int countDescendantsRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 0;
        } else {
            int leaves = 0;
            if (this.root.getLeft() != null) {
                leaves++;
                leaves += this.root.getLeft().countDescendantsRecursive();
            }
            if (this.root.getRight() != null) {
                leaves++;
                leaves += this.root.getRight().countDescendantsRecursive();
            }
            return leaves;
        }
    }

    public BalancedTree<K, V> findParentIterative(BalancedTree<K, V> n) {
        if (n == null) {
            return n;
        }
        if (isEmpty()) {
            return null;
        }
        if (n == this) {
            return null;
        }
        Stack<BalancedTree<K, V>> stack = new Stack();
        stack.push(this);
        while (!stack.empty()) {
            BalancedTree<K, V> subtree = stack.pop();
            if (subtree.root.getLeft() != null) {
                BalancedNode<K, V> Ln = subtree.root.getLeft().getRoot();
                if (Ln == n.getRoot()) {
                    return subtree;
                } else {
                    stack.push(subtree.root.getLeft());
                }
            }
            if (subtree.root.getRight() != null) {
                BalancedNode<K, V> Rn = subtree.root.getRight().getRoot();
                if (Rn == n.getRoot()) {
                    return subtree;
                } else {
                    stack.push(subtree.root.getRight());
                }
            }
        }

        return null;
    }
}
