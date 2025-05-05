package br.edu.ifpb.coffeeshop.order.model;


public interface PedidoObserver {

    void atualizarStatus(Pedido pedido);
}
