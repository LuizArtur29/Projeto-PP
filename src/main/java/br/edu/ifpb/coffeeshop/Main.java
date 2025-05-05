
package br.edu.ifpb.coffeeshop;

import br.edu.ifpb.coffeeshop.drinkmanagement.model.Itens.Bebida;
import br.edu.ifpb.coffeeshop.drinkmanagement.model.Itens.BebidaFactory;
import br.edu.ifpb.coffeeshop.drinkmanagement.model.Item;
import br.edu.ifpb.coffeeshop.order.model.Cliente;
import br.edu.ifpb.coffeeshop.order.model.Pedido;


public class Main {
    public static void main(String[] args) {
        BebidaFactory fabrica = new BebidaFactory();

        Cliente joao = new Cliente("João");

        Item cafe = fabrica.criarBebida("cafe");

        Pedido pedido1 = new Pedido(joao, cafe);
        pedido1.addObserver(joao);
        pedido1.adicionarItem((Bebida) cafe);
    }
}