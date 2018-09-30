/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appRevista;

import java.io.Serializable;

/**
 *
 * @author erik_
 */
public class Artigo implements Serializable{
   private int ID;
   private String escritor;


    public Artigo(int ID, String escritor) {
        this.ID = 0;
        this.escritor = "";
    }

   
    public Artigo() {
    }
    public final String getEscritor() {
        return escritor;
    }

    public final int getID() {
        return ID;
    }

    public final void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public final void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public final String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
   
   
    
}
