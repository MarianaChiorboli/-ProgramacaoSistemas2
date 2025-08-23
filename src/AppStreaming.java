import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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

            opcao = entrada.nextInt();
            entrada.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do filme: ");
                    String tituloFilme = entrada.nextLine();
                    System.out.print("Digite a duração do filme (em minutos): ");
                    long duracaoFilme = entrada.nextLong();
                    entrada.nextLine(); 
                    
                    Filme novoFilme = new Filme(tituloFilme, duracaoFilme);
                    midias.add(novoFilme);
                    System.out.println("Filme adicionado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o título da série: ");
                    String tituloSerie = entrada.nextLine();
                    Serie novaSerie = new Serie(tituloSerie);

                    for (int i = 1; i <= 2; i++) {
                        Temporada novaTemporada = new Temporada(i);
                        System.out.println("--- Adicionando episódios para a temporada " + i + " ---");
                        
                        for (int j = 1; j <= 2; j++) {
                            System.out.print("Digite o título do " + j + "º episódio: ");
                            String tituloEp = entrada.nextLine();
                            System.out.print("Digite a duração do " + j + "º episódio (em minutos): ");
                            long duracaoEp = entrada.nextLong();
                            entrada.nextLine();
                            novaTemporada.adicionar(new Episodio(tituloEp, duracaoEp));
                        }
                        novaSerie.adicionar(novaTemporada);
                    }
                    midias.add(novaSerie);
                    System.out.println("Série adicionada com sucesso!");
                    break;
                case 3:
                    if (midias.isEmpty()) {
                        System.out.println("Nenhuma mídia cadastrada.");
                    } else {
                        System.out.println("--- LISTA DE MÍDIAS ---");
                        for (Midia m : midias) {
                            System.out.println(m.info());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

            System.out.println(); 

        } while (opcao != 4);

        entrada.close();
    }
}
