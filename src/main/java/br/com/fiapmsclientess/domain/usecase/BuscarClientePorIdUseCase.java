package br.com.fiapmsclientess.domain.usecase;

import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.domain.shared.IExecuteArgs;

public interface BuscarClientePorIdUseCase extends IExecuteArgs<ClienteDomainEntity, ClienteDomainEntity> {
}
