import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int porta = 5030;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress endereco = InetAddress.getByName(host);
            Scanner teclado = new Scanner(System.in);
            byte[] buffer = new byte[1024];

            System.out.println("[UDP] Pronto para enviar. Digite 'sair' para encerrar.");
            while (true) {
                System.out.print("> ");
                String mensagem = teclado.nextLine();
                byte[] dadosEnvio = mensagem.getBytes();
                DatagramPacket pacoteEnvio = new DatagramPacket(dadosEnvio, dadosEnvio.length, endereco, porta);
                socket.send(pacoteEnvio);

                if (mensagem.equalsIgnoreCase("sair")) break;

                DatagramPacket pacoteResposta = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacoteResposta);
                String resposta = new String(pacoteResposta.getData(), 0, pacoteResposta.getLength());
                System.out.println(resposta);
            }
        }
    }
}