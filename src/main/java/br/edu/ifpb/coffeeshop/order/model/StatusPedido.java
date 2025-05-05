package br.edu.ifpb.coffeeshop.order.model;

public enum StatusPedido {
    PENDENTE,    // Order received, waiting for stock confirmation/preparation
    PREPARANDO,  // Order confirmed, being prepared
    PRONTO,      // Order ready for pickup/delivery
    ENTREGUE,    // Order delivered/picked up
    CANCELADO    // Order cancelled (e.g., insufficient stock, customer request)
}

