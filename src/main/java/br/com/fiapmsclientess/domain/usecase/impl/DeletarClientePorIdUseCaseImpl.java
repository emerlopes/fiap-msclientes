package br.com.fiapmsclientess.domain.usecase.impl;


import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.repository.ClienteDomainRepository;
import br.com.fiapmsclientess.domain.usecase.BuscarClientePorIdUseCase;
import br.com.fiapmsclientess.domain.usecase.DeletarClientePorIdUseCase;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DeletarClientePorIdUseCaseImpl implements DeletarClientePorIdUseCase {

    private final Logger logger;

    private final ClienteDomainRepository clienteDomainRepository;

    public DeletarClientePorIdUseCaseImpl(
            final Logger logger,
            final ClienteDomainRepository clienteDomainRepository
    ) {
        this.logger = logger;
        this.clienteDomainRepository = clienteDomainRepository;
    }

    @Override
    public void execute(
            final ClienteDomainEntity clienteDomainEntity
    ) {
        logger.info("Deletando cliente por id externo: {}", clienteDomainEntity.getIdExterno());

        clienteDomainRepository.deletarClientePorIdExterno(clienteDomainEntity);
    }

}
