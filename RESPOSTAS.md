## Parte A — TCP

1. O cliente apresenta um erro de conexão recusada, pois o "new Socket(host, porta)" tenta estabelecer uma conexão, mas não existe um "ServerSocket" ativo aguardando conexões nessa porta. Isso ocorre porque o TCP precisa estabelecer uma conexão por meio do handshake antes de permitir a comunicação.

2. O TCP usa números de sequência nos segmentos enviados. Eles permitem que o receptor reorganize os dados na ordem correta antes de entregá-los à aplicação, mesmo que os segmentos cheguem fora de ordem.

3. Não. O servidor chama "accept()" apenas uma vez e, depois permanece tratando a conexão desse único cliente. Assim, um segundo cliente pode ficar aguardando na fila de conexões (backlog), mas não será atendido pelo código atual. Para suportar múltiplos clientes, seria necessário utilizar um "while" para realizar novos "accept()" e, normalmente, uma thread para cada cliente.


## Parte B — UDP

1. Não. No ClienteUDP, após socket.send(pacoteEnvio), o código executa socket.receive(pacoteResposta) e fica bloqueado indefinidamente esperando uma resposta. Como não há tratamento de timeout ou tentativas de reenvio, o cliente não recebe uma mensagem informando que o pacote foi perdido. Na prática, ele permanece aguardando.

2. O UDP é mais simples porque não estabelece uma conexão persistente e não utiliza mecanismos como accept(), confirmação de recebimento, controle de sequência ou retransmissão. Dessa forma, possui menor overhead e maior velocidade, mas abre mão das garantias de entrega, ordem e confiabilidade oferecidas pelo TCP.

3. O UDP é adequado para aplicações em tempo real, como chamadas de vídeo e jogos online, nas quais é melhor continuar a comunicação do que esperar pela retransmissão de um pacote atrasado. Já o TCP é mais adequado para situações em que a integridade dos dados é essencial, como transferências de arquivos, pois garante a entrega e a ordem dos dados.