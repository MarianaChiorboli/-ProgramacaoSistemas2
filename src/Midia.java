public abstract class Midia {
    private String titulo;
    
    public Midia(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String info() {
        return "Título: " + titulo;
    }

    public abstract long getDuracao();
}