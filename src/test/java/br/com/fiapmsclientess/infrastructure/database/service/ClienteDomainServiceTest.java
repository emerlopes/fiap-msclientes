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
import java.util.Optional;
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
    void deveCadastrarCliente() {
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

    @Test
    void deveBuscarClientePorId() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();
        final var entidade = criarEntity(clienteDomainEntity);

        Mockito.when(clinteRepository.findByIdExterno(Mockito.any(UUID.class))).thenReturn(Optional.of(entidade));

        // Act
        final var entidadeCriada = clienteDomainService.buscarClientePorIdExterno(clienteDomainEntity);

        // Assert
        Mockito.verify(logger, Mockito.times(1)).info("Buscando cliente por id externo: {}", clienteDomainEntity.getIdExterno());
        Assertions.assertThat(entidadeCriada).isNotNull().describedAs("Entidade criada não pode ser nula");
        Assertions.assertThat(entidadeCriada.getNome()).isEqualTo(clienteDomainEntity.getNome()).describedAs("Nome deve ser igual");
        Assertions.assertThat(entidadeCriada.getEndereco()).isEqualTo(clienteDomainEntity.getEndereco()).describedAs("Endereço deve ser igual");
        Assertions.assertThat(entidadeCriada.getTelefone()).isEqualTo(clienteDomainEntity.getTelefone()).describedAs("Telefone deve ser igual");
        Assertions.assertThat(entidadeCriada.getEmail()).isEqualTo(clienteDomainEntity.getEmail()).describedAs("Email deve ser igual");
        Assertions.assertThat(entidadeCriada.getDataCriacao()).isEqualTo(clienteDomainEntity.getDataCriacao()).describedAs("Data de criação deve ser igual");
        Assertions.assertThat(entidadeCriada.getDataAtualizacao()).isEqualTo(clienteDomainEntity.getDataAtualizacao()).describedAs("Data de atualização deve ser igual");
    }

    @Test
    void deveRetornarErroAoBuscarClientePorId() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();

        Mockito.when(clinteRepository.findByIdExterno(Mockito.any(UUID.class))).thenReturn(Optional.empty());

        // Act
        final var exception = assertThrows(RuntimeException.class, () -> clienteDomainService.buscarClientePorIdExterno(clienteDomainEntity));

        // Assert
        Mockito.verify(logger, Mockito.times(1)).info(
                "Buscando cliente por id externo: {}",
                clienteDomainEntity.getIdExterno()
        );
        Assertions.assertThat(exception).isNotNull().describedAs("Exceção não pode ser nula");
        Assertions.assertThat(exception.getMessage()).isEqualTo("Cliente não encontrado").describedAs("Mensagem de erro deve ser igual");
    }

    @Test
    void deveDeletarClientePorId() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();
        final var entidade = criarEntity(clienteDomainEntity);

        Mockito.when(clinteRepository.findByIdExterno(Mockito.any(UUID.class))).thenReturn(Optional.of(entidade));

        // Act
        clienteDomainService.deletarClientePorIdExterno(clienteDomainEntity);

        // Assert
        Mockito.verify(logger, Mockito.times(1)).info("Deletando cliente por id externo: {}", clienteDomainEntity.getIdExterno());
        Mockito.verify(clinteRepository, Mockito.times(1)).delete(entidade);
    }

    @Test
    void deveAtualizarCliente() {
        // Arrange
        final var clienteDomainEntity = criarClienteDomainEntity();
        final var entidade = criarEntity(clienteDomainEntity);

        Mockito.when(clinteRepository.findByIdExterno(Mockito.any(UUID.class))).thenReturn(Optional.of(entidade));
        Mockito.when(clinteRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(entidade);

        // Act
        final var entidadeCriada = clienteDomainService.atualizarCliente(clienteDomainEntity);

        // Assert
        Mockito.verify(logger, Mockito.times(1)).info("Atualizando cliente: {}", clienteDomainEntity.getNome());
        Mockito.verify(logger, Mockito.times(1)).info("Cliente atualizado com sucesso: {}", entidadeCriada.getNome());

        Assertions.assertThat(entidadeCriada).isNotNull().describedAs("Entidade criada não pode ser nula");
        Assertions.assertThat(entidadeCriada.getNome()).isEqualTo(clienteDomainEntity.getNome()).describedAs("Nome deve ser igual");
        Assertions.assertThat(entidadeCriada.getEndereco()).isEqualTo(clienteDomainEntity.getEndereco()).describedAs("Endereço deve ser igual");
        Assertions.assertThat(entidadeCriada.getTelefone()).isEqualTo(clienteDomainEntity.getTelefone()).describedAs("Telefone deve ser igual");
        Assertions.assertThat(entidadeCriada.getEmail()).isEqualTo(clienteDomainEntity.getEmail()).describedAs("Email deve ser igual");
        Assertions.assertThat(entidadeCriada.getDataCriacao()).isEqualTo(clienteDomainEntity.getDataCriacao()).describedAs("Data de criação deve ser igual");
//        Assertions.assertThat(entidadeCriada.getDataAtualizacao()).isEqualTo(clienteDomainEntity.getDataAtualizacao()).describedAs("Data de atualização deve ser igual");
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