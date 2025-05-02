package Itens;

public class Cafe implements Bebida {
    @Override
    public void preparar() {
        System.out.println("Preparando café...");
    }

    @Override
    public String getNome() {
        return "Café";
    }

    @Override
    public double getPreco() {
        return 3.50;
    }
}