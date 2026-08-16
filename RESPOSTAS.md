## Parte A — TCP

1. O cliente apresenta um erro de conexão recusada, pois o "new Socket(host, porta)" tenta estabelecer uma conexão, mas não existe um "ServerSocket" ativo aguardando conexões nessa porta. Isso ocorre porque o TCP precisa estabelecer uma conexão por meio do handshake antes de permitir a comunicação.

2. O TCP usa números de sequência nos segmentos enviados. Eles permitem que o receptor reorganize os dados na ordem correta antes de entregá-los à aplicação, mesmo que os segmentos cheguem fora de ordem.

3. Não. O servidor chama "accept()" apenas uma vez e, depois permanece tratando a conexão desse único cliente. Assim, um segundo cliente pode ficar aguardando na fila de conexões (backlog), mas não será atendido pelo código atual. Para suportar múltiplos clientes, seria necessário utilizar um "while" para realizar novos "accept()" e, normalmente, uma thread para cada cliente.


## Parte B — UDP

1. Não. No ClienteUDP, após socket.send(pacoteEnvio), o código executa socket.receive(pacoteResposta) e fica bloqueado indefinidamente esperando uma resposta. Como não há tratamento de timeout ou tentativas de reenvio, o cliente não recebe uma mensagem informando que o pacote foi perdido. Na prática, ele permanece aguardando.

2. O UDP é mais simples porque não estabelece uma conexão persistente e não utiliza mecanismos como accept(), confirmação de recebimento, controle de sequência ou retransmissão. Dessa forma, possui menor overhead e maior velocidade, mas abre mão das garantias de entrega, ordem e confiabilidade oferecidas pelo TCP.

3. O UDP é adequado para aplicações em tempo real, como chamadas de vídeo e jogos online, nas quais é melhor continuar a comunicação do que esperar pela retransmissão de um pacote atrasado. Já o TCP é mais adequado para situações em que a integridade dos dados é essencial, como transferências de arquivos, pois garante a entrega e a ordem dos dados.


## Parte C — Multicast

1. No unicast, o servidor precisa enviar um pacote separado para cada cliente, fazendo com que a duplicação do tráfego aconteça na origem. No multicast, o servidor envia um único pacote para o grupo, e os roteadores da rede fazem a replicação quando necessário. Assim, o multicast reduz a carga no servidor e torna o tráfego mais eficiente para muitos destinatários.

2. TTL (Time To Live) é um contador de saltos. A cada roteador atravessado, seu valor é reduzido em 1 e, quando chega a zero, o pacote é descartado. No código, IP_MULTICAST_TTL = 2 limita o pacote a dois saltos. Isso é importante no multicast para limitar o alcance do tráfego e evitar que ele se espalhe por redes além do necessário.

3. Não. O cliente que entrar no grupo depois do envio não recebe as mensagens anteriores, pois o multicast não mantém histórico ou fila de mensagens. Os pacotes são enviados em tempo real e, se o cliente não estiver inscrito no momento do envio, ele perde aquela mensagem.