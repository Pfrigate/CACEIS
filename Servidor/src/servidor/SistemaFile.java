/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author zorro
 */
public class SistemaFile implements Serializable {
    Arbol arbol;
    Nodo nodo;
    SistemaFile (String name){
       
        arbol=new Arbol();
        File raizF=new File(name);
        nodo = arbol.insertarRaiz(raizF.getName());
        arbol.raiz.verNodo();
        walkin(raizF);

        arbol.verHijosRecursivo(nodo);
        
    }
    
    public void walkin(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    arbol.InsertarRecursivo(nodo,listFile[i].getName(),dir.getName());
                    //System.out.println(dir.getName());
                    walkin(listFile[i]);
                } else {
                    arbol.InsertarRecursivo(nodo,listFile[i].getName(),dir.getName());
                    //System.out.println(listFile[i].getName());
                }
            }
        }
    }
}
