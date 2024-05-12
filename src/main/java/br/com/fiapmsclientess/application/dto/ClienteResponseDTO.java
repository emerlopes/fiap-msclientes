package br.com.fiapmsclientess.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ClienteResponseDTO {

    @JsonProperty("id_externo")
    private UUID idExterno;

    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    @JsonProperty("data_criacao")
    private LocalDateTime dataCriacao;

    @JsonProperty("data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
