package br.com.fiapmsclientess.domain.usecase.impl;

import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.repository.ClienteDomainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AtualizarClienteUseCaseImplTest {

    @InjectMocks
    AtualizarClienteUseCaseImpl atualizarClienteUseCase;

    @Mock
    Logger logger;

    @Mock
    ClienteDomainRepository clienteDomainRepository;

    @Test
    void deveAtualizarCliente() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();
        Mockito.when(clienteDomainRepository.atualizarCliente(clienteDomainEntity)).thenReturn(clienteDomainEntity);

        // Act
        final var clienteResponse = atualizarClienteUseCase.execute(clienteDomainEntity);

        // Assert
        assertNotNull(clienteResponse, "Cliente não pode ser nulo");
        assertNotNull(clienteResponse.getIdExterno(), "Id externo não pode ser nulo");
        assertEquals(clienteResponse.getNome(), clienteDomainEntity.getNome(), "Nome do cliente deve ser igual ao nome informado");
        assertEquals(clienteResponse.getEndereco(), clienteDomainEntity.getEndereco(), "Endereço do cliente deve ser igual ao endereço informado");
        assertEquals(clienteResponse.getTelefone(), clienteDomainEntity.getTelefone(), "Telefone do cliente deve ser igual ao telefone informado");
        assertEquals(clienteResponse.getEmail(), clienteDomainEntity.getEmail(), "Email do cliente deve ser igual ao email informado");
        assertNotNull(clienteResponse.getDataCriacao(), "Data de criação não pode ser nula");
        assertNotNull(clienteResponse.getDataAtualizacao(), "Data de atualização não pode ser nula");
    }

    private ClienteDomainEntity criarClienteDomainEntity() {
        return ClienteDomainEntity.builder()
                .idExterno(UUID.randomUUID())
                .nome("nome")
                .endereco("endereco")
                .telefone("telefone")
                .email("email")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();
    }
}