package br.edu.ifpb.coffeeshop.drinkmanagement.model.decorator;

import java.util.List;

import br.edu.ifpb.coffeeshop.drinkmanagement.model.Additional;
import br.edu.ifpb.coffeeshop.drinkmanagement.model.Item;

public class Caramelo implements Additional{
    private List<Additional> additionals;
    private Item item;
    public Caramelo(Item item) {
        this.item = item;
        additionals.add(this);
    }

    @Override
    public String getName() {
        return item.getName() + " com Caramelo";
    }

    @Override
    public double getPrice() {
        return item.getPrice() + 0.50;
    }

    @Override
    public List<Additional> getAdditionals() {
        return additionals;
    }

    @Override
    public Additional getAdditional() {
        return this;
    }


}
