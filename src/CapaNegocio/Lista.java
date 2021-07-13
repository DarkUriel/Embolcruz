/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

/**
 *
 * @author dark-uriel
 */
public class Lista {
    Nodo Primero, Ultimo;
    int contador;

    public Lista() {
        Primero = Ultimo = null;
        contador = 0;
    }
    public boolean Vacio(){
        return Primero == null;
    }
    public void Insertar(Detalle detalle){
        Nodo nuevo = new Nodo();
        nuevo.detalle = detalle;
        if (Vacio()) {
            Primero = nuevo;
            Primero.Siguiente = null;
            Ultimo = Primero;
        } else {
            Ultimo.Siguiente = nuevo;
            nuevo.Siguiente = null;
            Ultimo = nuevo;
        }
        contador++;
    }
    public int Contador(){
        return contador;
    }
    public Nodo getLista(){
        Nodo aux = Primero;
        return aux;
    }
    
}
