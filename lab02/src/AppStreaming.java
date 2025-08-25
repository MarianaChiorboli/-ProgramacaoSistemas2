import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AppStreaming {

    public static void main(String[] args) {
        List<Midia> midias = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("(1) Adicionar novo Filme.");
            System.out.println("(2) Adicionar nova Série.");
            System.out.println("(3) Listar todas as mídias.");
            System.out.println("(4) Sair"); 
            System.out.print("Escolha uma opção: ");

            try {
                opcao = entrada.nextInt();
                entrada.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida!Opções apenas números de 1 a 4");
                entrada.nextLine(); 
                opcao = 0; 
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do filme: ");
                    String tituloFilme = entrada.nextLine();

                    try {
                        System.out.print("Digite a duração do filme (em minutos): ");
                        long duracaoFilme = entrada.nextLong();
                        entrada.nextLine();

                        if (duracaoFilme <= 0) {
                            System.out.println("A duração deve ser maior que 0.");
                        } else {
                            Filme novoFilme = new Filme(tituloFilme, duracaoFilme);
                            midias.add(novoFilme);
                            System.out.println("Filme adicionado!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida para duração. Digite apenas números.");
                        entrada.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Digite o título da série: "); 
                    String tituloSerie = entrada.nextLine();
                    Serie serie = new Serie(tituloSerie);

                    for (int t = 1; t <= 2; t++) {
                        Temporada temporada = new Temporada(t);

                        for (int e = 1; e <= 2; e++) {
                            System.out.print("Nome do episódio " + e + " da temporada " + t + ": ");
                            String tituloEp = entrada.nextLine();

                            long duracaoEp = 0;
                            boolean valido = false;

                            while (!valido) {
                                try {
                                    System.out.print("Duração do episódio (em minutos): ");
                                    duracaoEp = entrada.nextLong();
                                    entrada.nextLine();

                                    if (duracaoEp <= 0) {
                                        System.out.println(" A duração deve ser maior que 0.");
                                    } else {
                                        valido = true;
                                    }
                                } catch (InputMismatchException ex) {
                                    System.out.println(" Entrada inválida. Digite apenas números.");
                                    entrada.nextLine();
                                }
                            }

                            temporada.adicionar(new Episodio(tituloEp, duracaoEp));
                        }
                        serie.adicionar(temporada);
                    }

                    midias.add(serie);
                    System.out.println("Série adicionada!");
                    break;

                case 3:
                    if (midias.isEmpty()) {
                        System.out.println("Nenhuma mídia cadastrada.");
                    } else {
                        System.out.println("LISTA DE MÍDIAS ");
                        for (Midia m : midias) {
                            System.out.println(m.info());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Encerrando");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }


        } while (opcao != 4);

        entrada.close();
    }
}