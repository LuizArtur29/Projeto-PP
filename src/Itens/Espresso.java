package Itens;

public class Espresso implements Bebida {

    public void preparar() {
        System.out.println("Preparando espresso...");
    }

    @Override
    public String getNome() {
        return "Itens.Espresso";
    }

    @Override
    public double getPreco() {
        return 4.00;
    }
}

