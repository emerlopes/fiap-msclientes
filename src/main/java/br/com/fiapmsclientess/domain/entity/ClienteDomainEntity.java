package br.com.fiapmsclientess.domain.entity;

import br.com.fiapmsclientess.application.dto.ClienteRequestDTO;
import br.com.fiapmsclientess.infrastructure.database.entity.ClienteEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ClienteDomainEntity {

    @NotBlank(message = "O id externo do cliente e obrigatorio")
    @Setter
    private UUID idExterno;

    @NotBlank(message = "O nome do cliente e obrigatorio")
    private String nome;

    @NotBlank(message = "O endereco do cliente e obrigatorio")
    private String endereco;

    @NotBlank(message = "O telefone do cliente e obrigatorio")
    @Pattern(regexp = "^\\(?(\\d{2})\\)?[ .-]?(\\d{4,5})[ .-]?(\\d{4})$", message = "Formato de telefone invalido")
    private String telefone;

    @NotBlank(message = "O email do cliente e obrigatorio")
    @Email(message = "Formato de email invalido")
    private String email;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static ClienteDomainEntity paraEntidadeDominio(
            final ClienteRequestDTO clienteRequestDTO
    ) {
        return ClienteDomainEntity.builder()
                .nome(clienteRequestDTO.getNome())
                .endereco(clienteRequestDTO.getEndereco())
                .telefone(clienteRequestDTO.getTelefone())
                .email(clienteRequestDTO.getEmail())
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static ClienteDomainEntity paraEntidadeDominio(
            final String idExterno
    ) {
        return ClienteDomainEntity.builder()
                .idExterno(UUID.fromString(idExterno))
                .build();
    }

    public static ClienteDomainEntity paraEntidadeDominio(
            final ClienteEntity clienteEntity
    ) {
        return ClienteDomainEntity.builder()
                .idExterno(clienteEntity.getIdExterno())
                .nome(clienteEntity.getNome())
                .endereco(clienteEntity.getEndereco())
                .telefone(clienteEntity.getTelefone())
                .email(clienteEntity.getEmail())
                .dataCriacao(clienteEntity.getDataCriacao())
                .dataAtualizacao(clienteEntity.getDataAtualizacao())
                .build();
    }

}
