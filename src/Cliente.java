class Cliente implements ClienteObserver {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String statusPedido) {
        System.out.println(nome + ", seu pedido está com status: " + statusPedido);
    }
}