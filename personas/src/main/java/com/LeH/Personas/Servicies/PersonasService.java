package com.LeH.personas.Servicies;

import java.util.List;
import java.util.Optional;


import com.LeH.personas.dto.PersonasDTO;
import com.LeH.personas.dto.PersonasDetalladoDTO;


public interface PersonasService {
    List <PersonasDTO> obtenerPersonas();
    Optional <PersonasDTO> obtenerPersonaPorId(Integer idpersona);
    PersonasDetalladoDTO obtenerPersonaDetallada(Integer idpersona);
    List<PersonasDetalladoDTO> obtenerTodasLasPersonasDetalladas();
}
