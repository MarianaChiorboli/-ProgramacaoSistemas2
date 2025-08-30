public class CriterioPrecoMaximo implements CriterioBusca {
    public boolean testar(Produto p, String valor) {

        try {
            double precoMaximo = Double.parseDouble(valor);
            return p.getPreco() <= precoMaximo;
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido para preço");
            return false;
        }
        
    }
}
