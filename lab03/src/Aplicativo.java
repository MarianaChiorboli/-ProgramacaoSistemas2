import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicativo {
    static Scanner sc = new Scanner(System.in);
    
    static List<Produto> pesquisar(List<Produto> produtos, String valor, CriterioBusca criterio) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p: produtos) {
            if (criterio.testar(p, valor)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    static void menuPrincipal(List<Produto> produtos) {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n(1) - Listar produtos");
            System.out.println("(2) - Pesquisar descrição");
            System.out.println("(3) - Pesquisar marca");
            System.out.println("(4) Pesquisar pelo preço máximo");
            System.out.println("(5) Pesquisar pelo preço mínimo");
            System.out.println("(0) - Sair");
        
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "0":
                    sair = true;
                    break;
                case "1":
                    System.out.println("\n\nLista de produtos: ");
                    for (Produto p: produtos) {
                        System.out.println(p);
                    }
                    break;
                case "2":
                    System.out.print("Termo a pesquisar: ");
                    String termo = sc.nextLine();
                    System.out.println("\nResultado da pesquisa: ");
                    List<Produto> resultado1 = pesquisar(produtos, termo, new CriterioDescricao());
                    for (Produto p : resultado1){
                        System.out.println(p);
                    } 
                    break;

                case "3":
                    System.out.print("Marca a pesquisar: ");
                    String marca = sc.nextLine();
                    System.out.println("\nResultado da pesquisa: ");
                    List<Produto> resultado2 = pesquisar(produtos, marca, new CriterioMarca());
                    for (Produto p : resultado2){
                        System.out.println(p);
                    } 
                    break;

                case "4":
                    System.out.print("Qual o valor máximo em R$: ");
                    String precoMaximo = sc.nextLine();  
                    System.out.println("\nResultado da pesquisa (preço máximo): ");
                    List<Produto> resultado4 = pesquisar(produtos, precoMaximo, new CriterioPrecoMaximo());
                    for (Produto p : resultado4) {
                        System.out.println(p);
                    }
                    break;

                case "5":
                    System.out.print("Qual o valor mínimo em R$: ");
                    String precoMinimo = sc.nextLine();  
                    System.out.println("\nResultado da pesquisa (preço mínimo): ");
                    List<Produto> resultado5 = pesquisar(produtos, precoMinimo, new CriterioPrecoMinimo());
                    for (Produto p : resultado5) {
                        System.out.println(p);
                    }
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        List<String> linhas;
        Path arquivo = Paths.get("produtos.txt");
        List<Produto> produtos = new ArrayList<>();


        try {
            linhas = Files.readAllLines(arquivo);
            for (String linha : linhas) {
                String[] c = linha.split(";");
                Produto p = new Produto(c[0], Double.parseDouble(c[1]), c[2]);
                produtos.add(p);
                
            }
            menuPrincipal(produtos);
        } catch (IOException e) {
            System.out.println("Erro ao carregar os produtos");
        }

    }
}