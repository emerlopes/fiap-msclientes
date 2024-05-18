package br.com.fiapmsclientess.application.entrypoint.controller;

import br.com.fiapmsclientess.application.dto.ClienteRequestDTO;
import br.com.fiapmsclientess.application.dto.ClienteResponseDTO;
import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.usecase.BuscarClientePorIdUseCase;
import br.com.fiapmsclientess.domain.usecase.CadastrarClienteUseCase;
import jakarta.validation.Valid;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final Logger logger;

    private final CadastrarClienteUseCase cadastrarClienteUseCase;

    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;

    public ClienteController(
            final Logger logger,
            final CadastrarClienteUseCase cadastrarClienteUseCase,
            final BuscarClientePorIdUseCase buscarClientePorIdUseCase
    ) {
        this.logger = logger;
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.buscarClientePorIdUseCase = buscarClientePorIdUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(
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

    @GetMapping({"/{idExterno}"})
    public ResponseEntity<ClienteResponseDTO> buscarClientePorIdExterno(
            @RequestHeader("correlation-id") String correlationId,
            @RequestHeader("flow-id") String flowId,
            @RequestHeader("Content-Type") String contentType,
            @PathVariable("idExterno") String idExterno
    ) {
        logger.info("Chamando endpoint de busca de cliente por idExterno: /clientes/{idExterno}");

        final var entidadeDominio = ClienteDomainEntity.paraEntidadeDominio(idExterno);

        final var cliente = buscarClientePorIdUseCase.execute(entidadeDominio);

        return ResponseEntity.status(HttpStatus.OK)
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
