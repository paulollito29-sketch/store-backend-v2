package com.example.repository;

import com.example.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {


    List<ClientEntity> findAllByEnabledIsTrueOrderByIdClientDesc();

    Optional<ClientEntity> findFirstByEnabledIsTrueAndIdClient(Long id);

    boolean existsByEnabledIsTrueAndFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    boolean existsByEnabledIsTrueAndIdClientNotAndFirstNameIgnoreCase(Long id, String name);
}
