/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zorro
 */
public class Nodo implements Serializable{
    
    String info;
    int nohijos;
    Nodo hijos[];
    Nodo hijosT[];

public  Nodo(String dato){
      info=dato;
      this.nohijos=0;
}

public void copiarHijos(){
hijosT=new Nodo[nohijos+1];
    for (int i = 0; i <this.nohijos; i++) {
        hijosT[i]=hijos[i];
    }
}
public void aumentarHijos(Nodo nodo){
    copiarHijos();
    hijosT[this.nohijos]=nodo;
    hijos=hijosT;
    this.nohijos++;
    
}    
public String getDato(){
return info;
}
public void setDato(String dato){
   info=dato;
}
public void verNodo(){
    System.out.println("{"+info+"}");

    }
}
    
    

