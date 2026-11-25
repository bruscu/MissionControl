package br.com.infnet.missioncontrol.test;

import br.com.infnet.missioncontrol.model.Instrumento;
import br.com.infnet.missioncontrol.model.Sonda;

import java.util.List;

public class Main {
    static void main() {
        Sonda sonda1 = new Sonda(1L, "Aurora-1", "Marte", 2028,
                "PLANEJADA", "Agencia Europeia",
                List.of(new Instrumento("Radar infravermelho", 3L)));
        System.out.println(sonda1);
    }
}
