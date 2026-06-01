package com.adapter;

public class ServicoPagSeguro {

    public void executarPagamento(double valor) {
        System.out.println("PagSeguro: Processando pagamento no valor de R$ " + valor);
        System.out.println("PagSeguro: Pagamento aprovado com sucesso!");
    }
}