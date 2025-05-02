class Cappuccino implements Bebida {
    @Override
    public void preparar() {
        System.out.println("Preparando cappuccino...");
    }

    @Override
    public String getNome() {
        return "Cappuccino";
    }

    @Override
    public double getPreco() {
        return 4.50;
    }
}