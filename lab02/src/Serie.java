import java.util.List;
import java.util.ArrayList;

public class Serie extends Midia {
    private List<Temporada> temporadas;

    public Serie(String titulo) {
        super(titulo);
        this.temporadas = new ArrayList<>();
    }
    
    public void adicionar(Temporada temporada) {
        this.temporadas.add(temporada);
    }

    @Override
    public long getDuracao() {
        long total = 0;
        for (Temporada t : temporadas) {
            total += t.getDuracao();
        }
        return total;
    }

    @Override
    public String info() {
        return "Série: " + getTitulo() + "; Duração total: " + getDuracao() + " min";
    }
}