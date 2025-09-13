import java.util.Scanner;
import java.util.List;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            ContaDao dao;
            String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.hscwujcsucpepnhczchw&password=PasswordLab04@";
            dao = new ContaDao(ConnectionFactory.getConnection(url));

            int opcao;

            do {
                System.out.println("1 - Criar conta");
                System.out.println("2 - Listar todas as contas");
                System.out.println("3 - Buscar conta pelo número");
                System.out.println("4 - Alterar o saldo de uma conta");
                System.out.println("5 - Apagar uma conta");
                System.out.println("6 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1: {
                        System.out.print("Número da conta: ");
                        long numero = scanner.nextLong();

                        System.out.print("Saldo inicial (use ponto como separador decimal): ");
                        BigDecimal saldo = scanner.nextBigDecimal();

                        Conta c = new Conta(numero, saldo);
                        if (dao.criar(c)) {
                            System.out.println("Conta criada com sucesso! \n" );
                        } else {
                            System.out.println("Erro ao criar conta! \n");
                        }
                        break;
                    }

                    case 2: {
                        List<Conta> contas = dao.lerTodas();
                        if (contas == null || contas.isEmpty()) {
                            System.out.println("Nenhuma conta encontrada.\n");
                        } else {
                            contas.forEach(System.out::println);
                        }
                        break;
                    }

                    case 3: {
                        System.out.print("Número da conta: ");
                        long num = scanner.nextLong();

                        Conta conta = dao.buscarPeloNumero(num);
                        if (conta != null) {
                            System.out.println(conta);
                        } else {
                            System.out.println("Conta não encontrada!\n");
                        }
                        break;
                    }

                    case 4: {
                        System.out.print("Número da conta: ");
                        long n = scanner.nextLong();

                        Conta contaAtualizar = dao.buscarPeloNumero(n);
                        if (contaAtualizar != null) {
                            System.out.print("Novo saldo (use ponto como separador decimal):  \n");
                            BigDecimal novoSaldo = scanner.nextBigDecimal();
                            contaAtualizar.setSaldo(novoSaldo);

                            if (dao.atualizar(contaAtualizar)) {
                                System.out.println("Conta atualizada com sucesso! \n");
                            } else {
                                System.out.println("Erro ao atualizar conta! \n");
                            }
                        } else {
                            System.out.println("Conta não encontrada! \n");
                        }
                        break;
                    }

                    case 5: {
                        System.out.print("Número da conta: ");
                        long numeroApagar = scanner.nextLong();

                        Conta contaApagar = dao.buscarPeloNumero(numeroApagar);
                        if (contaApagar != null) {
                            if (dao.apagar(contaApagar)) {
                                System.out.println("Conta apagada com sucesso! \n");
                            } else {
                                System.out.println("Erro ao apagar conta! \n");
                            }
                        } else {
                            System.out.println("Conta não encontrada! \n");
                        }
                        break;
                    }

                    case 6:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida! \n");
                        break;
                }
            } while (opcao != 6);
        }
    }
}
