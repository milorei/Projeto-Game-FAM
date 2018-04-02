/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenario;

import javax.swing.JFrame;

/**
 *
 * @author Bruno
 */
public class Jogo {
    public static void main(String[] args){
        JFrame janela;
        Cenario cenario = new Cenario();
        janela = cenario.CadastraJogador();
        janela.setVisible(true);
        
    }
}
