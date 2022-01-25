package TDABT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    //constructor
    public BinaryTree() {
        this.root = new BinaryNode<>();
    }

    //constructor
    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    //constructor
    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }

    //method to add a component left
    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    //method to add a component right
    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    //method to return the component left
    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    //method do return the component right
    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    //method to know if the tree is empty
    public boolean isEmpty() {
        return this.root == null;
    }

    //metod to now if the component is a leaf
    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    //metodo to count leaves recursive
    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeavesRecursive();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeavesRecursive();
            }
            return leavesLeft + leavesRight;
        }
    }

    //metodo to count leaves iterative
    public int countLeavesIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {

                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());
                }
                if (subtree.isLeaf()) {
                    count++;
                }
            }
        }
        return count;
    }

    //metodo to search a component recursive
    public BinaryNode<T> searchRecursive(T content, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().searchRecursive(content, cmp);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().searchRecursive(content, cmp);
                    }
                }
                return tmp;
            }
        }
    }

    //metodo to search a component iterative
    public BinaryNode<T> searchIterative(T content, Comparator<T> cmp) {
        Stack<BinaryTree<T>> stack = new Stack();
        if (this.isEmpty()) {
            return null;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                if (cmp.compare(content, stack.get(0).root.getContent()) == 0) {
                    return stack.get(0).root;
                }
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());

                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());

                }
                if (cmp.compare(content, subtree.root.getContent()) == 0) {
                    return subtree.root;
                }

            }
        }
        return null;

    }

    //metodo to return the least of all content from the tree recursive
    public BinaryNode<T> getMinRecursive(Comparator<T> cmp) {

        if (this.isEmpty()) {
            return null;
        } else if (this.isLeaf()) {
            return this.root;
        } else {
            BinaryNode<T> binari = this.root;
            if (this.root.getLeft() != null) {
                BinaryNode<T> viajero = this.root.getLeft().getMinRecursive(cmp);
                if (cmp.compare(binari.getContent(), viajero.getContent()) > 0) {
                    binari = viajero;
                }
            }
            if (this.root.getRight() != null) {
                BinaryNode<T> viajero = this.root.getRight().getMinRecursive(cmp);
                if (cmp.compare(binari.getContent(), viajero.getContent()) > 0) {
                    binari = viajero;
                }
            }
            return binari;
        }

    }

    //metodo to return the least of all content from the tree recursive
    public BinaryNode<T> getMinIterative(Comparator<T> cmp) {
        Stack<BinaryTree<T>> stack = new Stack<>();
        BinaryNode<T> menor;
        if (this.isEmpty()) {
            return null;
        } else {
            menor = this.root;
            stack.push(this);
            while (!stack.isEmpty()) {
                BinaryTree<T> subTree = stack.pop();
                if (cmp.compare(subTree.root.getContent(), menor.getContent()) < 0) {
                    menor = subTree.root;
                }
                if (subTree.root.getLeft() != null) {
                    stack.push(subTree.root.getLeft());
                }
                if (subTree.root.getRight() != null) {
                    stack.push(subTree.root.getRight());
                }
            }
        }
        return menor;
    }

    //method to coun the childrens recursive
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

    ////method to coun the childrens iterative
    public int countDescendantsIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    count++;
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    count++;
                    stack.push(subtree.root.getRight());
                }

            }
        }
        return count;
    }
    //Tarea:

    //    1) Implemente el método findParent, que dado un nodo de árbol binario, retorna el padre
    //          correspondiente.La implementación de su método debe considerar que el nodo raíz no tiene un padre
    public BinaryNode<T> findParentRecursive(BinaryNode<T> nodo, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        }
        BinaryNode<T> retorno = null;

        if (this.root.getLeft() != null) {

            if (cmp.compare(this.root.getLeft().getRoot().getContent(), nodo.getContent()) == 0) {
                return root;
            }
            BinaryNode<T> verificar = getLeft().findParentRecursive(nodo, cmp);
            if (verificar != null) {
                retorno = verificar;
            }
        }

        if (this.root.getRight() != null) {
            if (cmp.compare(getRight().getRoot().getContent(), nodo.getContent()) == 0) {
                return root;
            }
            BinaryNode<T> verificar = getRight().findParentRecursive(nodo, cmp);
            if (verificar != null) {
                retorno = verificar;
            }
        }
        return retorno;

    }

    public BinaryNode<T> findParentIterative(BinaryNode<T> nodo, Comparator<T> cmp) {
        if (nodo == null) {
            return nodo;
        }
        if (isEmpty()) {
            return null;
        }
        if (nodo == getRoot()) {
            return null;
        }
        Stack<BinaryTree<T>> stack = new Stack();
        stack.push(this);
        while (!stack.empty()) {
            BinaryTree<T> subtree = stack.pop();
            if (subtree.root.getLeft() != null) {
                if (cmp.compare(getLeft().getRoot().getContent(), nodo.getContent()) == 0) {
                    return subtree.root;
                }
                stack.push(subtree.root.getLeft());
            }
            if (subtree.root.getRight() != null) {
                if (cmp.compare(getRight().getRoot().getContent(), nodo.getContent()) == 0) {
                    return subtree.root;
                }
                stack.push(subtree.root.getRight());
            }
        }
        return null;
    }
//    2 Implemente el método countLevels que calcule el número de niveles de árbol
//    Considere que un árbol vacío tiene 0 niveles , mientras que un árbol hoja tiene 1 solo nivel

    public int countLevelsRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int levelLeft = 0;
            int levelRight = 0;
            if (this.root.getLeft() != null) {
                levelLeft += this.root.getLeft().countLevelsRecursive() + 1;
            }
            if (this.root.getRight() != null) {
                levelRight += this.root.getRight().countLevelsRecursive() + 1;
            }
            return (levelLeft >= levelRight ? levelLeft : levelRight);

        }
    }

    public int countLevelsIterative() {
        int level = 0;
        if (!isEmpty()) {
            int elementosRestantes = 1;
            int elementosEnNivel = 0;
            Stack<BinaryTree<T>> tree = new Stack<>();
            tree.add(this);
            while (!tree.isEmpty()) {
                BinaryTree<T> subtree = tree.pop();
                elementosRestantes--;

                if (subtree.getLeft() != null) {
                    tree.add(subtree.getLeft());
                    elementosEnNivel++;
                }
                if (subtree.getRight() != null) {
                    tree.add(subtree.getRight());
                    elementosEnNivel++;
                }

                if (elementosRestantes == 0) {
                    level++;
                    elementosRestantes = elementosEnNivel;
                    elementosEnNivel = 0;
                }

            }

        }
        return level;
    }

//    3. Se dice que un árbol binario es zurdo si el árbol 1) está vacío, 2) es una hoja , o 3) si sus
//    hijos son ambos zurdos y tiense a más de la mitad de sus descendientes en el hijo izquierdo
//    Implementar el método isLefty que indique si un árbol binario es zurdo o no
    public int logicaIsLeftyRecursive() {
        if (this.isEmpty() || this.isLeaf()) {
            return 1;
        } else {
            int leavesleft = 0;
            int leavesright = 0;
            if (this.root.getLeft() != null) {
                int valor = this.root.getLeft().logicaIsLeftyRecursive();
                if (valor == -1) {
                    return -1;
                } else {
                    leavesleft += valor + 1;
                }
            }
            if (this.root.getRight() != null) {
                int valor = this.root.getRight().logicaIsLeftyRecursive();
                if (valor == -1) {
                    return -1;
                } else {
                    leavesright += valor + 1;
                }
            }
            if (leavesleft > (leavesleft + leavesright) / 2) {
                System.out.println("retornod de hijos: " + (leavesleft + leavesright));
                return leavesleft + leavesright;
            } else {
                return -1;
            }
        }
    }

    public boolean isLeftyRecursive() {
        return this.logicaIsLeftyRecursive() > 0;
    }

    public boolean isLeftyIterative() {
        if (isEmpty() || isLeaf()) {
            return true;
        } else {
            Stack<BinaryTree<T>> stack = new Stack();
            stack.push(this);
            int Left = 0;
            int Right = 0;
            while (!stack.isEmpty()) {
                BinaryTree<T> st = stack.pop();
                if (st.root.getLeft() != null) {
                    Left += st.getLeft().countDescendantsIterative();
                    stack.push(st.getLeft());
                }
                if (st.root.getRight() != null) {
                    Right += st.getRight().countDescendantsIterative();
                    stack.push(st.getRight());
                }
            }
            return (Left > (Right + Left) / 2);
        }

    }

//    4. Implemente el método isIdentical que , dado un segundo árbol binario, retorne true o
//    false indicando si dicho árbol es igual al que invoca el método(this)
    public boolean isIdenticalRecursive(BinaryTree<T> repli) {
        boolean c1 = true;
        boolean c2 = true;
        boolean condicion1 = this.root.getLeft() == null && repli.getLeft() == null;
        boolean condicion2 = this.root.getRight() == null && repli.getRight() == null;
        if (this.root.getLeft() != null && repli.getLeft() != null) {
            c1 = this.root.getLeft().isIdenticalRecursive(repli.getLeft());
        } else if (condicion1) {
        } else {
            return false;
        }
        if (this.root.getRight() != null && repli.getRight() != null) {
            c2 = this.root.getRight().isIdenticalRecursive(repli.getRight());
        } else if (condicion2) {
        } else {
            return false;
        }
        if (c1 && c2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isIdenticalIterative(BinaryTree<T> t) {
        if (isEmpty() && t.isEmpty()) {
            return true;
        }
        if (isEmpty()) {
            return false;
        }
        if (t.isEmpty()) {
            return false;
        }

        Stack<BinaryTree<T>> stack = new Stack();
        Stack<BinaryTree<T>> stackCopy = new Stack();

        stack.push(this);
        stackCopy.push(t);

        while (!stack.empty() && !stackCopy.empty()) {
            BinaryTree<T> subtree = stack.pop();
            BinaryTree<T> subtreeCopy = stackCopy.pop();

            if (subtree.getRoot().getContent() != subtreeCopy.getRoot().getContent()) {
                return false;
            }

            if (subtree.root.getLeft() != null && subtreeCopy.getLeft() != null) {
                stack.push(subtree.root.getLeft());
                stackCopy.push(subtreeCopy.root.getLeft());
            } else if (subtree.root.getLeft() == null && subtreeCopy.root.getLeft() == null) {
                // Empty
            } else {
                return false;
            }

            if (subtree.root.getRight() != null && subtreeCopy.getRight() != null) {
                stack.push(subtree.root.getRight());
                stackCopy.push(subtreeCopy.root.getRight());
            } else if (subtree.root.getRight() == null && subtreeCopy.root.getRight() == null) {
                // Empty
            } else {
                return false;
            }
        }

        return true;

    }
//    5. Encontrar el valor más grande de cada nivel del árbol. El método largestValueOfEachLevel
//    debe imprimir el mayor valor presente en cada nivel de un árbol binario cuyos nodos
//    contienen números enteros. Ejemplos:

    public void largestValueOfEachLevelRecursive(Comparator<T> cmp) {
        List<T> valores = new ArrayList<>();
        Recursive(this.getRoot(), valores, 0, cmp);
        for (T valor : valores) {
            System.out.print(valor + " ");
        }
        System.out.println("");

    }

    public void Recursive(BinaryNode<T> node, List<T> valores, int level, Comparator<T> cmp) {
        if (node == null) {
            return;
        }
        if (level == valores.size()) {
            valores.add(node.getContent());
        } else {
            if (cmp.compare(valores.get(level), node.getContent()) >= 0) {
                valores.set(level, valores.get(level));
            } else {
                valores.set(level, node.getContent());
            }

        }
        if (node.getLeft() != null) {
            Recursive(node.getLeft().getRoot(), valores, level + 1, cmp);
        }
        if (node.getRight() != null) {
            Recursive(node.getRight().getRoot(), valores, level + 1, cmp);
        }

    }

    public void largestValueOfEachLevelIterative(Comparator<T> cmp) {
        if (this.isEmpty()) {
            return;
        }
        Stack<BinaryNode<T>> pila = new Stack();
        pila.push(root);

        Map<BinaryNode<T>, Integer> mapa = new HashMap();

        Map<Integer, List<T>> valoresNivel = new HashMap();

        mapa.put(root, 1);

        while (!pila.isEmpty()) {

            BinaryNode<T> bn = pila.pop();
            int nivel = mapa.get(bn) + 1;
            if (bn.getLeft() != null) {
                BinaryNode<T> izquierdo = bn.getLeft().getRoot();
                pila.push(izquierdo);
                mapa.put(izquierdo, nivel);
                if (valoresNivel.containsKey(nivel)) {
                    valoresNivel.get(nivel).add(izquierdo.getContent());
                } else {
                    List<T> l1 = new LinkedList();
                    l1.add(izquierdo.getContent());
                    valoresNivel.put(nivel, l1);
                }
            }
            if (bn.getRight() != null) {
                BinaryNode<T> derecho = bn.getRight().getRoot();
                pila.push(derecho);

                mapa.put(derecho, nivel);
                if (valoresNivel.containsKey(nivel)) {
                    valoresNivel.get(nivel).add(derecho.getContent());
                } else {
                    List<T> lista = new LinkedList();
                    lista.add(derecho.getContent());
                    valoresNivel.put(nivel, lista);
                }
            }
        }
        System.out.print(root.getContent() + " ");

        valoresNivel.keySet().forEach(i -> {
            System.out.print(valoresNivel.get(i).stream().max(cmp).get() + " ");
        });

        System.out.println("");
    }
//6. El método countNodesWithOnlyChild debe retornar el número de nodos de un árbol que
//tienen un solo hijo. Ejemplo:

    public int countNodesWithOnlyChildRecursive() {
        if (isEmpty()) {
            return 0;
        }
        int c = 0;
        if (this.root.getLeft() != null && this.root.getRight() == null || this.root.getLeft() == null && this.root.getRight() != null) {
            c++;
        }
        if (this.getLeft() != null) {
            c += this.getLeft().countNodesWithOnlyChildRecursive();
        }
        if (this.getRight() != null) {
            c += this.getRight().countNodesWithOnlyChildRecursive();
        }
        return c;
    }

    public int countNodesWithOnlyChildIterative() {
        if (isEmpty()) {
            return 0;
        }
        int c;
        Stack<BinaryTree<T>> stack = new Stack();
        stack.push(this);
        c = 0;

        while (!stack.isEmpty()) {
            BinaryTree<T> st = stack.pop();
            BinaryTree<T> Lt = st.getLeft();
            BinaryTree<T> Rt = st.getRight();

            if (!st.isLeaf()) {
                int cL = (Lt != null) ? 1 : 0;
                int cR = (Rt != null) ? 1 : 0;
                if ((cL + cR) == 1) {
                    c++;
                }
            }

            if (Lt != null) {
                stack.push(Lt);

            }
            if (Rt != null) {
                stack.push(Rt);
            }
        }
        return c;

    }
//   7) El método isHeightBalanced debe retornar si un árbol binario está balanceado en altura o
//    no. Un árbol vacío está siempre balanceado en altura. Un árbol binario no vacío está
//    balanceado si y solo si:

    public boolean isHeightBalancedRecursive() {
        if (isEmpty()) {
            return true;
        }
        if (isLeaf()) {
            return true;
        }
        if (getLeft() != null && getRight() != null) {
            int diferencia = this.getLeft().countLevelsRecursive() - this.getRight().countLevelsRecursive();
            if (diferencia == 1 || diferencia == 0 || diferencia == -1) {
                return getLeft().isHeightBalancedRecursive() && getRight().isHeightBalancedRecursive();
            }
            return false;
        } else if (this.getLeft() != null) {
            return this.getLeft().isLeaf();
        } else {
            return this.getRight().isLeaf();
        }
    }

    public boolean isHeightBalancedIterative() {
        if (isEmpty()) {
            return true;
        }
        int Llevels = 0;
        int Rlevels = 0;

        if (getLeft() != null) {
            Iterator<List<BinaryTree<T>>> it = getLeft().lvlIterator();
            while (it.hasNext()) {
                Llevels++;
                it.next();
            }
        }
        if (getRight() != null) {
            Iterator<List<BinaryTree<T>>> it = getRight().lvlIterator();
            while (it.hasNext()) {
                Rlevels++;
                it.next();
            }
        }
        return Math.abs(Llevels - Rlevels) <= 1;
    }

    private Iterator<List<BinaryTree<T>>> lvlIterator() {
        return new levelIterator<>(this);
    }

    private static class levelIterator<T> implements Iterator<List<BinaryTree<T>>> {

        private final Stack<BinaryTree<T>> stack;
        private final List<BinaryTree<T>> list;

        public levelIterator(BinaryTree<T> arbol) {
            stack = new Stack<>();
            list = new ArrayList<>();
            if (arbol != null) {
                stack.push(arbol);
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public List<BinaryTree<T>> next() {
            list.clear();
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
            list.forEach(a -> {
                if (a.getLeft() != null) {
                    stack.push(a.getLeft());
                }
                if (a.getRight() != null) {
                    stack.push(a.getRight());
                }

            });
            return list;
        }

    }

    public static BinaryTree<Integer> findIntersection(BinaryTree<Integer> arbol1, BinaryTree<Integer> arbol2) {
        if (arbol1.isEmpty() && arbol2.isEmpty()) {
            return null;
        } else if (arbol1.isLeaf() && arbol2.isLeaf()) {
            return new BinaryTree<>(arbol1.getRoot().getContent() + arbol2.getRoot().getContent());
        } else {
            BinaryTree<Integer> retorno = new BinaryTree<>(arbol1.getRoot().getContent() + arbol2.getRoot().getContent());
            if (arbol1.root.getLeft() != null && arbol2.root.getLeft() != null) {
                retorno.setLeft(findIntersection(arbol1.getLeft(), arbol2.getLeft()));
            }
            if (arbol1.root.getRight() != null && arbol2.root.getRight() != null) {
                retorno.setRight(findIntersection(arbol1.getRight(), arbol2.getRight()));
            }
            return retorno;
        }

    }

    //getters and setters method
    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }
}
