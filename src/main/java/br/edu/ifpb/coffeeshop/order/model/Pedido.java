package br.edu.ifpb.coffeeshop.order.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifpb.coffeeshop.drinkmanagement.model.Item;
import br.edu.ifpb.coffeeshop.drinkmanagement.model.Itens.Bebida;
import br.edu.ifpb.coffeeshop.drinkmanagement.model.Itens.BebidaFactory;


public class Pedido {


    private static long contadorPedidos = 1; // Começa em 1 para o primeiro pedido

    private final long id; // ID final do pedido
    private final Cliente cliente;
    private final Item bebidaFinal;
    private StatusPedido status;
    private final List<Bebida> bebidas;
    private List<PedidoObserver> observers;
    private static final BebidaFactory fabrica = new BebidaFactory();



    // Construtor - Recebe o cliente e o pedido
    public Pedido(Cliente cliente, Item bebidaFinal) {
        // Atribui o ID sequencial atual e incrementa o contador para o próximo
        this.bebidas = new ArrayList<>();
        this.id = contadorPedidos++;
        this.status = StatusPedido.PENDENTE;
        this.cliente = Objects.requireNonNull(cliente, "Cliente não pode ser nulo");
        this.bebidaFinal = Objects.requireNonNull(bebidaFinal, "Bebida (Item) não pode ser nula");
        this.observers = new ArrayList<>();

        if (cliente instanceof PedidoObserver) {
            this.addObserver((PedidoObserver) cliente);
            System.out.println(">>> Cliente '" + cliente.getNome() + "' registrado como observer do Pedido #" + this.id);
        } else {
            System.out.println(">>> Cliente '" + cliente.getNome() + "' NÃO implementa PedidoObserver. Não foi registrado automaticamente.");
        }

        // Log de criação
        System.out.println(">>> Pedido #" + this.id + " criado para " + cliente.getNome() + " com item: '" + bebidaFinal.getName() + "'");
    }

    // --- Métodos do Padrão Observer ---

    public void addObserver(PedidoObserver observer) {
        // Renomeei o método para seguir a convenção Java e evitar conflito com atributo
        if (observer != null && !this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    public void removeObserver(PedidoObserver observer) {
        // Renomeei o método
        this.observers.remove(observer);
    }

    /**
     * Notifica todos os observers registrados sobre uma mudança no estado do pedido.
     * Chama o método 'update' (ou 'atualizarStatus' se for esse o nome na sua interface PedidoObserver).
     */
    private void notifyObservers() {
        // Cria cópia para evitar ConcurrentModificationException
        List<PedidoObserver> observersCopy = new ArrayList<>(this.observers);
        System.out.println("\nNotificando " + observersCopy.size() + " observer(s) para pedido #" + this.id + "...");

        for (PedidoObserver observer : observersCopy) {
            observer.atualizarStatus(this);
        }
    }


    // --- Métodos do Pedido (sem alterações, exceto o ID agora é sequencial) ---

    public long getId() {
        return id;
    }

    public void adicionarItem(Bebida bebida) {
        bebidas.add(bebida);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Item getBebidaFinal() {
        return bebidaFinal;
    }

    public StatusPedido getStatus() {
        return status;
    }


    public void setStatus(StatusPedido novoStatus) {
        if (novoStatus == null) {
            System.out.println("Pedido #" + this.id + ": Tentativa de definir status nulo ignorada.");
            return; // Sai do método se o novo status for nulo
        }

        if (novoStatus != this.status) {
            System.out.println("Pedido #" + this.id + ": Status mudando de '" + this.status + "' para '" + novoStatus + "'.");
            this.status = novoStatus;
            notifyObservers(); // Notifica apenas se o status realmente mudou
        } else {
            System.out.println("Pedido #" + this.id + ": Status já é '" + novoStatus + "'. Nenhuma notificação necessária.");
        }
    }

    public void prepararPedido() {
        setStatus(status);
        for (Bebida bebida : bebidas) {
            bebida.preparar();
        }
        setStatus(status);
    }

    public double getCustoTotal() {
        // O custo é obtido diretamente do Item final (que já inclui adicionais)
        return (this.bebidaFinal != null) ? this.bebidaFinal.getPrice() : 0.0;
    }

    @Override
    public String toString() {
        String nomeCliente = (cliente != null) ? cliente.getNome() : "N/A";
        String descBebida = "N/A";
        double precoBebida = 0.0; // <<< CORREÇÃO: Inicializa com 0.0 (um valor double) em vez de "N/A"

        // Se a bebida final existir, pega o nome e o preço (double) dela
        if (bebidaFinal != null) {
            descBebida = bebidaFinal.getName(); // Usa getName() do Item
            precoBebida = bebidaFinal.getPrice(); // Usa getPrice() do Item (que retorna double)
        }

        // Usa String.format para montar a string final.
        // Os formatadores %.2f esperam receber valores double.
        return String.format(
                "Pedido #%d [Cliente: %s] [Status: %s]\n" +
                        "  Item Final: %s (R$ %.2f)\n" +       // Mostra o item único final
                        "  Custo Total: R$ %.2f",              // Mostra o custo total
                this.id,
                nomeCliente,
                this.status,
                descBebida,
                precoBebida, // Passa a variável double 'precoBebida'
                this.getCustoTotal() // Chama o método getCustoTotal() que retorna double
        );
    }


}
