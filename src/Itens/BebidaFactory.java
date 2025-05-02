package Itens;

public class BebidaFactory {
    public Bebida criarBebida(String tipo) {
        switch (tipo.toLowerCase()) {
            case "cafe":
                return new Cafe();
            case "cha":
                return new Cha();
            case "cappuccino":
                return new Cappuccino();
            case "espresso":
                return new Espresso();
            default:
                throw new IllegalArgumentException("Tipo de bebida desconhecido: " + tipo);
        }
    }
}