/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/** 
    Foi criado tres laços, um dentro do outro para que fosse registrado no arquivo txt as informaçoes de forma que elas ficasssem em 
    ordem de dependencia,*/
package Controle;

import appRevista.Revista;
import appRevista.Artigo;
import appRevista.Edicao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscrevaArquivos {

    private final ArrayList<Revista> revista = arrayBD.getArrayRevista();
    
    public final void escreverTXT(File diretorio, boolean append) throws IOException {
        diretorio.mkdir();
        File file = new File(diretorio, "ArrayBD.txt");
        if (file.exists()) {
            file.delete();
        } else {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Revista res : revista) {
            writer.write("#"+res.getID() + "#" + res.getEditora() + "#" + res.getNome() + "#" + res.getSite()+"#"+".");
            writer.newLine();
            for (Edicao edt : res.getEdicao()) {
                writer.write( "#"+edt.getID() + "#" + edt.getEdicao() + "#" + edt.getNumEdicao()+"#"+".");
                writer.newLine();
                for (Artigo arg : edt.getArtigo()) {
                    writer.write("#"+arg.getID() + "#" + arg.getEscritor()+"#"+".");
                    writer.newLine();
                }
            }
        }

        writer.flush();
        //Fechando conexão e escrita do arquivo.
        writer.close();
    }
}
