/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appRevista;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author erik_
 */
public class Revista  implements Serializable {
    private String Nome;
    private String Editora;
    private String Site;
    private int ID;
    private ArrayList<Edicao> edicao = new ArrayList<>();
    
    public Revista() {
    }  
    
    
    public Revista(String Nome, String Editora, String Site, int ID) {
        this.Nome = Nome;
        this.Editora = Editora;
        this.Site = Site;
        this.ID = ID;
    }

    public final void setEdicao(Edicao edicao) {
        this.edicao.add(edicao);
    }

    public final ArrayList<Edicao> getEdicao() {
        return edicao;
    }    
    
    public final int getID() {
        return ID;
    }

    public final void setID(int ID) {
        this.ID = ID;
    }
    

    public final String getEditora() {
        return Editora;
    }

    public final String getNome() {
        return Nome;
    }

    public final String getSite() {
        return Site;
    }

    public final void setEditora(String Editora) {
        this.Editora = Editora;
    }

    public final void setNome(String Nome) {
        this.Nome = Nome;
    }

    public final void setSite(String Site) {
        this.Site = Site;
    }       
        
}
