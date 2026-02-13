package com.example.repository;

import com.example.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    List<ContactEntity> findAllByEnabledIsTrueOrderByIdContactDesc();

    Optional<ContactEntity> findFirstByEnabledIsTrueAndIdContact(Long id);

    boolean existsByEnabledIsTrueAndPhone(String phone);

    boolean existsByEnabledIsTrueAndEmailIgnoreCase(String email);

    boolean existsByEnabledIsTrueAndIdContactNotAndPhone(Long id, String phone);

    boolean existsByEnabledIsTrueAndIdContactNotAndEmailIgnoreCase(Long id, String email);
}