/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codehm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import javax.print.DocFlavor;

/**
 *
 * @author david
 */
public class CodeHM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cadena = leerArchivo("src/Texto/tex.txt");
        System.out.println(cadena);
        System.out.println("------------------1------------------");
        Map<String, Integer> frecuencia = HuffmanInfo.getFrequencies(cadena);
        System.out.println("impresion del mapa principal: ");
        principalMap(frecuencia);
        System.out.println("----------------2----------------");
        BinaryTreeHM<HuffmanInfo> arbolhf = HuffmanInfo.buildHuffmanTree(frecuencia);
        System.out.println(arbolhf.getRoot().getContent().getCaracter());
        System.out.println("----------------3----------------");
        Map<String, String> mapa1 = new HashMap<>();
        Map<String, String> mapa2 = new HashMap<>();
        HuffmanInfo.getHuffmanCodes(arbolhf, new ArrayList<>(frecuencia.keySet()), mapa1, mapa2);
        System.out.println("size del mapa 1: " + mapa1.size() + " size del mapa 2: " + mapa2.size());
        System.out.println("impresion del mapa 1: ");
        imprimirMapa(mapa1);
        System.out.println("impresion del mapa 2: ");
        imprimirMapa(mapa2);
        System.out.println("------------------4------------------");
        System.out.println(HuffmanInfo.encode(mapa1, cadena));
        System.out.println("------------------5------------------");
        System.out.println(HuffmanInfo.decode(mapa2, HuffmanInfo.encode(mapa1, cadena)));
        System.out.println("------------------6------------------");
        
        escribirArchivo("src/Texto/tex1.txt", HuffmanInfo.encode(mapa1,cadena));
        System.out.println("------------------7------------------");
        escribirArchivo("src/Texto/tex2.txt", HuffmanInfo.decode(mapa2, HuffmanInfo.encode(mapa1, cadena)));
    }

    public static void imprimirMapa(Map<String, String> map) {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String valor = it.next();
            System.out.println("clave: " + valor + " valor: " + map.get(valor));
        }
    }

    public static void principalMap(Map<String, Integer> map) {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String valor = it.next();
            System.out.println("clave: " + valor + " valor: " + map.get(valor));
        }
    }

    public static String leerArchivo(String nombre) {
        String texto = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(nombre));
            String temp = "";
            String read;
            while ((read = bf.readLine()) != null) {
                temp = temp + " " + read;
            }
            texto = temp;
            bf.close();
        } catch (Exception e) {
            System.out.println("error al leer");
        }
        return texto;
    }

    public static void escribirArchivo(String nombre, String cadena) {

        try {
            FileWriter fw = new FileWriter(nombre);
            fw.write(cadena);
            fw.close();
        } catch (Exception e) {
            System.out.println("error al escribir ");
        }
    }
}
