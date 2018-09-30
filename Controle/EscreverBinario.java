/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import appRevista.Revista;
import appRevista.Artigo;
import appRevista.Edicao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author erik_
 *
 * Foi criado tres laços, um dentro do outro para que fosse registrado no arquivo txt as informaçoes de forma que elas ficasssem em 
 * ordem de dependencia. Apos gerar o arquivo txt e feita a leitura do mesmo e todos os dados são armazenados em um Array de String.
 * Depois de armazenado esse arrai e gravado em arquivo binario;
 */


public class EscreverBinario {

    private final ArrayList<Revista> revista = arrayBD.getArrayRevista();
    private final String arq = "ArrayBD.txt";
    private final ArrayList<String> lista = new ArrayList<>();

    public final void escreveBinario(File diretorio, boolean check) throws Exception {
        preparaLista(diretorio);
        ObjectOutputStream out;
        File file = new File(diretorio, "ArrayBD.obt");

        FileOutputStream fos = new FileOutputStream(file);
        out = new ObjectOutputStream(fos);

        out.writeObject(lista);
        out.flush();
        new File(diretorio, arq).delete();
    }

    public final void preparaLista(File diretorio) throws Exception {
        File file = new File(diretorio, arq);
        if (file.exists()) {
            file.delete();
        } else {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        //writer.write("Arquivo gravado em : " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));		
        //writer.newLine();
        for (Revista res : revista) {
            writer.write("#" + res.getID() + "#" + res.getEditora() + "#" + res.getNome() + "#" + res.getSite() + "#" + ".");
            writer.newLine();
            for (Edicao edt : res.getEdicao()) {
                writer.write("#" + edt.getID() + "#" + edt.getEdicao() + "#" + edt.getNumEdicao() + "#" + ".");
                writer.newLine();
                for (Artigo arg : edt.getArtigo()) {
                    writer.write("#" + arg.getID() + "#" + arg.getEscritor() + "#" + ".");
                    writer.newLine();
                }
            }
        }
        writer.flush();
        //Fechando conexão e escrita do arquivo.
        writer.close();
        //////        
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String linha = reader.readLine();
        int i = -1;
        int x = -1;
        while (linha != null) {

            lista.add(linha);
            linha = reader.readLine();

        }
        fileReader.close();
        reader.close();
    }
}
