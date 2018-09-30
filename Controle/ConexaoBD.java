/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import appRevista.Artigo;
import appRevista.Edicao;
import appRevista.Revista;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author erik_
 */
public class ConexaoBD {

    private static Connection connection = null;
    private Statement stdados = null;
    private ResultSet rsdados = null;
    private PreparedStatement pstdados = null;
    private CallableStatement cstdados = null;

    public ConexaoBD() {
        try {
            String usuario = "root";
            String senha = "12345";

            Class.forName("com.mysql.jdbc.Driver");
            String urlconexao = "jdbc:mysql://localhost/bancorevista";
            connection = DriverManager.getConnection(urlconexao, usuario, senha);
            connection.setAutoCommit(false);

        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC/ODBC." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
    }

    public final void atualizacao(){
            try {
            connection.rollback();
        } catch (SQLException erro) {
            System.out.println("Erro ao atualzar = " + erro);
        }
    }
    public final void salvaNoBanco() {
        try {
            connection.commit();
        } catch (SQLException erro) {
            System.out.println("Erro commit = " + erro);
        }
    }

    public final boolean insereRevista(Revista revista) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            cstdados = connection.prepareCall("{call insere_revista(?,?,?,?)}", tipo, concorrencia);
            cstdados.setInt(1, revista.getID());
            cstdados.setString(2, revista.getNome());
            cstdados.setString(3, revista.getSite());
            cstdados.setString(4, revista.getEditora());
            cstdados.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException x) {
            JOptionPane.showMessageDialog(null, "Já existe um registro com esse nome/n" + x.getMessage());
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir revista = " + erro.getMessage());
            return false;
        }
        return true;
    }

    public final boolean insereEdicao(Revista revista, Edicao edicao) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            // stdados = connection.createStatement(tipo, concorrencia);
            //rsdados = stdados.executeQuery("select Nome_Revisrta from Revista where ID_Revista = "+revista.getID()+";");
            cstdados = connection.prepareCall("{call insere_edicao(?,?,?,?)}", tipo, concorrencia);
            cstdados.setInt(1, edicao.getID());
            cstdados.setString(2, revista.getNome());
            cstdados.setString(3, edicao.getEdicao());
            cstdados.setInt(4, edicao.getNumEdicao());
            cstdados.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir revista = " + erro.getMessage());
            return false;
        }
        return true;
    }

    public final ArrayList carregaBanco() {
        ArrayList<Revista> array = new ArrayList<>();
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            cstdados = connection.prepareCall("{call carrega_banco()}", tipo, concorrencia);
            ResultSet rs = cstdados.executeQuery();
            if (!rs.first()) {
                JOptionPane.showMessageDialog(null, "O ResultSet esta vazio.");
                return null;
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            do {
                array.add(ProximoRegistroRevista(rs, rsmd));
            } while (rs.next());
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir banco = " + erro.getMessage());
            return null;
        }
        return carregaBancoArtigo(carregaBancoEdicao(array));
    }

    private final Revista ProximoRegistroRevista(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        Revista revista = new Revista();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            //A classe Types eh uma classo do pacote java.sql
            if (rsmd.getColumnName(i).equalsIgnoreCase("ID_Revista")) {//para string
                revista.setID(Integer.parseInt(rs.getString(i)));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("Nome_Revista")) {//para inteiros
                revista.setNome(rs.getString(i));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("Site_Revista")) {
                //tratamento para os tipos que serao lidos do banco de dados.
                revista.setSite(rs.getString(i));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("Editora_Revista")) {
                revista.setEditora(rs.getString(i));
            }
        }
        return revista;
    }

    public final boolean insereArtigo(Revista revista, Edicao edicao, Artigo artigo) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            // stdados = connection.createStatement(tipo, concorrencia);
            //rsdados = stdados.executeQuery("select Nome_Revisrta from Revista where ID_Revista = "+revista.getID()+";");
            cstdados = connection.prepareCall("{call insere_artigo(?,?,?)}", tipo, concorrencia);
            cstdados.setInt(1, artigo.getID());
            cstdados.setInt(2, edicao.getID());
            cstdados.setString(3, artigo.getEscritor());
            cstdados.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir revista = " + erro.getMessage());
            return false;
        }
        return true;
    }

    private final ArrayList carregaBancoEdicao(ArrayList<Revista> array) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            cstdados = connection.prepareCall("{call carrega_edicao()}", tipo, concorrencia);
            ResultSet rs = cstdados.executeQuery();
            if (!rs.first()) {
                JOptionPane.showMessageDialog(null, "O ResultSet esta vazio.");
                return null;
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            do {
                ProximoRegistroEdicao(rs, rsmd, array);
            } while (rs.next());
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir banco = " + erro.getMessage());
            return null;
        }
        return array;
    }

    private final void ProximoRegistroEdicao(ResultSet rs, ResultSetMetaData rsmd, ArrayList<Revista> array) throws SQLException {
        Edicao edicao = new Edicao();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            //A classe Types eh uma classo do pacote java.sql
            if (rsmd.getColumnName(i).equalsIgnoreCase("ID_Edicao")) {//para string
                edicao.setID(Integer.parseInt(rs.getString(i)));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("Lancamento_Edicao")) {//para inteiros
                edicao.setEdicao(rs.getString(i));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("Num_Edicao")) {
                //tratamento para os tipos que serao lidos do banco de dados.
                edicao.setNumEdicao(Integer.parseInt(rs.getString(i)));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("Nome_Revista")) {
                for (Revista ret : array) {
                    if (ret.getNome().equalsIgnoreCase(rs.getString(i))) {
                        ret.setEdicao(edicao);
                    }
                }
            }
        }
    }

    private final ArrayList carregaBancoArtigo(ArrayList<Revista> array) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            cstdados = connection.prepareCall("{call carrega_artigo()}", tipo, concorrencia);
            ResultSet rs = cstdados.executeQuery();
            if (!rs.first()) {
                JOptionPane.showMessageDialog(null, "O ResultSet esta vazio.");
                return null;
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            do {
                ProximoRegistroArtigo(rs, rsmd, array);
            } while (rs.next());
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir banco = " + erro.getMessage());
            return null;
        }
        return array;
    }

    private final void ProximoRegistroArtigo(ResultSet rs, ResultSetMetaData rsmd, ArrayList<Revista> array) throws SQLException {
        Artigo artigo = new Artigo();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            //System.out.println("/n"+rs.getString(i));
            //A classe Types eh uma classo do pacote java.sql
            if (rsmd.getColumnName(i).equalsIgnoreCase("ID_Artigo")) {//para string
                artigo.setID(Integer.parseInt(rs.getString(i)));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("Escritor_Artigo")) {//para inteiros
                artigo.setEscritor(rs.getString(i));
            } else if (rsmd.getColumnName(i).equalsIgnoreCase("ID_Edicao")) {
                for (Revista res : array) {
                    for (Edicao edi : res.getEdicao()) {

                        if (edi.getID() == Integer.parseInt(rs.getString(i))) {
                            edi.setArtigo(artigo);

                        }
                    }
                }
            }
        }
    }

    public final String excluiArtigo(Revista res) throws SQLException {
        int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
        int concorrencia = ResultSet.CONCUR_UPDATABLE;
        cstdados = connection.prepareCall("{call exclui_registro_artigo(?,?)}", tipo, concorrencia);
        cstdados.setInt(1, res.getID());
        cstdados.setString(2, res.getNome());
        ResultSet rs = cstdados.executeQuery();
        //System.out.println(""+rs.toString() );
        return rs.toString();
    }

    public final String excluiEdicao(Revista res) throws SQLException {
        int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
        int concorrencia = ResultSet.CONCUR_UPDATABLE;
        cstdados = connection.prepareCall("{call excluie_registro_edicao(?,?)}", tipo, concorrencia);
        cstdados.setInt(1, res.getID());
        cstdados.setString(2, res.getNome());
        ResultSet rs = cstdados.executeQuery();
        //System.out.println(""+rs.toString() );
        return rs.toString();
    }

    public final String excluiRevista(Revista res) throws SQLException {
        
        int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
        int concorrencia = ResultSet.CONCUR_UPDATABLE;
        cstdados = connection.prepareCall("{call exclui_registro_revista(?,?)}", tipo, concorrencia);
        cstdados.setInt(1, res.getID());
        cstdados.setString(2, res.getNome());
        ResultSet rs = cstdados.executeQuery();
        //System.out.println(""+rs.toString() );
        return rs.toString();
    }
    
    public final void atualizaArray(){
        try{
        connection.close();
         new ConexaoBD();
        }catch(SQLException erro){
             System.out.println("Erro ao atualizar " + erro.getMessage());
        }
    }
}
