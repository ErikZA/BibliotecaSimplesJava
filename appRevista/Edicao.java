/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appRevista;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author erik_
 */
public class Edicao implements Serializable {
    private int numEdicao;
    private String Edicao;
    private int ID;   
    private ArrayList<Artigo> artigo = new ArrayList<>();

    public  Edicao() {
    }

    public final ArrayList<Artigo> getArtigo() {
        return artigo;
    }

    public final void setArtigo(Artigo artigo) {
        this.artigo.add(artigo);
    }



    public Edicao(int numEdicao, String Edicao, int ID) {
        this.numEdicao = numEdicao;
        this.Edicao = Edicao;
        this.ID = ID;
    }

    public final void setID(int ID) {
        this.ID = ID;
    }

    public final int getID() {
        return ID;
    }

    public final int getNumEdicao() {
        return numEdicao;
    }

    public final void setNumEdicao(int numEdicao) {
        this.numEdicao = numEdicao;
    }

    public final String getEdicao() {
        return Edicao;
    }

    public final void setEdicao(String Edicao) {
        this.Edicao = Edicao;
    }
}
