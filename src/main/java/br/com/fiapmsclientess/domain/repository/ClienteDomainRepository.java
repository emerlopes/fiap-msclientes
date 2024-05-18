package br.com.fiapmsclientess.domain.repository;

import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;

public interface ClienteDomainRepository {

    ClienteDomainEntity cadastrarCliente(ClienteDomainEntity clienteDomainEntity);

    ClienteDomainEntity buscarClientePorIdExterno(ClienteDomainEntity clienteDomainEntity);

}
