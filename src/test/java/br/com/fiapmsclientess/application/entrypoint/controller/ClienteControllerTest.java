package br.com.fiapmsclientess.application.entrypoint.controller;

import br.com.fiapmsclientess.application.dto.ClienteRequestDTO;
import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.usecase.AtualizarClienteUseCase;
import br.com.fiapmsclientess.domain.usecase.BuscarClientePorIdUseCase;
import br.com.fiapmsclientess.domain.usecase.CadastrarClienteUseCase;
import br.com.fiapmsclientess.domain.usecase.DeletarClientePorIdUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    Logger logger;

    @Mock
    CadastrarClienteUseCase cadastrarClienteUseCase;

    @Mock
    BuscarClientePorIdUseCase buscarClientePorIdUseCase;

    @Mock
    AtualizarClienteUseCase atualizarClienteUseCase;

    @Mock
    DeletarClientePorIdUseCase deletarClientePorIdUseCase;

    @Test
    void deveCadastrarClienteComSucesso() {

        // ARRANGE
        final var clienteRequestDTO = criarClienteRequestDTO();
        final var clienteDomainEntity = criarClienteDomainEntity();
        Mockito.when(cadastrarClienteUseCase.execute(Mockito.any(ClienteDomainEntity.class))).thenReturn(clienteDomainEntity);

        // ACT
        final var clienteResponse = clienteController.cadastrarCliente(
                "correlation-id",
                "flow-id",
                "Content-Type",
                clienteRequestDTO
        );

        // ASSERT

        final var clienteResponseDTO = clienteResponse.getBody();

        Mockito.verify(cadastrarClienteUseCase, Mockito.times(1)).execute(Mockito.any(ClienteDomainEntity.class));

        Assertions.assertThat(clienteResponseDTO).isNotNull().describedAs("ClienteResponseDTO não pode ser nulo");
        Assertions.assertThat(clienteResponseDTO.getDataCriacao()).isNotNull().describedAs("Data de criação não pode ser nula");
        Assertions.assertThat(clienteResponseDTO.getDataAtualizacao()).describedAs("Data de atualização deve ser nula").isNull();
        Assertions.assertThat(clienteResponseDTO.getIdExterno()).isNotNull().describedAs("Id externo não pode ser nulo");
        Assertions.assertThat(clienteResponseDTO.getNome()).isEqualTo(clienteRequestDTO.getNome()).describedAs("Nome deve ser igual ao nome do request");
        Assertions.assertThat(clienteResponseDTO.getEndereco()).isEqualTo(clienteRequestDTO.getEndereco()).describedAs("Endereço deve ser igual ao endereço do request");
        Assertions.assertThat(clienteResponseDTO.getTelefone()).isEqualTo(clienteRequestDTO.getTelefone()).describedAs("Telefone deve ser igual ao telefone do request");
        Assertions.assertThat(clienteResponseDTO.getEmail()).isEqualTo(clienteRequestDTO.getEmail()).describedAs("Email deve ser igual ao email do request");
    }

    @Test
    void deveBuscarClientePorIdComSucesso() {
        // ARRANGE
        final var clienteDomainEntity = criarClienteDomainEntity();
        Mockito.when(buscarClientePorIdUseCase.execute(Mockito.any(ClienteDomainEntity.class))).thenReturn(clienteDomainEntity);

        // ACT
        final var clienteResponse = clienteController.buscarClientePorIdExterno(
                "correlation-id",
                "flow-id",
                "Content-Type",
                clienteDomainEntity.getIdExterno().toString()
        );

        // ASSERT

        final var clienteResponseDTO = clienteResponse.getBody();

        Assertions.assertThat(clienteResponseDTO).isNotNull().describedAs("ClienteResponseDTO não pode ser nulo");
        Assertions.assertThat(clienteResponseDTO.getDataCriacao()).isNotNull().describedAs("Data de criação não pode ser nula");
        Assertions.assertThat(clienteResponseDTO.getDataAtualizacao()).describedAs("Data de atualização deve ser nula").isNull();
        Assertions.assertThat(clienteResponseDTO.getIdExterno()).isNotNull().describedAs("Id externo não pode ser nulo");
        Assertions.assertThat(clienteResponseDTO.getNome()).isEqualTo(clienteDomainEntity.getNome()).describedAs("Nome deve ser igual ao nome do request");
        Assertions.assertThat(clienteResponseDTO.getEndereco()).isEqualTo(clienteDomainEntity.getEndereco()).describedAs("Endereço deve ser igual ao endereço do request");
        Assertions.assertThat(clienteResponseDTO.getTelefone()).isEqualTo(clienteDomainEntity.getTelefone()).describedAs("Telefone deve ser igual ao telefone do request");
        Assertions.assertThat(clienteResponseDTO.getEmail()).isEqualTo(clienteDomainEntity.getEmail()).describedAs("Email deve ser igual ao email do request");
    }

    @Test
    void deveDeletarClientePorIdComSucesso() {
        // ARRANGE
        final var clienteDomainEntity = criarClienteDomainEntity();

        // ACT
        final var clienteResponse = clienteController.deletarClientePorIdExterno(
                "correlation-id",
                "flow-id",
                "Content-Type",
                clienteDomainEntity.getIdExterno().toString()
        );

        // ASSERT

        final var clienteResponseDTO = clienteResponse.getBody();

        Assertions.assertThat(clienteResponseDTO).isNull();
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        // ARRANGE
        final var clienteRequestDTO = criarClienteRequestDTO();
        final var clienteDomainEntity = criarClienteDomainEntity();
        Mockito.when(atualizarClienteUseCase.execute(Mockito.any(ClienteDomainEntity.class))).thenReturn(clienteDomainEntity);

        // ACT
        final var clienteResponse = clienteController.atualizarCliente(
                "correlation-id",
                "flow-id",
                "Content-Type",
                clienteDomainEntity.getIdExterno().toString(),
                clienteRequestDTO
        );

        // ASSERT

        final var clienteResponseDTO = clienteResponse.getBody();

        Mockito.verify(atualizarClienteUseCase, Mockito.times(1)).execute(Mockito.any(ClienteDomainEntity.class));

    }

    private ClienteRequestDTO criarClienteRequestDTO() {
        return ClienteRequestDTO.builder()
                .nome("nome")
                .endereco("endereco")
                .telefone("telefone")
                .email("email")
                .build();
    }

    private ClienteDomainEntity criarClienteDomainEntity() {
        return ClienteDomainEntity.builder()
                .idExterno(UUID.randomUUID())
                .nome("nome")
                .endereco("endereco")
                .telefone("telefone")
                .email("email")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(null)
                .build();
    }

}