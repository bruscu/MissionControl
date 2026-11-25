package br.com.infnet.missioncontrol.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor@ToString
public class Sonda {
    private Long id;
    private String nome;
    private String destino;
    private Integer anoLancamento;
    private String status;
    private String agencia;
    private List<Instrumento> instrumentos;
}
