package Pedidos;

interface ClienteObserver {
    void atualizar(String statusPedido, int numeroPedido, String nomeCliente);
}