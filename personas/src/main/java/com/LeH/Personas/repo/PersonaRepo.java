package com.LeH.personas.repo;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.LeH.personas.entities.PersonaEntities;

public interface PersonaRepo extends MongoRepository<PersonaEntities, ObjectId> {
    List<PersonaEntities> findAll();
    Optional<PersonaEntities> findByIdpersona(Integer idpersona);
}

