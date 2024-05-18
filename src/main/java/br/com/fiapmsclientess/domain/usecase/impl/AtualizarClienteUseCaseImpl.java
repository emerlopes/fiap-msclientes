package br.com.fiapmsclientess.domain.usecase.impl;


import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.repository.ClienteDomainRepository;
import br.com.fiapmsclientess.domain.usecase.AtualizarClienteUseCase;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AtualizarClienteUseCaseImpl implements AtualizarClienteUseCase {

    private final Logger logger;

    private final ClienteDomainRepository clienteDomainRepository;

    public AtualizarClienteUseCaseImpl(
            final Logger logger,
            final ClienteDomainRepository clienteDomainRepository
    ) {
        this.logger = logger;
        this.clienteDomainRepository = clienteDomainRepository;
    }

    @Override
    public ClienteDomainEntity execute(
            final ClienteDomainEntity clienteDomainEntity
    ) {

        logger.info("Atualizando cliente: {}",
                clienteDomainEntity.getIdExterno()
        );

        return clienteDomainRepository.atualizarCliente(clienteDomainEntity);
    }

}
