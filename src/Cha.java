class Cha implements Bebida {
    @Override
    public void preparar() {
        System.out.println("Preparando chá...");
    }

    @Override
    public String getNome() {
        return "Chá";
    }

    @Override
    public double getPreco() {
        return 2.50;
    }
}