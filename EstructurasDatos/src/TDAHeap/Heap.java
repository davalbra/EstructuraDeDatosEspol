/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apo;

import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author david
 */
public class Heap<E> {

    private Random r = new Random();
    private Comparator<E> cmp;
    private E[] arreglo;
    private int max;
    private int efectivo;
    private boolean isMax;

    public Heap(boolean isMax, Comparator<E> cmp) {//constructor de la clase
        this.max = 30;
        this.cmp = cmp;
        this.isMax = isMax;
        arreglo = (E[]) (new Object[max]);
        efectivo = 0;

    }

    public E pstIzq(int i) {//retorn el hijo izquierdo
        if (((i * 2) + 1) <= efectivo) {
            return arreglo[(i * 2) + 1];
        } else {
            System.out.println("espacion agotador...");
            return null;
        }
    }

    public E posDer(int i) {//retorna el hijo derecho
        if ((i * 2 + 2) < efectivo) {
            return arreglo[i * 2 + 2];
        } else {
            System.out.println("espacio agotado...");
            return null;
        }
    }
//retorna el padre 
    public E posPadre(int i) {
        if (i != 0) {
            if (i <= efectivo) {
                return arreglo[(i - 1) / 2];
            } else {
                System.out.println("espacio agotado...");
                return null;
            }
        } else {
            return null;
        }
    }
    //metodo que no sirve para nada, solo me dice si es padre por la izq y derecha 
    public boolean isFatherx2(int i) {
        if ((i * 2 + 1) > efectivo) {
            return false;
        }
        if ((i * 2 + 2) > efectivo) {
            return false;
        }
        return true;

    }

    //retorna si el tda ta vacio
    public boolean isEmpty() {
        return efectivo <= 0;
    }
// ajsuta de forma recursiva desde la posicion posnodo 
    public void ajustar(int posnodo) {
        int pmayor = posnodo;
        int izq = ((posnodo * 2) + 1);
        int der = (posnodo * 2 + 2);
        if (izq >= 0 && izq < this.efectivo && condicionIsMax(isMax, izq, posnodo)) {
            pmayor = izq;
            if (der >= 0 && der < this.efectivo && condicionIsMax(isMax, der, pmayor)) {
                pmayor = der;
                if (pmayor != posnodo) {
                    intercambiar(pmayor, posnodo);
                }
            } else {
                if (pmayor != posnodo) {
                    intercambiar(pmayor, posnodo);
                }
            }
        } else if (der >= 0 && der < this.efectivo && condicionIsMax(isMax, der, pmayor)) {
            pmayor = der;
            if (pmayor != posnodo) {
                intercambiar(pmayor, posnodo);
            }
        }
    }
//metodo que me ayuda a validar la orientacion edl arreglo
    public boolean condicionIsMax(boolean mx, int valor, int mayor) {
        if (mx) {
            return cmp.compare(this.arreglo[valor], this.arreglo[mayor]) >= 0;
        } else {
            return cmp.compare(this.arreglo[valor], this.arreglo[mayor]) <= 0;
        }
    }
//metodo que hace u cambio en el arreglo(un cambio de 2 puntos)
    public void intercambiar(int pmayor, int posnodo) {
        E tmp = arreglo[pmayor];
        arreglo[pmayor] = arreglo[posnodo];
        arreglo[posnodo] = tmp;
        ajustar(pmayor);
    }
//contruye un heap a partir de un arreglo con contenido aleatorio(no se usa)
    public void buildHeap() {
        for (int i = (this.efectivo / 2) - 1; i >= 0; i--) {
            ajustar(i);
        }
    }
//retorna el elemento en la pos 0 y ejecuta ajustar que es un metodo recursivo y todo se va arreglando recursivamente 
    public E desencolar() {
        E maxValue = null;
        if (!isEmpty()) {
            maxValue = this.arreglo[0];
            arreglo[0] = arreglo[efectivo - 1];
            efectivo--;
            ajustar(0);
        }
        return maxValue;

    }
//este metodo es una sobrecarga del insert me ayuda hacer una recursividad para ir desde el utimo elemento hasta el elemento indicado 
    private int insert(E i, int pos) {
        ajustar(pos);
        return indexPadre(pos);
    }
//este metodo ejecuta la sobrecarga y pregunta al usuario el objeto que quiere ingresar
    public void insert(E i) {
        if (efectivo >= max) {
            addCapacity();
        }
        arreglo[efectivo] = i;
        efectivo++;
        int programa = indexPadre(efectivo - 1);
        while (programa > 0) {
            programa = insert(i, programa);
        }
        insert(i, programa);
    }
//metodo que devuelve el resultado matematico de encontrar al padre
    public int indexPadre(int i) {
        return (i - 1) / 2;
    }
//agrega capacidad al arreglo
    private void addCapacity() {
        E[] tmp = (E[]) (new Object[max * 2]);
        for (int i = 0; i < this.max; i++) {
            tmp[i] = this.arreglo[i];
        }
        this.arreglo = tmp;
        this.max = this.max * 2;
    }
    //imprime el contenido del heap
    public void imprimirArreglo() {
        for (int i = 0; i < efectivo; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println("");
        for (int i = 0; i < efectivo; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");

    }


    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    public Comparator<E> getCmp() {
        return cmp;
    }

    public void setCmp(Comparator<E> cmp) {
        this.cmp = cmp;
    }

    public E[] getArreglo() {
        return arreglo;
    }

    public void setArreglo(E[] arreglo) {
        this.arreglo = arreglo;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(int efectivo) {
        this.efectivo = efectivo;
    }

    public boolean isIsMax() {
        return isMax;
    }

    public void setIsMax(boolean isMax) {
        this.isMax = isMax;
    }

}
