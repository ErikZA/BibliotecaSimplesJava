/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import appRevista.Revista;
import appRevista.Artigo;
import appRevista.Edicao;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author erik_
 * 
 * O arquivo e carregado, apos o carregamento e feita a leitura dos objetos no laço while, e são convertidos em strings,
 * quando e finalizada a leitura do arquivo a exception e uzada para encerrar o laço,
 * Depois a Array carregada e separada nos respectivos objetos.
 */
public class LerBinario {

    private final ArrayList<Revista> revista = arrayBD.getArrayRevista();
    private int i = -1;
    private int x = -1;
    private int l = 0;
    private ArrayList<String> lista = new ArrayList<>();
    private ObjectInputStream ois = null;
        
    public final void leitorBinario(File file) throws Exception {
        
        FileInputStream fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);
        while (true) {
            try {
                lista = (ArrayList<String>) ois.readObject();
            } catch (Exception ex) {
                break;
            }            
        }

        while (l < lista.size()) {           
            Revista res = new Revista();            
            Edicao edi = new Edicao();
            Artigo arg = new Artigo();

            String[] valor = lista.get(l).split("#");
                if (valor.length == 6) {                  
                    res.setID(Integer.parseInt(valor[1]));
                    res.setNome(valor[2]);
                    res.setEditora(valor[3]);
                    res.setSite(valor[4]);
                    revista.add(res);
                    i++;
                    x = -1;
                }
                if (valor.length == 5) {
                    edi.setID(Integer.parseInt(valor[1]));
                    edi.setEdicao(valor[2]);
                    edi.setNumEdicao(Integer.parseInt(valor[3]));
                    revista.get(i).setEdicao(edi);
                    x++;
                }
                if (valor.length == 4) {
                    arg.setID(Integer.parseInt(valor[1]));
                    arg.setEscritor(valor[2]);
                    revista.get(i).getEdicao().get(x).setArtigo(arg);
                }
           l++;
        }
    }
}
