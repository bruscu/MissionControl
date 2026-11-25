package br.com.infnet.missioncontrol.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Missao {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Destino é obrigatório")
    private String destino;
    @NotNull(message = "O Ano é obrigatório")
    @Min(value = 1957, message = "O ano mínimo é 1957 ")
    @Max(value = 2100, message = "O ano máximo é 2100 ")
    private Integer anoLancamento;
    private StatusMissao status;
    @NotNull(message = "Tripulantes são obrigatórios")
    private List<String> tripulantes;

    @NotBlank(message="Email é obrigatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Deve ser um email valido!")
    private String email;


}
/*
Not Null
Not Empty
NOt Blank -> Texto nao pode ser nulo, vazio, ou so espacos
Min(3)
MAx
Size
Pattern @ .com

String nome = null; null
String nome = "" vazio
String nome = "                 " espacos

Integer s = null;

 */