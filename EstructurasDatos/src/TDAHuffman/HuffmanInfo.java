/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codehm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author david
 */
public class HuffmanInfo {

    private String caracter;
    private int frecuencia;
    private String bit="";

    public HuffmanInfo(String caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }
    public static Map<String, Integer> getFrequencies(String cadena) {
        Map<String, Integer> frecuencia = new HashMap<>();
        String[] lisCadena = cadena.split("");
        for (int i = 0; i < lisCadena.length; i++) {
            if (!frecuencia.containsKey(lisCadena[i])) {
                frecuencia.put(lisCadena[i], 1);
            } else {
                int valor = frecuencia.get(lisCadena[i]);
                frecuencia.replace(lisCadena[i], valor + 1);
            }
        }
        return frecuencia;
    }
    public static BinaryTreeHM<HuffmanInfo> buildHuffmanTree(Map<String, Integer> frecuencia) {
        Queue<BinaryTreeHM<HuffmanInfo>> cola = new PriorityQueue<>((a, b) -> {
            if (a.getRoot().getContent().getFrecuencia() == b.getRoot().getContent().getFrecuencia()) {
                return a.getRoot().getContent().getCaracter().compareTo(b.getRoot().getContent().getCaracter());
            } else {
                return a.getRoot().getContent().getFrecuencia() - b.getRoot().getContent().getFrecuencia();
            }
        });
        Iterator<String> it = frecuencia.keySet().iterator();
        while (it.hasNext()) {
            String llav = it.next();
            cola.offer(new BinaryTreeHM<>(new HuffmanInfo(llav, frecuencia.get(llav))));
        }

        while (cola.size() > 1) {
            BinaryTreeHM<HuffmanInfo> hf1 = cola.poll();
            BinaryTreeHM<HuffmanInfo> hf2 = cola.poll();
            HuffmanInfo padre = new HuffmanInfo(hf1.getRoot().getContent().getCaracter() + hf2.getRoot().getContent().getCaracter(), hf1.getRoot().getContent().getFrecuencia() + hf2.getRoot().getContent().getFrecuencia());
            hf1.getRoot().getContent().setBit("0");
            hf2.getRoot().getContent().setBit("1");
            BinaryTreeHM<HuffmanInfo> treepadre = new BinaryTreeHM<>(new BinaryNode<>(padre, hf1, hf2));
            cola.offer(treepadre);
        }
        return cola.poll();
    }

    public static void getHuffmanCodes(BinaryTreeHM<HuffmanInfo> hm, ArrayList<String> lista, Map<String, String> d1, Map<String, String> d2) {
        for (String s : lista) {
            String codigo = buscaCodes(hm, s);
            d1.put(s, codigo);
            d2.put(codigo, s);
        }
    }

    public static String buscaCodes(BinaryTreeHM<HuffmanInfo> hm, String car) {
        String retornr = "";
        if (hm.isEmpty()) {
            return null;
        } else if (hm.isLeaf() && hm.getRoot().getContent().getCaracter().equals(car)) {
            return hm.getRoot().getContent().getBit();
        } else {
            if (!hm.getLeft().isEmpty() && hm.getLeft().getRoot().getContent().getCaracter().contains(car)) {
                retornr = hm.getRoot().getContent().getBit() + buscaCodes(hm.getLeft(), car);
            }
            if (!hm.getRight().isEmpty() && hm.getRight().getRoot().getContent().getCaracter().contains(car)) {
                retornr = hm.getRoot().getContent().getBit() + buscaCodes(hm.getRight(), car);
            }
        }
        return retornr;
    }

    public static String encode(Map<String, String> d1, String cadena) {
        String retornar = "";
        String[] listCadena = cadena.split("");
        for (String s : listCadena) {
            System.out.println("s: "+s+" d1.get(s)"+d1.get(s));
            retornar = retornar + d1.get(s);
        }
        return retornar;
    }

    public static String decode(Map<String, String> d1, String cadena) {
        String[] listcad = cadena.split("");
        String viajera = listcad[0];
        String retornar = "";
        for (int i = 1; i < listcad.length; i++) {
            if (d1.containsKey(viajera)) {
                retornar = retornar + d1.get(viajera);
                System.out.println("s: "+viajera+" d1.get(s)"+d1.get(viajera));
                viajera = listcad[i];
                
            } else {
                viajera = viajera + listcad[i];

            }
        }
        return retornar + d1.get(viajera);
    }
}
