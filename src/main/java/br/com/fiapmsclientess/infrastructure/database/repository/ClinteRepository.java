package br.com.fiapmsclientess.infrastructure.database.repository;

import br.com.fiapmsclientess.infrastructure.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClinteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByIdExterno(UUID idExterno);

}
