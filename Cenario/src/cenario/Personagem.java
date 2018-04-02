/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenario;

import java.util.Random;

/**
 *
 * @author Bruno
 */
public class Personagem {
    private String Nome, TipoPoder, Imagem;
    private int AtaqueBasico, AtaqueEspecial, Vida;
    public Personagem(String Nome, String TipoPoder, int AtaqueBasico, int AtaqueEspecial, int Vida, String Imagem){
        this.Nome = Nome;
        this.TipoPoder = TipoPoder;
        this.AtaqueBasico = AtaqueBasico;
        this.AtaqueEspecial = AtaqueEspecial;
        this.Vida = Vida;
        this.Imagem = Imagem;
    }
    
    public String getNome(){
        return this.Nome;
    }
    public int getVida(){
        return this.Vida;
    }
    public void setVida(int Vida){
        this.Vida = Vida;
    }
    public String getImagem(){
        return this.Imagem;
    }
    public String getTipoPoder(){
        return this.TipoPoder;
    }
    public int getAtaqueBasico(){
        return this.AtaqueBasico;
    }
    public int getAtaqueEspecial(){
        return this.AtaqueEspecial;
    }
    public int AtaqueBasico(){
        Random random = new Random();
        return random.nextInt() * 10;
    }
    public int AtaqueEspecial(){
        Random random = new Random();
        int num = random.nextInt() * 10;
        return num < 2 ? 3 : num;
    }
    public int Defesa(){
        Random random = new Random();
        return random.nextInt() * 10;
    }
}
