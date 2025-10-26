package Exercicio1;

public class FilaPedidosPendentes {
    private No<Pedido> frente;
    private No<Pedido> tras;

    public FilaPedidosPendentes() {
        this.frente = this.tras = null;
    }

    public void enfileirar(Pedido pedido) {
        No<Pedido> novoNo = new No<>(pedido);
        if (tras == null) {
            frente = tras = novoNo;
            return;
        }
        tras.setProximo(novoNo);
        tras = novoNo;
    }

    public Pedido desenfileirar() {
        if (frente == null) {
            return null;
        }
        Pedido pedido = frente.getDado();
        frente = frente.getProximo();
        if (frente == null) {
            tras = null;
        }
        return pedido;
    }

    public boolean estaVazia() {
        return frente == null;
    }

    public void imprimirFila() {
        if (frente == null) {
            System.out.println("Nenhum pedido pendente.");
            return;
        }

        No<Pedido> atual = frente;
        while (atual != null) {
            System.out.println(atual.getDado());
            atual = atual.getProximo();
        }
    }
}
