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
    public String info() {
        return "Série: " + super.info() + "; Duração total: " + getDuracao() + " minutos";
    }

    @Override
    public long getDuracao() {
        long duracaoTotal = 0;
        for (Temporada temporada : temporadas) {
            duracaoTotal += temporada.getDuracao();
        }
        return duracaoTotal;
    }
}