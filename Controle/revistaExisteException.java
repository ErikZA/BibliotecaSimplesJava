/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import javax.swing.JOptionPane;

/**
 *
 * @author erik_
 */
public class revistaExisteException extends Exception {

    public revistaExisteException() {
    }

    public final void revistaExiste() {
        JOptionPane.showMessageDialog(null, "Revista Ja Cadastrada");
    }

    public final void revistaNaoExiste() {
        JOptionPane.showMessageDialog(null, "Revista Não Cadastrada");
    }

    public final void edicaoExiste() {
        JOptionPane.showMessageDialog(null, "Edicao ja Cadastrada");
    }

    public final void edicaoNaoExiste() {
        JOptionPane.showMessageDialog(null, "Edicao Nao Cadastrada");
    }

    public final void artigoExiste() {
        JOptionPane.showMessageDialog(null, "Artigo ja Cadastrada");
    }

    public final void artigoNaoExiste() {
        JOptionPane.showMessageDialog(null, "Artigo Nao Cadastrada");
    }
    
}
