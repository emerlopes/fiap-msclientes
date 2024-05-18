package br.com.fiapmsclientess.domain.usecase.impl;


import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.repository.ClienteDomainRepository;
import br.com.fiapmsclientess.domain.usecase.CadastrarClienteUseCase;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CadastrarClienteUseCaseImpl implements CadastrarClienteUseCase {

    private final Logger logger;

    private final ClienteDomainRepository clienteDomainRepository;

    public CadastrarClienteUseCaseImpl(
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

        logger.info("Iniciando cadastro de cliente");

        return clienteDomainRepository.cadastrarCliente(clienteDomainEntity);
    }

}
