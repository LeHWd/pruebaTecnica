package com.LeH.personas.Servicies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LeH.personas.client.PersonasFeign;
import com.LeH.personas.dto.CosasDTO;
import com.LeH.personas.dto.PersonasDTO;
import com.LeH.personas.dto.PersonasDetalladoDTO;
import com.LeH.personas.entities.PersonaEntities;
import com.LeH.personas.repo.PersonaRepo;

@Service
public class PersonaSerImpl implements PersonasService {

    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    private PersonasFeign personasFeign;

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

    // Método para obtener "cosas" por propietario (usando Feign)
    

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

    @Override
public List<PersonasDetalladoDTO> obtenerTodasLasPersonasDetalladas() {
    // Obtenemos todas las personas
    List<PersonaEntities> personasEntities = personaRepo.findAll();
    
    // Mapeamos las entidades de persona a DTO de detalle
    return personasEntities.stream()
            .map(persona -> {
                // Creamos el DTO para la persona detallada
                PersonasDetalladoDTO dto = new PersonasDetalladoDTO();
                dto.setIdpersona(persona.getIdpersona());
                dto.setNombres(persona.getNombres());
                dto.setApellidos(persona.getApellidos());
                dto.setEdad(persona.getEdad());
                dto.setGenero(persona.getGenero());
                dto.setStatus(persona.getStatus());
                
                // Obtenemos las "cosas" asociadas a esta persona
                List<CosasDTO> cosas = personasFeign.obtenerCosasPorPropietario(persona.getIdpersona());
                dto.setCosas(cosas);
                
                return dto;
            })
            .collect(Collectors.toList());
}

    @Override
    public PersonasDetalladoDTO obtenerPersonaDetallada(Integer idpersona) {
        // Obtener la persona de la base de datos
        Optional<PersonaEntities> personaEntity = personaRepo.findByIdpersona(idpersona);

        if (personaEntity.isPresent()) {
            PersonaEntities persona = personaEntity.get();

            // Mapear los datos básicos a PersonasDetalladoDTO
            PersonasDetalladoDTO detalladoDTO = new PersonasDetalladoDTO();
            detalladoDTO.setIdpersona(persona.getIdpersona());
            detalladoDTO.setNombres(persona.getNombres());
            detalladoDTO.setApellidos(persona.getApellidos());
            detalladoDTO.setEdad(persona.getEdad());
            detalladoDTO.setGenero(persona.getGenero());
            detalladoDTO.setStatus(persona.getStatus());

            // Obtener las cosas asociadas mediante Feign
            List<CosasDTO> cosas = personasFeign.obtenerCosasPorPropietario(idpersona);
            detalladoDTO.setCosas(cosas);

            return detalladoDTO;
        }

        // Si no se encuentra la persona, retornar null (podrías lanzar una excepción personalizada si lo prefieres)
        return null;
    }

    

}
