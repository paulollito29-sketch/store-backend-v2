package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.ContactMapper;
import com.example.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactFindAll> findAll() {
        return contactRepository.findAllByEnabledIsTrueOrderByIdContactDesc().stream()
                .map(ContactMapper::toContactFindAll)
                .toList();
    }

    public ContactCreated create(ContactCreate dto) {
        if (contactRepository.existsByEnabledIsTrueAndPhone(dto.phone())) {
            throw new ResourceAlreadyExistsException("this phone: " + dto.phone() + " already exists");
        }

        if (contactRepository.existsByEnabledIsTrueAndEmailIgnoreCase(dto.email())) {
            throw new ResourceAlreadyExistsException("this email: " + dto.email() + " already exists");
        }

        var contactEntity = ContactMapper.toEntity(dto);
        var contactSaved = contactRepository.save(contactEntity);
        return ContactMapper.toContactCreated(contactSaved);
    }

    public ContactFindOne findOne(Long id) {
        var contact = contactRepository.findFirstByEnabledIsTrueAndIdContact(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        return ContactMapper.toContactFindOne(contact);
    }

    public ContactUpdated update(Long id, ContactUpdate dto) {
        var contact = contactRepository.findFirstByEnabledIsTrueAndIdContact(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        if (contactRepository.existsByEnabledIsTrueAndIdContactNotAndPhone(id, dto.phone())) {
            throw new ResourceAlreadyExistsException("this phone: " + dto.phone() + " already exists");
        }

        if (contactRepository.existsByEnabledIsTrueAndIdContactNotAndEmailIgnoreCase(id, dto.email())) {
            throw new ResourceAlreadyExistsException("this email: " + dto.email() + " already exists");
        }

        var contactEntity = ContactMapper.toEntity(contact, dto);
        var contactSaved = contactRepository.save(contactEntity);
        return ContactMapper.toContactUpdated(contactSaved);
    }

    public void delete(Long id) {
        var contact = contactRepository.findFirstByEnabledIsTrueAndIdContact(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        var contactEntity = ContactMapper.toEntity(contact);
        contactRepository.save(contactEntity);
    }
}
