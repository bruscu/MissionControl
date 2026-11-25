package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.Sonda;
import br.com.infnet.missioncontrol.service.SondaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sondas")
@RequiredArgsConstructor // -> Gera o construtor com os parametros obrigatorios

public class SondaController {
    private final SondaService service;

    @GetMapping
    public ResponseEntity listarTodas(){
        List<Sonda> sondas =
                service.listarTodos();
        return ResponseEntity.ok(sondas);

    }
    @GetMapping("/{id}")
    public ResponseEntity geById(@PathVariable Long id){
        Sonda byId = service.getById(id);
        if(byId != null){
            return ResponseEntity.ok(byId);
        }
        return ResponseEntity.notFound().build();
    }

}
