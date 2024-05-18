package br.com.fiapmsclientess.infrastructure.database.mapper;

import br.com.fiapmsclientess.domain.entity.ClienteDomainEntity;
import br.com.fiapmsclientess.infrastructure.database.entity.ClienteEntity;

public class ClienteEntityMapper {

    public static ClienteEntity paraEntidade(
            final ClienteDomainEntity clienteDomainEntity
    ) {
        return new ClienteEntity()
                .setNome(clienteDomainEntity.getNome())
                .setEndereco(clienteDomainEntity.getEndereco())
                .setTelefone(clienteDomainEntity.getTelefone())
                .setEmail(clienteDomainEntity.getEmail())
                .setDataCriacao(clienteDomainEntity.getDataCriacao())
                .setDataAtualizacao(clienteDomainEntity.getDataAtualizacao());
    }
}
