package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AstronautaServiceTest {

    @Test
    void deveInicializarComTresAstronautas() {
        AstronautaService service = new AstronautaService();
        assertEquals(3, service.listarTodas().size());
    }

    @Test
    void deveCadastrarAstronautaComIdAutomatico() {
        AstronautaService service = new AstronautaService();
        Astronauta novo = new Astronauta(999L, "Carlos Mendes", "Brasil", 32, 6,
                StatusAstronauta.DISPONIVEL);
        Astronauta cadastrado = service.adicionar(novo);
        assertEquals(4L, cadastrado.getId());
        assertEquals(4, service.listarTodas().size());
    }

    @Test
    void deveFiltrarAstronautasPorStatus() {
        AstronautaService service = new AstronautaService();
        assertEquals(1, service.filtrarPorStatus(StatusAstronauta.DISPONIVEL).size());
        assertEquals("Mei Lin", service.filtrarPorStatus(StatusAstronauta.AFASTADO).get(0).getNome());
    }

    @Test
    void naoDeveCadastrarNomeDuplicado() {
        AstronautaService service = new AstronautaService();
        Astronauta duplicado = new Astronauta(null, "ana silva", "Brasil", 30, 4,
                StatusAstronauta.DISPONIVEL);
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(duplicado));
    }

    @Test
    void deveAtualizarAstronautaSemAlterarSeuId() {
        AstronautaService service = new AstronautaService();
        Astronauta dados = new Astronauta(500L, "Ana Silva", "Brasil", 35, 9,
                StatusAstronauta.EM_MISSAO);
        Astronauta atualizado = service.atualizar(1L, dados);
        assertNotNull(atualizado);
        assertEquals(1L, atualizado.getId());
        assertEquals(35, atualizado.getIdade());
        assertEquals(StatusAstronauta.EM_MISSAO, atualizado.getStatus());
    }

    @Test
    void deveRemoverAstronauta() {
        AstronautaService service = new AstronautaService();
        assertTrue(service.remover(1L));
        assertNull(service.buscarPeloId(1L));
        assertFalse(service.remover(1L));
    }

    @Test
    void naoDeveAceitarIdadeMenorQueDezoito() {
        AstronautaService service = new AstronautaService();
        Astronauta invalido = new Astronauta(null, "Pedro Costa", "Brasil", 17, 0,
                StatusAstronauta.DISPONIVEL);
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(invalido));
    }
}
