package com.LeH.personas.Servicies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LeH.personas.dto.PersonasDTO;
import com.LeH.personas.entities.PersonaEntities;
import com.LeH.personas.repo.PersonaRepo;

@Service
public class PersonaSerImpl implements PersonasService {

    @Autowired
    private PersonaRepo personaRepo;

    @Override
    public List<PersonasDTO> obtenerPersonas() {
        return personaRepo.findAll().stream()
                .map(persona -> {
                    PersonasDTO dto = new PersonasDTO();
            //        dto.setId(persona.getId().toHexString());
                    dto.setIdpersona(persona.getIdpersona());  
                    dto.setNombres(persona.getNombres());
                    dto.setApellidos(persona.getApellidos());
                    dto.setEdad(persona.getEdad());
                    dto.setGenero(persona.getGenero());
                    dto.setStatus(persona.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonasDTO> obtenerPersonaPorId(Integer idpersona) {
        Optional<PersonaEntities> persona = personaRepo.findByIdpersona(idpersona);
        return persona.map(personaEntity -> {
            PersonasDTO dto = new PersonasDTO();
          //  dto.setId(personaEntity.getId().toHexString());
            dto.setIdpersona(personaEntity.getIdpersona());  
            dto.setNombres(personaEntity.getNombres());
            dto.setApellidos(personaEntity.getApellidos());
            dto.setEdad(personaEntity.getEdad());
            dto.setGenero(personaEntity.getGenero());
            dto.setStatus(personaEntity.getStatus());
            return dto;
        });
    }
}
