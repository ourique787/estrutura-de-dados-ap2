package Exercicio1;

import java.util.Scanner;

public class MainPedidos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaPedidosPendentes filaPedidos = new FilaPedidosPendentes();
        PilhaPedidosCancelados pilhaCancelados = new PilhaPedidosCancelados();

        int opcao;
        do {
            System.out.println("\n===== GESTÃO DE PEDIDOS =====");
            System.out.println("1. Adicionar novo pedido");
            System.out.println("2. Atender pedido");
            System.out.println("3. Cancelar pedido");
            System.out.println("4. Restaurar pedido");
            System.out.println("5. Imprimir pedidos pendentes");
            System.out.println("6. Imprimir pedidos cancelados");
            System.out.println("0. Sair");
            System.out.print("O que você deseja fazer? ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Descrição do pedido: ");
                    String descricao = scanner.nextLine();
                    Pedido novoPedido = new Pedido(descricao);
                    filaPedidos.enfileirar(novoPedido);
                    System.out.println("✓ Pedido adicionado: " + novoPedido);
                    break;

                case 2:
                    Pedido pedidoAtendido = filaPedidos.desenfileirar();
                    if (pedidoAtendido != null) {
                        System.out.println("✓ Pedido atendido: " + pedidoAtendido);
                    } else {
                        System.out.println("✗ Não há pedidos pendentes para atender.");
                    }
                    break;

                case 3:
                    Pedido pedidoCancelado = filaPedidos.desenfileirar();
                    if (pedidoCancelado != null) {
                        pilhaCancelados.empilhar(pedidoCancelado);
                        System.out.println("✓ Pedido cancelado: " + pedidoCancelado);
                    } else {
                        System.out.println("✗ Não há pedidos pendentes para cancelar.");
                    }
                    break;

                case 4:
                    Pedido pedidoRestaurado = pilhaCancelados.desempilhar();
                    if (pedidoRestaurado != null) {
                        filaPedidos.enfileirar(pedidoRestaurado);
                        System.out.println("✓ Pedido restaurado: " + pedidoRestaurado);
                    } else {
                        System.out.println("✗ Não há pedidos cancelados para restaurar.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- Pedidos Pendentes ---");
                    filaPedidos.imprimirFila();
                    break;

                case 6:
                    System.out.println("\n--- Pedidos Cancelados ---");
                    pilhaCancelados.imprimirPilha();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("✗ Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}