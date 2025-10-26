package Exercicio2;

public class ListaDuplamenteLigada<T> {
    private NoDuplo<T> inicio;
    private NoDuplo<T> fim;
    private NoDuplo<T> atual;
    private int tamanho;

    public ListaDuplamenteLigada() {
        this.inicio = null;
        this.fim = null;
        this.atual = null;
        this.tamanho = 0;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }


    public void adicionarInicio(T dado) {
        NoDuplo<T> novoNo = new NoDuplo<>(dado);

        if (estaVazia()) {
            inicio = fim = atual = novoNo;
        } else {
            novoNo.setProximo(inicio);
            inicio.setAnterior(novoNo);
            inicio = novoNo;
        }
        tamanho++;
    }


    public void adicionarFim(T dado) {
        NoDuplo<T> novoNo = new NoDuplo<>(dado);

        if (estaVazia()) {
            inicio = fim = atual = novoNo;
        } else {
            fim.setProximo(novoNo);
            novoNo.setAnterior(fim);
            fim = novoNo;
        }
        tamanho++;
    }


    public boolean adicionarPosicao(T dado, int posicao) {
        if (posicao < 1 || posicao > tamanho + 1) {
            return false;
        }

        if (posicao == 1) {
            adicionarInicio(dado);
            return true;
        }

        if (posicao == tamanho + 1) {
            adicionarFim(dado);
            return true;
        }

        NoDuplo<T> novoNo = new NoDuplo<>(dado);
        NoDuplo<T> temp = inicio;

        for (int i = 1; i < posicao - 1; i++) {
            temp = temp.getProximo();
        }

        novoNo.setProximo(temp.getProximo());
        novoNo.setAnterior(temp);
        temp.getProximo().setAnterior(novoNo);
        temp.setProximo(novoNo);

        tamanho++;
        return true;
    }


    public boolean removerPorTitulo(String titulo) {
        if (estaVazia()) {
            return false;
        }

        NoDuplo<T> temp = inicio;

        while (temp != null) {
            if (temp.getDado() instanceof Musica) {
                Musica musica = (Musica) temp.getDado();
                if (musica.getTitulo().equalsIgnoreCase(titulo)) {
                    return removerNo(temp);
                }
            }
            temp = temp.getProximo();
        }

        return false;
    }


    public T removerPorPosicao(int posicao) {
        if (posicao < 1 || posicao > tamanho) {
            return null;
        }

        NoDuplo<T> temp = inicio;

        for (int i = 1; i < posicao; i++) {
            temp = temp.getProximo();
        }

        T dadoRemovido = temp.getDado();
        removerNo(temp);
        return dadoRemovido;
    }

    private boolean removerNo(NoDuplo<T> no) {
        if (no == null) {
            return false;
        }


        if (no == inicio && no == fim) {
            inicio = fim = atual = null;
        } else if (no == inicio) {
            inicio = inicio.getProximo();
            inicio.setAnterior(null);
            if (atual == no) {
                atual = inicio;
            }
        } else if (no == fim) {
            fim = fim.getAnterior();
            fim.setProximo(null);
            if (atual == no) {
                atual = fim;
            }
        } else {
            no.getAnterior().setProximo(no.getProximo());
            no.getProximo().setAnterior(no.getAnterior());
            if (atual == no) {
                atual = no.getProximo();
            }
        }

        tamanho--;
        return true;
    }


    public T proximo() {
        if (atual == null || atual.getProximo() == null) {
            return null;
        }
        atual = atual.getProximo();
        return atual.getDado();
    }


    public T anterior() {
        if (atual == null || atual.getAnterior() == null) {
            return null;
        }
        atual = atual.getAnterior();
        return atual.getDado();
    }

    public T getAtual() {
        return atual != null ? atual.getDado() : null;
    }


    public void listar() {
        if (estaVazia()) {
            System.out.println("Lista vazia.");
            return;
        }

        NoDuplo<T> temp = inicio;
        int posicao = 1;

        while (temp != null) {
            String marcador = (temp == atual) ? "► " : "  ";
            System.out.println(marcador + posicao + ". " + temp.getDado());
            temp = temp.getProximo();
            posicao++;
        }
    }


    public void ordenarPorTitulo() {
        if (tamanho < 2) {
            return;
        }

        boolean trocou;
        do {
            trocou = false;
            NoDuplo<T> temp = inicio;

            while (temp.getProximo() != null) {
                if (temp.getDado() instanceof Musica && temp.getProximo().getDado() instanceof Musica) {
                    Musica m1 = (Musica) temp.getDado();
                    Musica m2 = (Musica) temp.getProximo().getDado();

                    if (m1.getTitulo().compareToIgnoreCase(m2.getTitulo()) > 0) {
                        T aux = temp.getDado();
                        temp.setDado(temp.getProximo().getDado());
                        temp.getProximo().setDado(aux);
                        trocou = true;
                    }
                }
                temp = temp.getProximo();
            }
        } while (trocou);
    }


    public void ordenarPorArtista() {
        if (tamanho < 2) {
            return;
        }

        boolean trocou;
        do {
            trocou = false;
            NoDuplo<T> temp = inicio;

            while (temp.getProximo() != null) {
                if (temp.getDado() instanceof Musica && temp.getProximo().getDado() instanceof Musica) {
                    Musica m1 = (Musica) temp.getDado();
                    Musica m2 = (Musica) temp.getProximo().getDado();

                    if (m1.getArtista().compareToIgnoreCase(m2.getArtista()) > 0) {
                        T aux = temp.getDado();
                        temp.setDado(temp.getProximo().getDado());
                        temp.getProximo().setDado(aux);
                        trocou = true;
                    }
                }
                temp = temp.getProximo();
            }
        } while (trocou);
    }
}