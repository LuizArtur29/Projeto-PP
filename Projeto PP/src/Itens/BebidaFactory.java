package Itens;

public class BebidaFactory {
    public Bebida criarBebida(String tipo) {
         switch (tipo.toLowerCase()) {
            case "cafe" : return new Bebida("Café", 3.50,
                    () -> System.out.println("Preparando café... Moendo grãos..."));
            case "cha" : return new Bebida("Chá", 2.50,
                    () -> System.out.println("Preparando chá... Infundindo ervas..."));
            case "cappuccino" : return new Bebida("Cappuccino", 4.50,
                    () -> System.out.println("Preparando cappuccino... Vaporizando leite..."));
             case "espresso" : return new Bebida("Espresso", 3.00,
                     () -> System.out.println("Preparando Espresso... Extraindo o máximo de sabor..."));
            default : throw new IllegalArgumentException("Bebida não disponível: " + tipo);
        }
    }
}