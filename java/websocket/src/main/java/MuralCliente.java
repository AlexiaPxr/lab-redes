import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Scanner;

public class MuralCliente extends WebSocketClient {

    public MuralCliente(URI servidor) {
        super(servidor);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("[WebSocket] Conectado ao mural. Digite 'sair' para encerrar.");
    }

    @Override
    public void onMessage(String mensagem) {
        System.out.println("\n" + mensagem);
        System.out.print("> ");
    }

    @Override
    public void onClose(int codigo, String motivo, boolean remoto) {
        System.out.println("[WebSocket] Conexão encerrada.");
    }

    @Override
    public void onError(Exception excecao) {
        excecao.printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        MuralCliente cliente = new MuralCliente(new URI("ws://localhost:8916"));
        cliente.connect();

        Scanner teclado = new Scanner(System.in);
        while (true) {
            String mensagem = teclado.nextLine();
            if (mensagem.equalsIgnoreCase("sair")) {
                cliente.close();
                break;
            }
            cliente.send(mensagem);
        }
    }
}