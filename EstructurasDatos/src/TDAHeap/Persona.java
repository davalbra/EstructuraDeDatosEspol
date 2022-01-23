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
public class Persona {
    private int edad;
    private String nombre;
    private boolean casado;

    public Persona(int edad, String nombre, boolean casado) {
        this.edad = edad;
        this.nombre = nombre;
        this.casado = casado;
    }

    
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isCasado() {
        return casado;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    @Override
    public String toString() {
        return "Persona{" + "edad=" + edad + ", nombre=" + nombre + '}';
    }
    
    
    
}
