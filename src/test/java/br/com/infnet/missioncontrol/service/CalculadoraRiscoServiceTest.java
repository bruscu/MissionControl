package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Missao;
import br.com.infnet.missioncontrol.model.NivelRisco;
import br.com.infnet.missioncontrol.model.StatusMissao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class CalculadoraRiscoServiceTest {
    @Autowired
    private CalculadoraRiscoService service;


    @Test
    @DisplayName("Deve Retornar risco baixo para uma missao tranquila")
    public void deveRetornarRiscoBaixo(){
        //Arrange
        Missao missao = new Missao(
                1L,
                "Exploradores da Lua",
                "Lua",
                Year.now().getValue() + 5,
                StatusMissao.PLANEJADA,
                new ArrayList<>(List.of("Ana")),
                "comando@missao.com"
        );
        //Execute
        NivelRisco resultado = service.calcularRisco(missao);
        //Verify
        assertThat(resultado, is(NivelRisco.BAIXO));

    }

}
