package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.ErrorResponse;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import br.com.infnet.missioncontrol.service.AstronautaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Locale;

@RestController
@RequestMapping("/astronautas")
public class AstronautaController {

    private final AstronautaService astronautaService;

    public AstronautaController(AstronautaService astronautaService) {
        this.astronautaService = astronautaService;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> tratarJsonInvalido(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse("JSON inválido. Verifique os tipos dos campos e o status informado."));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> tratarParametroInvalido(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse("O ID informado deve ser numérico."));
    }

    @GetMapping
    public ResponseEntity<?> listar(@RequestParam(required = false) String status) {
        if (status == null) {
            return ResponseEntity.ok(astronautaService.listarTodas());
        }
        try {
            StatusAstronauta statusAstronauta =
                    StatusAstronauta.valueOf(status.trim().toUpperCase(Locale.ROOT));
            return ResponseEntity.ok(astronautaService.filtrarPorStatus(statusAstronauta));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse("Status inválido. Utilize DISPONIVEL, EM_MISSAO ou AFASTADO."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("O ID deve ser maior que zero."));
        }

        Astronauta astronauta = astronautaService.buscarPeloId(id);
        if (astronauta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Astronauta não encontrado."));
        }
        return ResponseEntity.ok(astronauta);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody(required = false) Astronauta astronauta) {
        try {
            Astronauta cadastrado = astronautaService.adicionar(astronauta);
            return ResponseEntity.status(HttpStatus.CREATED).body(cadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                        @RequestBody(required = false) Astronauta dadosAtualizados) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("O ID deve ser maior que zero."));
        }

        try {
            Astronauta atualizado = astronautaService.atualizar(id, dadosAtualizados);
            if (atualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Astronauta não encontrado."));
            }
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("O ID deve ser maior que zero."));
        }

        boolean removeu = astronautaService.remover(id);
        if (!removeu) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Astronauta não encontrado."));
        }
        return ResponseEntity.noContent().build();
    }
}
