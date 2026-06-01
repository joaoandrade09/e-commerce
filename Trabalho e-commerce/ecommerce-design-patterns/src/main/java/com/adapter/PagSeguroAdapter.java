package com.adapter;

public class PagSeguroAdapter extends ProcessadorPagamento {

    private ServicoPagSeguro servicoPagSeguro;

    public PagSeguroAdapter() {
        this.servicoPagSeguro = new ServicoPagSeguro();
    }

    // Aqui nós implementamos o método que a classe abstrata exige!
    @Override
    protected void executarCobranca(double valor) {
        // O Adapter repassa a chamada para o serviço externo
        servicoPagSeguro.executarPagamento(valor);
    }
}