
import ConexaoBanco.*;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import static java.lang.System.out;
import java.net.Socket;
import java.net.ServerSocket;

public class Servidor {

    // O soquete do servidor.
    private static ServerSocket serverSocket = null;
    // O soquete do cliente .
    private static Socket clienteSocket = null;

    // aqui estou definindo 5 jogadores
    private static final int Quantidadedejogador = 2;
    private static final clientThread[] threads = new clientThread[Quantidadedejogador];

    public static void main(String args[]) {
        
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        PersonagemDTO personagem1 = conexaoSQLite.SelecionarPersonagemPeloId(1);
        PersonagemDTO personagem2 = conexaoSQLite.SelecionarPersonagemPeloId(2);
        PersonagemDTO personagem3 = conexaoSQLite.SelecionarPersonagemPeloId(3);
        
        JogadorDTO jogador1 = conexaoSQLite.SelecionarJogadorPeloId(1);
        JogadorDTO jogador2 = conexaoSQLite.SelecionarJogadorPeloId(2);
        JogadorDTO jogador3 = conexaoSQLite.SelecionarJogadorPeloId(3);        
        
        // servidor consultando se o bando de dados ja esta conectado
        out.println(ConexaoMySQL.statusConection());

        // chamando o metodo para conectar ao banco de dados
        ConexaoMySQL.getConexaoMySQL();
        out.println(ConexaoMySQL.statusConection());

        // porta padrao.
        int Numerodaporta = 2222;
        if (args.length < 1) {
            System.out
                    .println("porta conectada " + Numerodaporta);
        } else {
            Numerodaporta = Integer.valueOf(args[0]).intValue();
        }

        try {
            serverSocket = new ServerSocket(Numerodaporta);
        } catch (IOException e) {
            System.out.println(e);
        }

        /*
   
      Crie um soquete de cliente para cada conexão e passe-o para um novo cliente
     * 
         */
        while (true) {
            try {
                clienteSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < Quantidadedejogador; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new clientThread(clienteSocket, threads)).start();
                        break;
                    }
                }
                if (i == Quantidadedejogador) {
                    PrintStream saida = new PrintStream(clienteSocket.getOutputStream());
                    saida.println("Servidor ocupado tente mais tarde");
                    saida.close();
                    clienteSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

class clientThread extends Thread {

    //ler dados
    private DataInputStream entrada = null;
    //metodo de saida de dados
    private PrintStream saida = null;

    private Socket clienteSocket = null;
    private final clientThread[] threads;
    private int Quantidadedejogador;

    public clientThread(Socket clienteSocket, clientThread[] threads) {
        this.clienteSocket = clienteSocket;
        this.threads = threads;
        Quantidadedejogador = threads.length;
    }

    @Override
    public void run() {
        int Quantidadedejogador = this.Quantidadedejogador;
        clientThread[] threads = this.threads;

        try {
            /*
       * Crie fluxos de entrada e saída para este cliente.
             */
            entrada = new DataInputStream(clienteSocket.getInputStream());
            saida = new PrintStream(clienteSocket.getOutputStream());
            saida.println("Digite seu nome.");
            String name = entrada.readLine().trim();
            saida.println("nome do jogador: " + name);
            for (int i = 0; i < Quantidadedejogador; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].saida.println("o jogador: " + name +" entrou no jogo");
                }
            }
            while (true) {
                String line = entrada.readLine();
                if (line.startsWith("/quit")) {
                    break;
                }
                for (int i = 0; i < Quantidadedejogador; i++) {
                    if (threads[i] != null) {
                        threads[i].saida.println("<" + name + "&gr; " + line);
                    }
                }
            }
            for (int i = 0; i < Quantidadedejogador; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].saida.println("o jogador " + name
                            + " saiu do jogo ");
                }
            }
            saida.println("thau " + name + " ");

            /*
       * Limpar. Definir a variável de thread atual como null para que um novo jogador 
       * possa ser aceito pelo servidor.
             */
            for (int i = 0; i < Quantidadedejogador; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }

            /*
       * 
  Fechando o fluxo de saída, feche o fluxo de entrada, feche o soquete.
             */
            entrada.close();
            saida.close();
            clienteSocket.close();
        } catch (IOException e) {
        }
    }
}
