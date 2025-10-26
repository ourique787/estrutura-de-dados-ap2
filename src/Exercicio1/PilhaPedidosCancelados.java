package Exercicio1;

public class PilhaPedidosCancelados {
    private No<Pedido> topo;

    public PilhaPedidosCancelados() {
        this.topo = null;
    }

    public void empilhar(Pedido pedido) {
        No<Pedido> novoNo = new No<>(pedido);
        novoNo.setProximo(topo);
        topo = novoNo;
    }

    public Pedido desempilhar() {
        if (topo == null) {
            return null;
        }
        Pedido pedido = topo.getDado();
        topo = topo.getProximo();
        return pedido;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void imprimirPilha() {
        if (topo == null) {
            System.out.println("Nenhum pedido cancelado.");
            return;
        }

        No<Pedido> atual = topo;
        while (atual != null) {
            System.out.println(atual.getDado());
            atual = atual.getProximo();
        }
    }
}
