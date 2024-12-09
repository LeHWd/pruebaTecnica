package com.LeH.personas.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.LeH.personas.entities.PersonaEntities;

public interface PersonaRepo extends MongoRepository<PersonaEntities, Integer> {
    List<PersonaEntities> findAll();
    Optional<PersonaEntities> findByIdpersona(Integer idpersona);
}

