public class Pilha {
    private Node topo;
    private int tamanho;
    private int capacidade;
    private String tipoOrdenacao;

    public Pilha() {
        topo = null;
        tamanho = 0;
    }

    public boolean cheia() {
        if (this.tamanho < capacidade) {
            return false;
        } else {
            return true;
        }
    }

    public boolean ordenada() {
        if (tipoOrdenacao == "crescente") {
            Node atual = topo;
            Node proximo = atual.getProximo();

            if (atual == null) {
                return true;
            }

            while (atual.getProximo() != null) {
                while (proximo.getProximo() != null) {
                    if (atual.getInformacao() < proximo.getInformacao()) {
                        return false;
                    }
                    proximo = proximo.getProximo();
                }
                atual = atual.getProximo();
                proximo = atual.getProximo();
            }

            return true;
        }
        if (tipoOrdenacao == "decrescente"){
            Node atual = topo;
            Node proximo = atual.getProximo();

            while (atual.getProximo() != null) {
                while (proximo.getProximo() != null) {
                    if (atual.getInformacao() > proximo.getInformacao()) {
                        return false;
                    }
                    proximo = proximo.getProximo();
                }
                atual = atual.getProximo();
                proximo = atual.getProximo();
            }

            return true;
        }
        return false;
    }

    public void inserir(int v) {
        Node no = new Node();
        no.setInformacao(v);
        if (this.cheia()) {
            System.out.println("Pilha cheia");
            return;
        }
        if (topo == null) {
            topo = no;
            tamanho++;
        } else {
            no.setProximo(topo);
            topo = no;
            tamanho++;
        }
    }

    public Integer remove() {
        if (topo == null) {
            System.out.println("Pilha vazia");
            return null;
        } else {
            if (topo.getProximo() == null) {
                int valor = topo.getInformacao();
                tamanho--;
                topo = null;
                return valor;
            } else {
                Node excluido = topo;
                topo = topo.getProximo();
                excluido.setProximo(null);
                tamanho--;
                return excluido.getInformacao();
            }
        }
    }

    public Integer getTopo() {
        if (topo != null) {
            return topo.getInformacao();
        }
        return null;
    }

    public void imprime() {
        Node atual = topo;
        if (atual == null) {
            for (int i = 0; i < capacidade; i++) {
                System.out.println("-");
            }
        } else {
            while (atual.getProximo() != null) {
                System.out.println(atual.getInformacao());
                atual = atual.getProximo();
            }
            System.out.println(atual.getInformacao());
        }
    }

    public String getTipoOrdenacao() {
        return tipoOrdenacao;
    }

    public void setTipoOrdenacao(String tipoOrdenacao) {
        this.tipoOrdenacao = tipoOrdenacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getTamanho() {
        return tamanho;
    }

}
