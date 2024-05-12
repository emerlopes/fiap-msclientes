package br.com.fiapmsclientess.domain.usecase.impl;

import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.repository.ClienteDomainRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CadastrarClienteUseCaseImplTest {

    @InjectMocks
    CadastrarClienteUseCaseImpl cadastrarClienteUseCaseImpl;

    @Mock
    Logger logger;

    @Mock
    ClienteDomainRepository clienteDomainRepository;

    @Test
    void deveCadastrarCliente() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();

        Mockito.when(clienteDomainRepository.cadastrarCliente(clienteDomainEntity)).thenReturn(clienteDomainEntity);

        // Act
        final var clienteResponse = cadastrarClienteUseCaseImpl.execute(clienteDomainEntity);

        // Assert

        Mockito.verify(logger, Mockito.times(1)).info("Iniciando cadastro de cliente");

        Assertions.assertThat(clienteResponse).isNotNull().describedAs("Cliente não pode ser nulo");
        Assertions.assertThat(clienteResponse.getIdExterno()).isNotNull().describedAs("Id externo não pode ser nulo");
        Assertions.assertThat(clienteResponse.getNome()).isEqualTo(clienteDomainEntity.getNome()).describedAs("Nome do cliente deve ser igual ao nome informado");
        Assertions.assertThat(clienteResponse.getEndereco()).isEqualTo(clienteDomainEntity.getEndereco()).describedAs("Endereço do cliente deve ser igual ao endereço informado");
        Assertions.assertThat(clienteResponse.getTelefone()).isEqualTo(clienteDomainEntity.getTelefone()).describedAs("Telefone do cliente deve ser igual ao telefone informado");
        Assertions.assertThat(clienteResponse.getEmail()).isEqualTo(clienteDomainEntity.getEmail()).describedAs("Email do cliente deve ser igual ao email informado");
        Assertions.assertThat(clienteResponse.getDataCriacao()).isNotNull().describedAs("Data de criação não pode ser nula");

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