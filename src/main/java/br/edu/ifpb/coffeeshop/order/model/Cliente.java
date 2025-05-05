package br.edu.ifpb.coffeeshop.order.model;

public class Cliente implements PedidoObserver {

    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void atualizarStatus(Pedido pedido) {
        System.out.println("--------------------------------------------------");
        System.out.println("Notificação para Cliente: " + this.nome);
        System.out.println("O status do seu pedido #" + pedido.getId() + " mudou para: " + pedido.getStatus());
        // poderia adicionar lógica mais complexa, como enviar um email, SMS, etc.
        System.out.println("--------------------------------------------------");
    }


}
