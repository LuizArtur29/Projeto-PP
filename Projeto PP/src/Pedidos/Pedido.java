package Pedidos;

import Itens.Bebida;
import Itens.BebidaFactory;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 0;
    public final int numero;
    private String status;
    private final List<Bebida> itens;
    private final List<ClienteObserver> clientes;
    private String nomeCliente;
    private static final BebidaFactory fabrica = new BebidaFactory();

    public Pedido() {
        this.itens = new ArrayList<>();
        this.numero = ++contadorPedidos;
        this.status = "Recebido";
        this.clientes = new ArrayList<>();
    }

    public void adicionarItem(Bebida bebida) {
        itens.add(bebida);
    }

    public void adicionarObservador(ClienteObserver cliente) {
        clientes.add(cliente);
        this.nomeCliente = ((Cliente) cliente).getNome();
    }

    public void removerObservador(ClienteObserver cliente) {
        clientes.remove(cliente);
    }

    public void notificarObservadores() {
        for (ClienteObserver cliente : clientes) {
            cliente.atualizar(status, numero, nomeCliente);
        }
    }

    public void setStatus(String novoStatus) {
        this.status = novoStatus;
        notificarObservadores();
    }

    public void prepararPedido() {
        setStatus("Em preparo");
        for (Bebida bebida : itens) {
            bebida.preparar();
        }
        setStatus("Pronto");
    }

    public void processarPedido(boolean comAdicional) {
        System.out.println("========== NOVO PEDIDO ==========");
        System.out.println("Pedido #" + this.numero + " - Processando pedido de " + this.getNomeCliente());
        System.out.println("Itens:");
        this.getItens().forEach(item ->
                System.out.println("- " + item.getNome() + " (R$" + item.getPreco() + ")"));

        this.prepararPedido();
        System.out.println("\nTotal: R$" + this.calcularTotal());

        if (comAdicional) {
            System.out.println("\nAdicionando item adicional ao pedido #" + this.numero);
            this.adicionarItem(fabrica.criarBebida("cha"));
            System.out.println("Itens + adicional:");
            this.getItens().forEach(item ->
                    System.out.println("- " + item.getNome() + " (R$" + item.getPreco() + ")"));
            this.setStatus("Pronto");
            System.out.println("\nNovo total: R$" + this.calcularTotal());
        }
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(Bebida::getPreco).sum();
    }

    public String getStatus() {
        return status;
    }

    public List<Bebida> getItens() {
        return itens;
    }

    public static int getTotalPedidos() {
        return contadorPedidos;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

}