package br.com.fiapmsclientess.infrastructure.database.repository;

import br.com.fiapmsclientess.infrastructure.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinteRepository extends JpaRepository<ClienteEntity, Long> {
}
