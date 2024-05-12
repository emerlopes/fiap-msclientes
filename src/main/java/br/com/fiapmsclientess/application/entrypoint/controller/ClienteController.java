package br.com.fiapmsclientess.application.entrypoint.controller;

import br.com.fiapmsclientess.application.dto.ClienteRequestDTO;
import br.com.fiapmsclientess.application.dto.ClienteResponseDTO;
import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.usecase.CadastrarClienteUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final Logger logger;

    private final CadastrarClienteUseCase cadastrarClienteUseCase;

    public ClienteController(
            final Logger logger,
            final CadastrarClienteUseCase cadastrarClienteUseCase
    ) {
        this.logger = logger;
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(
            @RequestHeader("correlation-id") String correlationId,
            @RequestHeader("flow-id") String flowId,
            @RequestHeader("Content-Type") String contentType,
            @RequestBody @Valid ClienteRequestDTO clienteResquestDTO
    ) {

        logger.info("Chamando endpoint de cadastro de cliente: /clientes");

        final var entidadeDominio = ClienteDomainEntity.paraEntidadeDominio(clienteResquestDTO);

        final var cliente = cadastrarClienteUseCase.execute(entidadeDominio);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClienteResponseDTO.builder()
                        .idExterno(cliente.getIdExterno())
                        .nome(cliente.getNome())
                        .endereco(cliente.getEndereco())
                        .telefone(cliente.getTelefone())
                        .email(cliente.getEmail())
                        .dataCriacao(cliente.getDataCriacao())
                        .dataAtualizacao(cliente.getDataAtualizacao())
                        .build()
                );
    }
}
