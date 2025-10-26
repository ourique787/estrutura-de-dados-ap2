package Exercicio2;

import java.util.Scanner;

public class MainMusicas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDuplamenteLigada<Musica> playlist = new ListaDuplamenteLigada<>();

        //Adicionar algumas musicas pra testar rapidamente
        playlist.adicionarFim(new Musica("Tempo Perdido", "Legião Urbana", "Dois", 354));
        playlist.adicionarInicio(new Musica("Eduardo e Monica", "Legião Urbana", "Tres", 183));
        playlist.adicionarFim(new Musica("Chão de Giz", "Zé Ramalho", "Quatro", 391));

        int opcao;
        do {
            System.out.println("\n===== GERENCIADOR DE MÚSICAS =====");
            System.out.println("1. Próxima música");
            System.out.println("2. Música anterior");
            System.out.println("3. Ordenar playlist");
            System.out.println("4. Tocar música");
            System.out.println("5. Adicionar música");
            System.out.println("6. Remover música");
            System.out.println("7. Listar músicas");
            System.out.println("0. Sair");
            System.out.print("Digite a opção desejada: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Musica proxima = playlist.proximo();
                    if (proxima != null) {
                        System.out.println("► Avançou para: " + proxima);
                    } else {
                        System.out.println("✗ Você já está na última música.");
                    }
                    break;

                case 2:
                    Musica anterior = playlist.anterior();
                    if (anterior != null) {
                        System.out.println("◄ Voltou para: " + anterior);
                    } else {
                        System.out.println("✗ Você já está na primeira música.");
                    }
                    break;

                case 3:
                    if (playlist.estaVazia()) {
                        System.out.println("✗ Playlist vazia.");
                        break;
                    }

                    System.out.println("\nOrdenar por:");
                    System.out.println("1. Título");
                    System.out.println("2. Artista");
                    System.out.print("Escolha: ");
                    int criterio = scanner.nextInt();
                    scanner.nextLine();

                    if (criterio == 1) {
                        playlist.ordenarPorTitulo();
                        System.out.println("✓ Playlist ordenada por título.");
                    } else if (criterio == 2) {
                        playlist.ordenarPorArtista();
                        System.out.println("✓ Playlist ordenada por artista.");
                    } else {
                        System.out.println("✗ Opção inválida.");
                    }
                    break;

                case 4:
                    Musica atual = playlist.getAtual();
                    if (atual != null) {
                        System.out.println("\n♪ Tocando agora:");
                        System.out.println("  Título: " + atual.getTitulo());
                        System.out.println("  Artista: " + atual.getArtista());
                        System.out.println("  Álbum: " + atual.getAlbum());
                        System.out.println("  Duração: " + atual.getDuracaoFormatada());
                    } else {
                        System.out.println("✗ Nenhuma música selecionada.");
                    }
                    break;

                case 5:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Artista: ");
                    String artista = scanner.nextLine();
                    System.out.print("Álbum: ");
                    String album = scanner.nextLine();
                    System.out.print("Duração (segundos): ");
                    int duracao = scanner.nextInt();
                    scanner.nextLine();

                    Musica novaMusica = new Musica(titulo, artista, album, duracao);

                    System.out.println("\nAdicionar:");
                    System.out.println("1. No início");
                    System.out.println("2. No fim");
                    System.out.println("3. Em posição específica");
                    System.out.print("Escolha: ");
                    int opcaoAdicionar = scanner.nextInt();
                    scanner.nextLine();

                    if (opcaoAdicionar == 1) {
                        playlist.adicionarInicio(novaMusica);
                        System.out.println("✓ Música adicionada no início.");
                    } else if (opcaoAdicionar == 2) {
                        playlist.adicionarFim(novaMusica);
                        System.out.println("✓ Música adicionada no fim.");
                    } else if (opcaoAdicionar == 3) {
                        System.out.print("Posição (1 a " + (playlist.getTamanho() + 1) + "): ");
                        int posicao = scanner.nextInt();
                        scanner.nextLine();

                        if (playlist.adicionarPosicao(novaMusica, posicao)) {
                            System.out.println("✓ Música adicionada na posição " + posicao + ".");
                        } else {
                            System.out.println("✗ Posição inválida.");
                        }
                    }
                    break;

                case 6:
                    if (playlist.estaVazia()) {
                        System.out.println("✗ Playlist vazia.");
                        break;
                    }

                    System.out.println("\nRemover por:");
                    System.out.println("1. Título");
                    System.out.println("2. Posição");
                    System.out.print("Escolha: ");
                    int opcaoRemover = scanner.nextInt();
                    scanner.nextLine();

                    if (opcaoRemover == 1) {
                        System.out.print("Digite o título da música a remover: ");
                        String tituloRemover = scanner.nextLine();

                        if (playlist.removerPorTitulo(tituloRemover)) {
                            System.out.println("✓ Música removida com sucesso.");
                        } else {
                            System.out.println("✗ Música não encontrada.");
                        }
                    } else if (opcaoRemover == 2) {
                        System.out.print("Digite a posição (1 a " + playlist.getTamanho() + "): ");
                        int posicaoRemover = scanner.nextInt();
                        scanner.nextLine();

                        Musica musicaRemovida = playlist.removerPorPosicao(posicaoRemover);
                        if (musicaRemovida != null) {
                            System.out.println("✓ Música removida: " + musicaRemovida);
                        } else {
                            System.out.println("✗ Posição inválida.");
                        }
                    } else {
                        System.out.println("✗ Opção inválida.");
                    }
                    break;

                case 7:
                    System.out.println("\n--- PLAYLIST ---");
                    playlist.listar();
                    System.out.println("\nTotal: " + playlist.getTamanho() + " música(s)");
                    break;

                case 0:
                    System.out.println("Encerrando player...");
                    break;

                default:
                    System.out.println("✗ Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}