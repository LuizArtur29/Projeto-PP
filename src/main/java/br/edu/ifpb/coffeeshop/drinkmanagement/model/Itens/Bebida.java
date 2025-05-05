package br.edu.ifpb.coffeeshop.drinkmanagement.model.Itens;

import br.edu.ifpb.coffeeshop.drinkmanagement.model.Item;

public class Bebida implements Item {
    private final String nome;
    private final double preco;
    private final Preparo preparo;

    @Override
    public String getName() {
        return "";
    }

    @Override
    public double getPrice() {
        return 0;
    }

    public interface Preparo {
        void preparar();
    }

    Bebida(String nome, double preco, Preparo preparo) {
        this.nome = nome;
        this.preco = preco;
        this.preparo = preparo;
    }


    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public void preparar() { preparo.preparar(); }
}