/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenario;

import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
/**
 *
 * @author Bruno
 */
public class Jogo {
    public static void main(String[] args){
        JFrame janela;
        Cenario cenario = new Cenario();
        janela = cenario.SplashScreen();
    
        janela.setVisible(true);
    }
}
