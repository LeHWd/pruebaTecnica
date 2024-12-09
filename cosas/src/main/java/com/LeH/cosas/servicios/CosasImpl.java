package com.LeH.cosas.servicios;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LeH.cosas.cliente.CosasFeign;
import com.LeH.cosas.dto.CosasDTO;
import com.LeH.cosas.dto.CosasDetalladoDTO;
import com.LeH.cosas.dto.PersonasDTO;
import com.LeH.cosas.entidades.CosasEntidades;
import com.LeH.cosas.repositorio.CosasRepositorio;

@Service
public class CosasImpl implements CosasServicio {


    @Autowired
    private CosasRepositorio cosasRepositorio;

    @Autowired
    private CosasFeign personasFeignClient;  // Inyectamos el cliente Feign

    @Override
    public List<CosasDTO> obtenerCosas() {
        List<CosasEntidades> cosas = cosasRepositorio.encontrarCosas();
        return cosas.stream()
                    .map(cosa -> mapearCosaSinPropietario(cosa)) // Mapeo sin el propietario
                    .collect(Collectors.toList());
    }
    
     
    @Override
    public List<CosasDTO> cosasPorId(Integer idcosa) {
        List<CosasEntidades> cosa = cosasRepositorio.CosaPorId(idcosa);  // Traemos la lista (aunque debería ser un único elemento)
        if (cosa != null && !cosa.isEmpty()) {
            // Si la lista no está vacía, mapeamos el primer (y único) elemento
            return Collections.singletonList(mapearCosaSinPropietario(cosa.get(0)));
        } else {
            // Si la lista está vacía o es null, devolvemos una lista vacía
            return Collections.emptyList();
        }
    }

     @Override
     public List<CosasDetalladoDTO> cosasDetallado() {
        // Recuperamos todas las cosas
        List<CosasEntidades> cosas = cosasRepositorio.findAll();  

        return cosas.stream()
                    .map(cosa -> {
                        CosasDetalladoDTO dto = new CosasDetalladoDTO();
                        dto.setIdcosa(cosa.getIdcosa());
                        dto.setTipo(cosa.getTipo());
                        dto.setNombre(cosa.getNombre());
                        dto.setDescripcion(cosa.getDescripcion());
                        dto.setPropietario(cosa.getPropietario());
                        dto.setStatus(cosa.getStatus());

                        // Usamos el Feign Client para obtener los detalles de la persona
                        var persona = personasFeignClient.obtenerPersonaPorId(cosa.getPropietario());
                        if (persona != null) {
                            dto.setNombresPropietario(persona.getNombres());
                            dto.setApellidosPropietario(persona.getApellidos());                         
                            dto.setEdadPropietario(persona.getEdad());
                            dto.setGeneroPropietario(persona.getGenero());
                            dto.setStatusPropietario(persona.getStatus());
                                

                         }

                        return dto;
                    })
                    .collect(Collectors.toList());
    }
/*
    @Override
    public List<CosasDTO> cosasPorPropietario(Integer propietario) {
        List<CosasEntidades> cosas = cosasRepositorio.CosasPorPropietario(propietario);
        return cosas.stream()
                    .map(this::mapearCosaConPropietario) // Usamos el mapeo con propietario
                    .collect(Collectors.toList());
    }
  */
    private CosasDTO mapearCosaSinPropietario(CosasEntidades cosa) {
        CosasDTO cosaDTO = new CosasDTO();
        cosaDTO.setIdcosa(cosa.getIdcosa());
        cosaDTO.setTipo(cosa.getTipo());
        cosaDTO.setNombre(cosa.getNombre());
        cosaDTO.setDescripcion(cosa.getDescripcion());
        cosaDTO.setPropietario(cosa.getPropietario());
        cosaDTO.setStatus(cosa.getStatus());
        
        // No se mapea el propietario aquí
        // cosaDTO.setPropietario(persona); <-- Esta línea se elimina
    
        return cosaDTO;
    }
 
    /* 
    private CosasDTO mapearCosaConPropietario(CosasEntidades cosa) {
        CosasDTO cosaDTO = new CosasDTO();
        cosaDTO.setIdcosa(cosa.getIdcosa());
        cosaDTO.setTipo(cosa.getTipo());
        cosaDTO.setNombre(cosa.getNombre());
        cosaDTO.setDescripcion(cosa.getDescripcion());
        cosaDTO.setStatus(cosa.getStatus());
    
        // Mapeo del propietario (llamado a FeignClient)
        PersonasDTO persona = personasFeignClient.obtenerPersonaPorId(cosa.getPropietario());
        cosaDTO.setPropietario(persona);
    
        return cosaDTO;
    }
        */
}
