package br.com.fiapmsclientess.infrastructure.database.service;

import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.infrastructure.database.entity.ClienteEntity;
import br.com.fiapmsclientess.infrastructure.database.repository.ClinteRepository;
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
class ClienteDomainServiceTest {

    @InjectMocks
    ClienteDomainService clienteDomainService;

    @Mock
    Logger logger;

    @Mock
    ClinteRepository clinteRepository;

    @Test
    void deve_cadastrar_cliente() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();
        final var entidade = criarEntity(clienteDomainEntity);

        Mockito.when(clinteRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(entidade);

        // Act
        final var entidadeCriada = clienteDomainService.cadastrarCliente(clienteDomainEntity);

        // Assert
        Mockito.verify(logger, Mockito.times(1)).info("Cadastrando cliente: {}", clienteDomainEntity.getNome());
        Mockito.verify(logger, Mockito.times(1)).info("Cliente cadastrado com sucesso: {}", entidadeCriada.getNome());

        Assertions.assertThat(entidadeCriada).isNotNull().describedAs("Entidade criada não pode ser nula");
        Assertions.assertThat(entidadeCriada.getNome()).isEqualTo(clienteDomainEntity.getNome()).describedAs("Nome deve ser igual");
        Assertions.assertThat(entidadeCriada.getEndereco()).isEqualTo(clienteDomainEntity.getEndereco()).describedAs("Endereço deve ser igual");
        Assertions.assertThat(entidadeCriada.getTelefone()).isEqualTo(clienteDomainEntity.getTelefone()).describedAs("Telefone deve ser igual");
        Assertions.assertThat(entidadeCriada.getEmail()).isEqualTo(clienteDomainEntity.getEmail()).describedAs("Email deve ser igual");
        Assertions.assertThat(entidadeCriada.getDataCriacao()).isEqualTo(clienteDomainEntity.getDataCriacao()).describedAs("Data de criação deve ser igual");
        Assertions.assertThat(entidadeCriada.getDataAtualizacao()).isEqualTo(clienteDomainEntity.getDataAtualizacao()).describedAs("Data de atualização deve ser igual");

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

    private ClienteEntity criarEntity(ClienteDomainEntity clienteDomainEntity) {
        return new ClienteEntity()
                .setIdExterno(clienteDomainEntity.getIdExterno())
                .setNome(clienteDomainEntity.getNome())
                .setEndereco(clienteDomainEntity.getEndereco())
                .setTelefone(clienteDomainEntity.getTelefone())
                .setEmail(clienteDomainEntity.getEmail())
                .setDataCriacao(clienteDomainEntity.getDataCriacao())
                .setDataAtualizacao(clienteDomainEntity.getDataAtualizacao());
    }
}