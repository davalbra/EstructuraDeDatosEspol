package TDABT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
    public BinaryNode<T> findParentRecursive(BinaryNode<T> nodo) {
        if (this.isEmpty()) {
            return null;
        } else {
            BinaryNode<T> retorno = null;

            if (this.root.getLeft() != null) {

                if (this.root.getLeft().getRoot() == nodo) {
                    return this.root;
                }
                retorno = this.root.getLeft().findParentRecursive(nodo);
            }
            if (this.root.getRight() != null) {
                if (this.root.getRight().getRoot() == nodo) {
                    return this.root;
                }
                retorno = this.root.getRight().findParentRecursive(nodo);
            }
            return retorno;
        }
    }

    public BinaryNode<T> findParentIterative(BinaryNode<T> n) {
        if (n == null) {
            return n;
        }
        if (isEmpty()) {
            return null;
        }
        if (n == getRoot()) {
            return null;
        }
        Stack<BinaryTree<T>> stack = new Stack();
        stack.push(this);
        while (!stack.empty()) {
            BinaryTree<T> subtree = stack.pop();
            if (subtree.root.getLeft() != null) {
                BinaryNode<T> Ln = subtree.root.getLeft().getRoot();
                if (Ln == n) {
                    return subtree.root;
                } else {
                    stack.push(subtree.root.getLeft());
                }
            }
            if (subtree.root.getRight() != null) {
                BinaryNode<T> Rn = subtree.root.getRight().getRoot();
                if (Rn == n) {
                    return subtree.root;
                } else {
                    stack.push(subtree.root.getRight());
                }
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

    public int countLevelsIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (!subtree.isLeaf()) {
                    if (subtree.root.getLeft() != null) {
                        stack.push(subtree.root.getLeft());
                    } else {
                        stack.push(new BinaryTree<>(root.getContent()));
                    }
                    if (subtree.root.getRight() != null) {
                        stack.push(subtree.root.getRight());
                    } else {
                        stack.push(new BinaryTree<>(root.getContent()));
                    }
                } else {
                    if (count < stack.size()) {
                        count = stack.size();
                    }
                }
            }
        }

        return count + 1;
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

    public static Map<Integer, ArrayList<Integer>> calculo(BinaryTree<Integer> arbol, Map<Integer, ArrayList<Integer>> mapa, int nivel) {
        if (!mapa.containsKey(nivel)) {
            mapa.put(nivel, new ArrayList<>());
        }
        if (arbol.isEmpty()) {
            return null;
        } else {
            mapa.get(nivel).add(arbol.getRoot().getContent());
//            Map<Integer, ArrayList<Integer>> maper = null;
            if (arbol.root.getLeft() != null) {
                mapa = arbol.root.getLeft().calculo(arbol.root.getLeft(), mapa, nivel + 1);
            }
            if (arbol.root.getRight() != null) {
                mapa = arbol.root.getRight().calculo(arbol.root.getRight(), mapa, nivel + 1);
            }
            return mapa;
        }
    }

    public static void largestValueOfEachLevelRecursive(BinaryTree<Integer> arbol) {
        Map<Integer, ArrayList<Integer>> mapa = new HashMap<>();
        Map<Integer, ArrayList<Integer>> resultado = calculo(arbol, mapa, 0);
        for (int i = 0; i < resultado.size(); i++) {
            int contenido = resultado.get(i).get(0);
            for (int j = 1; j < resultado.get(i).size(); j++) {
                if (contenido < resultado.get(i).get(i)) {
                    contenido = resultado.get(i).get(i);
                }
            }
            System.out.println("nivel: " + i + " mayor: " + contenido);

        }

    }

    public static void largestValueOfEachLevelIterative(BinaryTree<Integer> arbol) {
        Stack<BinaryNode<Integer>> padres = new Stack();

        if (arbol.isEmpty()) {
            System.out.println("null");
        } else {
            padres.push(arbol.getRoot());
            while (!padres.empty()) {
                PriorityQueue<Integer> colle = new PriorityQueue<>((a, b) -> {
                    return b - a;
                });
                Stack<BinaryNode<Integer>> hijos = new Stack();
                while (!padres.empty()) {
                    BinaryNode<Integer> hijo = padres.pop();
                    colle.add(hijo.getContent());
                    if (hijo.getLeft() != null) {
                        hijos.push(hijo.getLeft().getRoot());
                    }
                    if (hijo.getRight() != null) {
                        hijos.push(hijo.getRight().getRoot());
                    }
                }
                System.out.println(colle.poll());
                padres = hijos;

            }
        }
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
        if (getLeft() != null && getRight() != null) {
            return getLeft().isHeightBalancedRecursive() && getRight().isHeightBalancedRecursive();
        } else if (getLeft() == null && getRight() == null) {
            return true;
        } else {
            BinaryTree<T> hoja = (getLeft() != null) ? getLeft() : getRight();
            return hoja.isLeaf();
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
