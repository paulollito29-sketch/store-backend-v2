package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.ClientMapper;
import com.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository ClientRepository;

    public ClientService(ClientRepository ClientRepository) {
        this.ClientRepository = ClientRepository;
    }

    public List<ClientFindAll> findAll() {
        return ClientRepository.findAllByEnabledIsTrueOrderByIdClientDesc().stream()
                //.map(item -> ClientMapper.ToClienteFindAll(item))
                .map(ClientMapper::ToClientFindAll)
                .toList();
    }

    public ClientCreated create(ClientCreate dto) {
        if (ClientRepository.existsByEnabledIsTrueAndFirstNameIgnoreCaseAndLastNameIgnoreCase(dto.firstName(), dto.lastName())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.firstName() + " already exists");
        }
        var clientEntity = ClientMapper.ToEntity(dto);
        var clientSaved = ClientRepository.save(clientEntity);
        return ClientMapper.ToClientCreated(clientSaved);
    }

    public ClientFindOne findOne(Long id) {
        var client = ClientRepository.findFirstByEnabledIsTrueAndIdClient(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return ClientMapper.ToClientFindOne(client);
    }

    public ClientUpdated update(Long id, ClientUpdate dto) {
        var client = ClientRepository.findFirstByEnabledIsTrueAndIdClient(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        if (ClientRepository.existsByEnabledIsTrueAndFirstNameIgnoreCaseAndLastNameIgnoreCase(dto.firstName(), dto.lastName())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.firstName() + " already exists");
        }
        var clientUpdate = ClientMapper.ToEntity(client, dto);
        var clientSaved = ClientRepository.save(clientUpdate);
        return ClientMapper.ToClientUpdated(clientSaved);
    }

    public void delete(Long id) {
        var client = ClientRepository.findFirstByEnabledIsTrueAndIdClient(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        var clientDeleted = ClientMapper.ToEntity(client);
        ClientRepository.save(clientDeleted);
    }

}
