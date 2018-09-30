/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package Controle;

/**
 *
 * @author erik_
 */
import appRevista.Artigo;
import appRevista.Edicao;
import appRevista.Revista;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author erik_
 * 
 * O leitor carrega o arquivo e le as linhas uma por uma e garrega um vetor de string,
 * Apos carregar a string e feitta a sepração das strings nos respctivos objetos.
 */

public class ReadFiles {

    private final ArrayList<Revista> revista = arrayBD.getArrayRevista();
    
    public final void read(File file) throws IOException {        
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String linha = reader.readLine();
        int i = -1;
        int x = -1;

        while (linha != null) {
            Revista res = new Revista();            
            Edicao edi = new Edicao();
            Artigo arg = new Artigo();

            String[] valor = linha.split("#");
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
            linha = reader.readLine();

        }

        fileReader.close();
        reader.close();
    }

}
