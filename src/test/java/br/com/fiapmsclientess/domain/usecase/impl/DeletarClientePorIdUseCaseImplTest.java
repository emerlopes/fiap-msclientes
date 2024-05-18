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
class DeletarClientePorIdUseCaseImplTest {

    @InjectMocks
    DeletarClientePorIdUseCaseImpl deletarClientePorIdUseCaseImpl;

    @Mock
    Logger logger;

    @Mock
    ClienteDomainRepository clienteDomainRepository;

    @Test
    void deveDeletarClientePorId() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();

        // Act
        deletarClientePorIdUseCaseImpl.execute(clienteDomainEntity);

        // Assert
        Mockito.verify(logger, Mockito.times(1)).info("Deletando cliente por id externo: {}", clienteDomainEntity.getIdExterno());
        Mockito.verify(clienteDomainRepository, Mockito.times(1)).deletarClientePorIdExterno(clienteDomainEntity);
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