/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apo;

/**
 *
 * @author david
 */
public class APO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Heap<Persona> ah = new Heap<>(false, (a, b) -> {
            return a.getEdad() - b.getEdad();
        });
        ah.insert(new Persona(10, "juan", true));
        ah.insert(new Persona(9, "paula", true));
        ah.insert(new Persona(170, "maria", true));
        ah.insert(new Persona(15, "mercedes", true));
        ah.insert(new Persona(40, "toral", true));
        ah.insert(new Persona(12, "delita", true));
        ah.imprimirArreglo();
        ah.desencolar();
        ah.imprimirArreglo();

    }

}
