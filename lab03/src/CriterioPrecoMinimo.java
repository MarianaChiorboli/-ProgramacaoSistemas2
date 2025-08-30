public class CriterioPrecoMinimo implements CriterioBusca {
    public boolean testar(Produto p, String valor) {

        try {
            double PrecoMinimo = Double.parseDouble(valor);
            return p.getPreco() <= PrecoMinimo;
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido para preço");
            return false;
        }
    }
}
