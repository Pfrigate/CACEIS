/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import java.io.Serializable;


public class Arbol implements Serializable{
    Nodo raiz;
    
    public Nodo insertarRaiz(String dato){
        raiz=new Nodo(dato);
        return raiz;
    }

    public void verHijosRecursivo(Nodo nodo){
        for (int i = 0; i <nodo.nohijos; i++) {
            nodo.hijos[i].verNodo();
            verHijosRecursivo(nodo.hijos[i]);
        }
    }
    public void InsertarRecursivo(Nodo nodo,String dato,String padre){
        Nodo nuevo = new Nodo(dato);
        if(nodo.getDato().equals(padre)){
            nodo.aumentarHijos(nuevo);
        }else{
            for (int i = 0; i <nodo.nohijos; i++) {
                if(nodo.hijos[i].getDato().equals(padre)){
                  nodo.hijos[i].aumentarHijos(nuevo);
                }else{
                    InsertarRecursivo(nodo.hijos[i],dato,padre);
                }
            }
        }
    }    

}
