package br.com.fiapmsclientess.infrastructure.database.service;

import br.com.fiapmsclientess.application.exception.BusinessException;
import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;

import br.com.fiapmsclientess.domain.repository.ClienteDomainRepository;
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
                clienteDomainEntity.getNome(),
                clienteDomainEntity.getIdExterno()
        );

        final var idExterno = clienteDomainEntity.getIdExterno();
        final var retornoEntidade = clinteRepository.findByIdExterno(idExterno);

        if (retornoEntidade.isEmpty()) {
            logger.info("Cliente não encontrado: {}", idExterno);
            throw new BusinessException("Cliente não encontrado");
        }

        final var cliente = retornoEntidade.get();

        logger.info("Cliente encontrado: {}", cliente.getNome());

        return ClienteDomainEntity.paraEntidadeDominio(cliente);
    }

}
