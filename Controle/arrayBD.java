/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import appRevista.Revista;
import appRevista.Artigo;
import appRevista.Edicao;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author erik_
 */
public class arrayBD implements Serializable {

    private static ArrayList<Revista> revista = new ArrayList<>();

    public final static ArrayList getArrayRevista() {
        return revista;
    }

    public arrayBD(ArrayList<Revista> revist) {
        this.revista = revist;
    }
    

    public final static void setRevista(ArrayList<Revista> revista) {
        arrayBD.revista = revista;
    }

    public final boolean insereRevista(Revista revista) {
        if (consultaRevista(revista) == null) {
            this.revista.add(revista);
        } else {
            return false;
        }
        return true;
    }

    public final Revista consultaRevista(Revista revista) {
        for (Revista res : this.revista) {
            try {
                if (testaIguaisNome(res.getNome(), revista.getNome()) || testaIguaisID(res.getID(), revista.getID())) {
                    throw new revistaExisteException();
                }
            } catch (revistaExisteException ex) {
                //ex.revistaExiste();
                return res;
            }
        }
        return null;
    }

    public final Edicao consultaEdicao(Edicao edicao, Revista rest) {
        Revista revista = consultaRevista(rest);
        try {
            if (revista == null) {
                throw new revistaExisteException();
            }
        } catch (revistaExisteException ex) {
            ex.revistaNaoExiste();
            return null;
        }
        for (Edicao res : revista.getEdicao()) {
            try {
                if (testaIguaisID(res.getID(), edicao.getID())) {
                    throw new revistaExisteException();
                }
            } catch (revistaExisteException ex) {
                ex.edicaoExiste();
                return res;
            }
        }
        return null;
    }

    public final Artigo consultaArtigo(Artigo artigo, Edicao edi) {
        for (Artigo res : edi.getArtigo()) {
            try {
                if (testaIguaisID(res.getID(), artigo.getID())) {
                    throw new revistaExisteException();
                }
            } catch (revistaExisteException ex) {
                ex.artigoExiste();
                return res;
            }
        }
        return null;
    }

    public final boolean testaIguaisID(int x, int y) {
        return x == y;
    }

    public final boolean testaIguaisNome(String nome, String nome2) {
        return nome.equalsIgnoreCase(nome2);
    }

    public final boolean insereEdicao(Edicao edicao, Revista revista) {
        Revista res = consultaRevista(revista);
        try {
            if (res == null) {
                throw new revistaExisteException();
            }
        } catch (revistaExisteException ex) {
            ex.revistaNaoExiste();
            return false;
        }
        if (consultaEdicao(edicao, res) == null) {
            res.setEdicao(edicao);
            return true;
        }
        return false;
    }

    public final boolean insereArtigo(Edicao edicao, Artigo artigo, Revista rest) {
        Edicao res = consultaEdicao(edicao, rest);
        try {
            if (res == null) {
                throw new revistaExisteException();
            }
        } catch (revistaExisteException ex) {
            ex.edicaoNaoExiste();
            return false;
        }
        if (consultaArtigo(artigo, edicao) == null) {
            res.setArtigo(artigo);
            return true;
        }
        return false;
    }

    public final ArrayList listaRevista(Revista revista) {
        ArrayList<Revista> array = new ArrayList<>();
        for (Revista res : this.revista) {
            try {
                if (res.getID() == revista.getID() || res.getNome().equalsIgnoreCase(revista.getNome())) {
                    array.add(res);
                }
            } catch (NullPointerException ex) {
                continue;

            }

        }
        return array;
    }

    public final ArrayList todasEdicoes() {
        ArrayList<Edicao> edicao = new ArrayList<>();
        for (Revista res : this.revista) {
            for (Edicao edt : res.getEdicao()) {
                edicao.add(edt);
            }
        }
        return edicao;
    }

    public final ArrayList todosArtigos() {
        ArrayList<Artigo> artigo = new ArrayList<>();
        for (Revista res : this.revista) {
            for (Edicao edt : res.getEdicao()) {
                for (Artigo arg : edt.getArtigo()) {
                    artigo.add(arg);
                }
            }
        }
        return artigo;
    }

    public final ArrayList listaEdicao(Edicao edicao) {
        ArrayList<Edicao> array = new ArrayList<>();
        ArrayList<Edicao> edt = todasEdicoes();
        for (Edicao res : edt) {
            try {
                if (res.getID() == edicao.getID() || res.getEdicao().equalsIgnoreCase(edicao.getEdicao())) {
                    array.add(res);
                }
            } catch (NullPointerException ex) {
                continue;

            }
        }
        return array;
    }

    public final ArrayList listaArtigo(Artigo artigo) {
        ArrayList<Artigo> array = new ArrayList<>();
        ArrayList<Artigo> arg = todosArtigos();
        for (Artigo res : arg) {
            try {
                if (res.getID() == artigo.getID() || res.getEscritor().equalsIgnoreCase(artigo.getEscritor())) {
                    array.add(res);
                }
            } catch (NullPointerException ex) {
                continue;

            }
        }
        return array;
    }
    
    public final void atualizaArray(){        
        revista = null;
        new ConexaoBD().atualizaArray();
    }
}
