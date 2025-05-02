import java.util.ArrayList;
import java.util.List;

class Pedido {
    private String status;
    private List<ClienteObserver> clientes = new ArrayList<>();
    private Bebida bebida;

    public Pedido(Bebida bebida) {
        this.bebida = bebida;
        this.status = "Recebido";
    }

    public void adicionarObservador(ClienteObserver cliente) {
        clientes.add(cliente);
    }

    public void removerObservador(ClienteObserver cliente) {
        clientes.remove(cliente);
    }

    public void notificarObservadores() {
        for (ClienteObserver cliente : clientes) {
            cliente.atualizar(this.status);
        }
    }

    public void setStatus(String novoStatus) {
        this.status = novoStatus;
        notificarObservadores();
    }

    public void prepararPedido() {
        setStatus("Em preparo");
        bebida.preparar();
        setStatus("Pronto");
    }

    public String getStatus() {
        return status;
    }

    public Bebida getBebida() {
        return bebida;
    }
}