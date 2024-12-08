package com.LeH.Personas.repositorios;

import com.LeH.Personas.entidades.Personas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonasRepositorio extends MongoRepository <Personas, Integer>{
    List <Personas> findAll();

    Personas findByIdPersona(Integer idPersona);
}
