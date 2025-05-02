import Itens.Cafe;
import Itens.Cappuccino;
import Itens.Cha;
import Pedidos.Cliente;
import Pedidos.Pedido;

public class Cafeteria {
    public static void main(String[] args) {
        // Criando clientes
        Cliente joao = new Cliente("João");
        Cliente maria = new Cliente("Maria");

        // Criando e processando pedido 1
        Pedido pedido1 = new Pedido();
        pedido1.adicionarObservador(joao);
        pedido1.adicionarItem(new Cafe());
        pedido1.adicionarItem(new Cha());
        pedido1.processarPedido(false);

        // Criando e processando pedido 2
        Pedido pedido2 = new Pedido();
        pedido2.adicionarObservador(maria);
        pedido2.adicionarItem(new Cappuccino());
        pedido2.adicionarItem(new Cafe());
        pedido2.processarPedido(true);

        System.out.println("\nTotal de pedidos realizados: " + Pedido.getTotalPedidos());
    }
}