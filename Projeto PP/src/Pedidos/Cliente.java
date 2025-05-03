package Pedidos;

public class Cliente implements ClienteObserver {
    private final String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String status, int numeroPedido,  String nomeCliente) {
        if (this.nome.equals(nomeCliente)) {
            System.out.printf("%s: Status do pedido atualizado para '%s'\n",
                    nome, status, numeroPedido);
        }
    }

    public String getNome() {
        return nome;
    }
}