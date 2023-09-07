import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Pilha p1 = new Pilha();
        Pilha p2 = new Pilha();
        Pilha p3 = new Pilha();


        System.out.println("Escolha o tipo de ordenação (sentido base -> topo): \n1- Decrescente \n2- Crescente");
        int tipoOrd = sc.nextInt();

        if (tipoOrd == 1) {
            p1.setTipoOrdenacao("decrescente");
            p2.setTipoOrdenacao("decrescente");
            p3.setTipoOrdenacao("decrescente");
        } else {
            p1.setTipoOrdenacao("crescente");
            p2.setTipoOrdenacao("crescente");
            p3.setTipoOrdenacao("crescente");
        }

        System.out.print("Insira o tamanho das pilhas: ");
        int tamanho = sc.nextInt();

        p1.setCapacidade(tamanho);
        p2.setCapacidade(tamanho);
        p3.setCapacidade(tamanho);

        Random random = new Random();
        int num;

        for (int i = 0; i < tamanho; i++) {
            num = random.nextInt(99) + 1;
            p1.inserir(num);
        }

        System.out.println("Pilha 1: ");
        p1.imprime();

        System.out.println("Pilha 2: ");
        p2.imprime();

        System.out.println("Pilha 3: ");
        p3.imprime();

        boolean ganhou = false;
        int jogadas = 0;
        int opt;
        while (!ganhou) {
            System.out.println("Selecione uma opção: \n1 - Sair \n2 - Movimentar  \n3 - Resolver");
            opt = sc.nextInt();
            if (opt == 1) {
                ganhou = true;
            }
            if (opt == 2) {
                jogadas++;
                System.out.println("Selecione a pilha origem, que vai ser desempilhada (1, 2, 3): ");
                int origem = sc.nextInt();
                Integer valorOrigem;
                if (origem == 1) {
                    valorOrigem = p1.remove();
                } else if (origem == 2) {
                    valorOrigem = p2.remove();
                } else {
                    valorOrigem = p3.remove();
                }
                System.out.println("Selecione a pilha destino, que vai ser empilhada (1, 2, 3): ");
                int destino = sc.nextInt();
                if (destino == 1) {
                    p1.inserir(valorOrigem);
                } else if (destino == 2) {
                    p2.inserir(valorOrigem);
                } else {
                    p3.inserir(valorOrigem);
                }

                System.out.println("Pilha 1");
                p1.imprime();
                System.out.println("Pilha 2");
                p2.imprime();
                System.out.println("Pilha 3");
                p3.imprime();

                if((p1.cheia() && p1.ordenada()) || (p2.cheia() && p2.ordenada()) || (p3.cheia() && p3.ordenada())) {
                    System.out.println("Ordenação concluída em " + jogadas + " jogadas");
                    ganhou = true;
                }

            }
            if (opt == 3) {
                resolver(p1, p2 ,p3);
                System.out.println("Pilha 1");
                p1.imprime();
                System.out.println("Pilha 2");
                p2.imprime();
                System.out.println("Pilha 3");
                p3.imprime();
                System.out.println("Ordenação concluída em " + passos + " jogadas");
                ganhou = true;
            }
        }

        sc.close();
    }

    public static int passos = 0;

    public static void resolver(Pilha p1, Pilha p2, Pilha p3) {
        // Passa o primeiro para p2
        p2.inserir(p1.remove());
        passos++;
        // Compara o topo de p1 com p2
        while (p1.getTopo() != null) {
            // Se for maior inserimos na pilha 2
            if (p1.getTopo() >= p2.getTopo()) {
                p2.inserir(p1.remove());
            } else {
                // Senão vai para p3
                p3.inserir(p1.remove());
            }
            passos++;
        }

        // Passa todos de p2 para p1 (maiores)
        while (p2.getTopo() != null) {
            p1.inserir(p2.remove());
            passos++;
        }

        // Pega os topos de p3
        while (p3.getTopo() != null) {
            // Se ele for menor do que topo de p1, pode empilhar
            if (p3.getTopo() <= p1.getTopo()) {
                p1.inserir(p3.remove());
                passos++;
            } else {
                // topo p3 é maior que topo p1
                // Enquanto o topo de p1 for maior que o topo de p3
                // Vamos passar o topo de p1 para p2
                // Até achar a posição para o topo p3
                while (p1.getTopo() < p3.getTopo()) {
                    p2.inserir(p1.remove());
                    passos++;
                }
                // Finalmente, inserimos o p3
                p1.inserir(p3.remove());
                passos++;
                // Pegamos os que passamos para p2 e voltamos para p1
                while (p2.getTopo() != null) {
                    p1.inserir(p2.remove());
                    passos++;
                }
            }
        }

        // Agora esta ordenado decrescente (base -> topo)
        // Se a ordenacao for crescente, basta passar todos de p1 para p2
        if (p1.getTipoOrdenacao() == "crescente") {
            while (p1.getTopo() != null) {
                p2.inserir(p1.remove());
                passos++;
            }
        }
    }
}