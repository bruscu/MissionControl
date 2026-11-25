package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Instrumento;
import br.com.infnet.missioncontrol.model.Sonda;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SondaService {
    private final List<Sonda> sondas = new ArrayList<>();

    public SondaService() {
        sondas.add(new Sonda(
                1L,
                "Aurora-1",
                "Marte",
                2028,
                "PLANEJADA",
                "Agência Orbital",
                List.of(new Instrumento("Câmera",2L), new Instrumento("Espectometro", 5L))
        ));
    }

    public List<Sonda> listarTodos() {
        return sondas;

    }

    public Sonda getById(Long id) {
        for (Sonda s: sondas) {
            if(s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}
