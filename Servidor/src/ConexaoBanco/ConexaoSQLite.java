/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBanco;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ConexaoSQLite {

    public ConexaoSQLite() {

    }

    public static Connection ConectarNoBanco() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String pathSQLFile = new File(".").getAbsolutePath();
            pathSQLFile = pathSQLFile.replace("\\.", "");
            pathSQLFile += "\\src\\ConexaoBanco\\JogoFAM.db";
            c = DriverManager.getConnection("jdbc:sqlite:" + pathSQLFile);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    public static ResultSet PesquisarNoBanco(String Query) {
        Connection conn = ConectarNoBanco();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            return rs;
        }
    }
    
    public static boolean FazerUmaOperacaoNoBanco(String Query){
        return true;
    }

    public static PersonagemDTO SelecionarPersonagemPeloId(int Id) {
        PersonagemDTO personagem = new PersonagemDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Personagem WHERE Id = " + Integer.toString(Id));
            while (rs.next()) {
                personagem.Id = rs.getInt("Id");
                personagem.Nome = rs.getString("Nome");
                personagem.AtaqueBasico = rs.getInt("AtaqueBasico");
                personagem.AtaqueEspecial = rs.getInt("AtaqueEspecial");
                personagem.ImagemAtaqueBasico = rs.getString("ImagemAtaqueBasico");
                personagem.ImagemAtaqueEspecial = rs.getString("ImagemAtaqueEspecial");
                personagem.TipoPoder = rs.getString("TipoPoder");
                personagem.TipoPoder = rs.getString("Vida");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return personagem;
    }
    
    public static PersonagemDTO SelecionarPersonagemPeloNome(String Nome) {
        PersonagemDTO personagem = new PersonagemDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Personagem WHERE Nome = " + Nome);
            while (rs.next()) {
                personagem.Id = rs.getInt("Id");
                personagem.Nome = rs.getString("Nome");
                personagem.AtaqueBasico = rs.getInt("AtaqueBasico");
                personagem.AtaqueEspecial = rs.getInt("AtaqueEspecial");
                personagem.ImagemAtaqueBasico = rs.getString("ImagemAtaqueBasico");
                personagem.ImagemAtaqueEspecial = rs.getString("ImagemAtaqueEspecial");
                personagem.TipoPoder = rs.getString("TipoPoder");
                personagem.TipoPoder = rs.getString("Vida");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return personagem;
    }
    
    public static JogadorDTO SelecionarJogadorPeloId(int Id) {
        JogadorDTO jogador = new JogadorDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Jogador WHERE Id = " + Id);
            while (rs.next()) {
                jogador.Id = rs.getInt("Id");
                jogador.Personagem = SelecionarPersonagemPeloId(rs.getInt("IdPersonagem"));
                jogador.Nome = rs.getString("Nome");                
                jogador.IP = rs.getString("IP");
                jogador.Porta = rs.getString("Porta");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return jogador;
    }
    
    public static JogadorDTO SelecionarJogadorPeloNome(String Nome) {
        JogadorDTO jogador = new JogadorDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Jogador WHERE Nome = " + Nome);
            while (rs.next()) {
                jogador.Id = rs.getInt("Id");
                jogador.Personagem = SelecionarPersonagemPeloId(rs.getInt("IdPersonagem"));
                jogador.Nome = rs.getString("Nome");                
                jogador.IP = rs.getString("IP");
                jogador.Porta = rs.getString("Porta");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return jogador;
    }
}
