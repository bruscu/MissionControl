package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstronautaService {

    private final List<Astronauta> astronautas = new ArrayList<>();
    private Long proximoId = 4L;

    public AstronautaService() {
        astronautas.add(new Astronauta(1L, "Ana Silva", "Brasil", 34, 8,
                StatusAstronauta.DISPONIVEL));
        astronautas.add(new Astronauta(2L, "John Carter", "Estados Unidos", 41, 15,
                StatusAstronauta.EM_MISSAO));
        astronautas.add(new Astronauta(3L, "Mei Lin", "China", 29, 5,
                StatusAstronauta.AFASTADO));
    }

    public List<Astronauta> listarTodas() {
        return new ArrayList<>(astronautas);
    }

    public List<Astronauta> filtrarPorStatus(StatusAstronauta status) {
        List<Astronauta> resultado = new ArrayList<>();
        for (Astronauta astronauta : astronautas) {
            if (astronauta.getStatus() == status) {
                resultado.add(astronauta);
            }
        }
        return resultado;
    }

    public Astronauta buscarPeloId(Long id) {
        for (Astronauta astronauta : astronautas) {
            if (astronauta.getId().equals(id)) {
                return astronauta;
            }
        }
        return null;
    }

    public Astronauta adicionar(Astronauta astronauta) {
        validarDados(astronauta, null);
        // O ID informado no JSON é ignorado.
        astronauta.setId(proximoId++);
        astronauta.setNome(astronauta.getNome().trim());
        astronauta.setPais(astronauta.getPais().trim());
        astronautas.add(astronauta);
        return astronauta;
    }

    public Astronauta atualizar(Long id, Astronauta dadosAtualizados) {
        Astronauta existente = buscarPeloId(id);
        if (existente == null) {
            return null;
        }

        validarDados(dadosAtualizados, id);
        existente.setNome(dadosAtualizados.getNome().trim());
        existente.setPais(dadosAtualizados.getPais().trim());
        existente.setIdade(dadosAtualizados.getIdade());
        existente.setAnosExperiencia(dadosAtualizados.getAnosExperiencia());
        existente.setStatus(dadosAtualizados.getStatus());
        return existente;
    }

    public boolean remover(Long id) {
        return astronautas.removeIf(astronauta -> astronauta.getId().equals(id));
    }

    private void validarDados(Astronauta astronauta, Long idIgnorado) {
        if (astronauta == null) {
            throw new IllegalArgumentException("O corpo da requisição é obrigatório.");
        }
        if (astronauta.getNome() == null || astronauta.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (astronauta.getPais() == null || astronauta.getPais().trim().isEmpty()) {
            throw new IllegalArgumentException("O país não pode ser nulo ou vazio.");
        }
        if (astronauta.getIdade() == null || astronauta.getIdade() < 18) {
            throw new IllegalArgumentException("A idade deve ser informada e maior ou igual a 18 anos.");
        }
        if (astronauta.getAnosExperiencia() == null || astronauta.getAnosExperiencia() < 0) {
            throw new IllegalArgumentException("Os anos de experiência devem ser informados e não podem ser negativos.");
        }
        if (astronauta.getStatus() == null) {
            throw new IllegalArgumentException("O status do astronauta é obrigatório.");
        }

        String nomeInformado = astronauta.getNome().trim();
        for (Astronauta cadastrado : astronautas) {
            boolean mesmoNome = cadastrado.getNome().equalsIgnoreCase(nomeInformado);
            boolean mesmoRegistro = idIgnorado != null && cadastrado.getId().equals(idIgnorado);
            if (mesmoNome && !mesmoRegistro) {
                throw new IllegalArgumentException("Já existe um astronauta cadastrado com esse nome.");
            }
        }
    }
}
