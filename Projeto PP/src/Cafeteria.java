import Itens.Bebida;
import Itens.BebidaFactory;
import Pedidos.Cliente;
import Pedidos.Pedido;

public class Cafeteria {
    public static void main(String[] args) {
        BebidaFactory fabrica = new BebidaFactory();

        // Criando clientes
        Cliente joao = new Cliente("João");
        Cliente maria = new Cliente("Maria");

        // Criando bebidas usando a fábrica
        Bebida cafe = fabrica.criarBebida("cafe");
        Bebida cha = fabrica.criarBebida("cha");
        Bebida cappuccino = fabrica.criarBebida("cappuccino");
        Bebida espresso = fabrica.criarBebida("espresso");

        // Criando e processando pedido 1
        Pedido pedido1 = new Pedido();
        pedido1.adicionarObservador(joao);
        pedido1.adicionarItem(cafe);
        pedido1.adicionarItem(cha);
        pedido1.adicionarItem(espresso);
        pedido1.processarPedido(false);

        // Criando e processando pedido 2
        Pedido pedido2 = new Pedido();
        pedido2.adicionarObservador(maria);
        pedido2.adicionarItem(cafe);
        pedido2.adicionarItem(cappuccino);
        pedido2.processarPedido(true);

        System.out.println("\nTotal de pedidos realizados: " + Pedido.getTotalPedidos());
    }
}