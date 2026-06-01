Markdown
# 🛒 E-commerce API: Design Patterns em Java e Spring Boot

Este repositório contém uma API REST desenvolvida em **Java** com **Spring Boot**, simulando o backend de um sistema de e-commerce. O foco principal deste projeto é a aplicação prática de **Design Patterns (Padrões de Projeto)** do catálogo GoF (Gang of Four) para criar uma arquitetura de software coesa, flexível e de fácil manutenção.

## 🎯 Objetivo do Projeto

O objetivo deste projeto não é apenas construir uma API funcional, mas demonstrar como o uso correto de padrões de projeto pode resolver problemas comuns de design de software, como o alto acoplamento, a violação do princípio de responsabilidade única (SRP) e a dificuldade de extensão de código.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot (Web, Data JPA)
* **Banco de Dados:** H2 Database (In-memory)
* **Testes de API:** Postman

## 📐 Padrões de Projeto Implementados

Nesta arquitetura, implementamos quatro padrões de projeto para resolver diferentes desafios de negócio:

### 1. Strategy (Comportamental)
**Problema:** O cálculo de frete varia dependendo do modal escolhido (Aéreo ou Terrestre). Se colocássemos toda essa lógica na classe `Pedido`, a classe cresceria indefinidamente a cada nova modalidade de entrega.
**Solução:** Implementamos a interface `EstrategiaFrete`. As classes `FreteAereo` e `FreteTerrestre` encapsulam as regras de cálculo. O `Pedido` apenas chama o método `calcular()`, permitindo que a estratégia seja definida e alterada em tempo de execução, respeitando o princípio OCP (Open-Closed Principle).

### 2. State (Comportamental)
**Problema:** Um pedido possui um ciclo de vida (Aguardando Pagamento, Pago, Enviado, Cancelado). Dependendo do estado atual, certas ações não são permitidas (ex: cancelar um pedido já enviado).
**Solução:** O estado do pedido não é apenas uma *String*. Usamos o padrão State, onde cada estado é uma classe que implementa a interface `EstadoPedido`. A transição entre os estados e as validações do que é permitido ou não ocorrem dentro dessas classes de estado, retirando a complexidade (e os vários `if/else`) da classe `Pedido`.

### 3. Observer (Comportamental)
**Problema:** O sistema precisa notificar outras partes da aplicação (ou sistemas externos) quando o status do pedido muda (ex: enviar um e-mail de confirmação). A classe `Pedido` não deve se preocupar em *como* enviar esses avisos.
**Solução:** O `Pedido` atua como um *Subject*, mantendo uma lista de *Observers*. A classe `EmailObserver` se inscreve para ser notificada. Quando o pedido muda de estado, ele notifica todos os observadores registrados, garantindo um baixo acoplamento entre a regra de negócio e os serviços de notificação.

### 4. Adapter & Template Method (Estrutural e Comportamental)
**Problema:** O sistema precisa integrar-se com gateways de pagamento externos (ex: PagSeguro), que possuem interfaces e métodos diferentes dos esperados pelo nosso domínio.
**Solução:** * Criamos a interface abstrata `ProcessadorPagamento`.
* O **Template Method** foi utilizado na classe `ProcessadorPagamento` para definir o fluxo padrão de uma transação (Validação -> Processamento -> Registro de Log).
* O **Adapter** (`PagSeguroAdapter`) implementa nosso `ProcessadorPagamento` e "traduz" as chamadas para o `ServicoPagSeguro` (que simula a API externa). Isso nos permite trocar de gateway de pagamento no futuro sem alterar o código do nosso domínio.

Bash
mvn spring-boot:run
A aplicação estará rodando na porta 8080.

Testando a API
Na pasta documento deste repositório, você encontrará a Collection do Postman contendo todas as requisições configuradas.

Principais Endpoints:

POST /api/pedidos: Cria um novo pedido.

GET /api/pedidos: Lista os pedidos.

PUT /api/pedidos/{id}: Atualiza o pedido (Altera a Strategy de frete).

PUT /api/pedidos/{id}/pagar: Altera o State para PAGO (via Adapter).

PUT /api/pedidos/{id}/enviar: Altera o State para ENVIADO.

PUT /api/pedidos/{id}/cancelar: Altera o State para CANCELADO.
