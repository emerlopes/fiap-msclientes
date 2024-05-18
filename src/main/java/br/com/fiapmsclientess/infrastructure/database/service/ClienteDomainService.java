package br.com.fiapmsclientess.infrastructure.database.service;

import br.com.fiapmsclientess.application.exception.BusinessException;
import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;

import br.com.fiapmsclientess.domain.repository.ClienteDomainRepository;
import br.com.fiapmsclientess.infrastructure.database.entity.ClienteEntity;
import br.com.fiapmsclientess.infrastructure.database.mapper.ClienteEntityMapper;
import br.com.fiapmsclientess.infrastructure.database.repository.ClinteRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteDomainService implements ClienteDomainRepository {

    private final Logger logger;
    private final ClinteRepository clinteRepository;

    public ClienteDomainService(
            final Logger logger,
            final ClinteRepository clinteRepository
    ) {
        this.logger = logger;
        this.clinteRepository = clinteRepository;
    }

    @Override
    public ClienteDomainEntity cadastrarCliente(
            final ClienteDomainEntity clienteDomainEntity
    ) {

        logger.info("Cadastrando cliente: {}", clienteDomainEntity.getNome());

        final var entidade = ClienteEntityMapper.paraEntidade(clienteDomainEntity);
        entidade.setIdExterno(UUID.randomUUID());
        final var retornoEntidade = clinteRepository.save(entidade);

        logger.info("Cliente cadastrado com sucesso: {}", retornoEntidade.getNome());

        return ClienteDomainEntity.paraEntidadeDominio(retornoEntidade);
    }

    @Override
    public ClienteDomainEntity buscarClientePorIdExterno(
            final ClienteDomainEntity clienteDomainEntity
    ) {

        logger.info("Buscando cliente por id externo: {}",
                clienteDomainEntity.getIdExterno()
        );

        final var idExterno = clienteDomainEntity.getIdExterno();
        final var cliente = buscarClientePorIdExterno(idExterno);

        logger.info("Cliente encontrado: {}", cliente.getNome());

        return ClienteDomainEntity.paraEntidadeDominio(cliente);
    }

    @Override
    public void deletarClientePorIdExterno(
            final ClienteDomainEntity clienteDomainEntity
    ) {

        logger.info("Deletando cliente por id externo: {}",
                clienteDomainEntity.getIdExterno()
        );

        final var idExterno = clienteDomainEntity.getIdExterno();
        final var cliente = buscarClientePorIdExterno(idExterno);

        clinteRepository.delete(cliente);

        logger.info("Cliente deletado com sucesso: {}", idExterno);

    }

    @Override
    public ClienteDomainEntity atualizarCliente(
            final ClienteDomainEntity clienteDomainEntity
    ) {

        logger.info("Atualizando cliente: {}", clienteDomainEntity.getNome());

        final var idExterno = clienteDomainEntity.getIdExterno();
        final var cliente = buscarClientePorIdExterno(idExterno);

        cliente.setNome(clienteDomainEntity.getNome());
        cliente.setEndereco(clienteDomainEntity.getEndereco());
        cliente.setTelefone(clienteDomainEntity.getTelefone());
        cliente.setEmail(clienteDomainEntity.getEmail());

        final var retornoEntidade = clinteRepository.save(cliente);

        logger.info("Cliente atualizado com sucesso: {}", retornoEntidade.getNome());

        return ClienteDomainEntity.paraEntidadeDominio(retornoEntidade);
    }

    private ClienteEntity buscarClientePorIdExterno(UUID idExterno) {
        return clinteRepository.findByIdExterno(idExterno)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
    }

}
