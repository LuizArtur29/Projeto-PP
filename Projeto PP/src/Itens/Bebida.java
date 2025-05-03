package Itens;

public class Bebida {
    public static BebidaFactory Factory;
    private final String nome;
    private final double preco;
    private final Preparo preparo;

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
