package com.LeH.personas.Servicies;

import java.util.List;
import java.util.Optional;


import com.LeH.personas.dto.PersonasDTO;


public interface PersonasService {
    List <PersonasDTO> obtenerPersonas();
    Optional <PersonasDTO> obtenerPersonaPorId(Integer idpersona);
    List <PersonasDTO> obtenerCosasPorPropietario(Integer propietario);
}
