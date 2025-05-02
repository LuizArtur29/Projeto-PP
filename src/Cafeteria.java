public class Cafeteria {
    public static void main(String[] args) {
        // Criando a fábrica de bebidas
        BebidaFactory fabrica = new BebidaFactory();

        // Criando clientes (observers)
        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");

        // Criando pedidos
        Bebida cafe = fabrica.criarBebida("cafe");
        Pedido pedido1 = new Pedido(cafe);
        pedido1.adicionarObservador(cliente1);

        Bebida cappuccino = fabrica.criarBebida("cappuccino");
        Pedido pedido2 = new Pedido(cappuccino);
        pedido2.adicionarObservador(cliente2);

        // Processando os pedidos
        System.out.println("Processando pedido 1 - " + pedido1.getBebida().getNome());
        pedido1.prepararPedido();

        System.out.println("\nProcessando pedido 2 - " + pedido2.getBebida().getNome());
        pedido2.prepararPedido();

        // Adicionando um novo observador durante o processo
        Cliente cliente3 = new Cliente("Carlos");
        pedido2.adicionarObservador(cliente3);
        pedido2.setStatus("Entregue");
    }
}