package ps2.titular_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class TitularDao {

    @Autowired
    private TitularRepo titularrepo;

    public void criar() {
        Titular t = new Titular();
        t.setNome(ES.input("Digite o nome do titular: "));
        t.setCpf(ES.input("Digite o CPF do titular: "));
        titularrepo.save(t);
        System.out.println("Titular criado com o ID " + t.getId());
    }

    public void lerTudo() {
        Iterable<Titular> titulares = titularrepo.findAll();
        for (Titular t : titulares) {
            System.out.println(t);
        }
    }

    public void buscarPorId() {
        try {
            Long id = Long.parseLong(ES.input("Digite o ID do titular a ser buscado: "));
            Optional<Titular> optional = titularrepo.findById(id);
            if (optional.isPresent()) {
                System.out.println(optional.get());
            } else {
                System.out.println("Titular não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    public void alterar() {
        try {
            Long id = Long.parseLong(ES.input("Digite o ID do titular a ser alterado: "));
            Optional<Titular> optional = titularrepo.findById(id);
            if (optional.isPresent()) {
                Titular t = optional.get();
                String novoNome = ES.input("Digite o novo nome (atual: " + t.getNome() + "): ");
                String novoCpf = ES.input("Digite o novo CPF (atual: " + t.getCpf() + "): ");
                if (!novoNome.isEmpty()) t.setNome(novoNome);
                if (!novoCpf.isEmpty()) t.setCpf(novoCpf);
                titularrepo.save(t);
                System.out.println("Titular atualizado.");
            } else {
                System.out.println("Titular não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    public void apagar() {
        try {
            Long id = Long.parseLong(ES.input("Digite o ID do titular a ser removido: "));
            Optional<Titular> optional = titularrepo.findById(id);
            if (optional.isPresent()) {
                titularrepo.deleteById(id);
                System.out.println("Titular removido.");
            } else {
                System.out.println("Titular não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }
}