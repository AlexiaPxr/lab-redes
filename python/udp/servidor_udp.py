import socket
from datetime import datetime

HOST = "0.0.0.0"
PORTA = 5030

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as servidor:
    servidor.bind((HOST, PORTA))
    print(f"[UDP] Servidor aguardando datagramas na porta {PORTA}...")

    while True:
        try:
            dados, endereco = servidor.recvfrom(1024)
        except ConnectionResetError:
            continue

        mensagem = dados.decode("utf-8")
        print(f"[UDP] Recebido de {endereco}: {mensagem}")

        if mensagem.lower() == "hora":
            resposta = f"Horário do servidor: {datetime.now()}"
        else:
            resposta = f'Monitor responde: recebi sua mensagem -> "{mensagem}"'

        servidor.sendto(resposta.encode("utf-8"), endereco)