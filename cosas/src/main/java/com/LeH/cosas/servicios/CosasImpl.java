package com.LeH.cosas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LeH.cosas.cliente.CosasFeign;
import com.LeH.cosas.dto.CosasDTO;
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
                    .map(this::mapearCosaAResultado)  // Usamos el método de referencia para simplificar
                    .collect(Collectors.toList());
    }

    @Override
    public List<CosasDTO> cosasPorId(Integer idcosa) {
        List<CosasEntidades> cosas = cosasRepositorio.CosaPorId(idcosa);  // Asumimos que solo se devuelve un objeto
        return cosas != null ? cosas.stream()
        .map(this::mapearCosaAResultado)  // Usamos el método de referencia para simplificar
        .collect(Collectors.toList()) : null;  // Devolvemos un único DTO
    }

    private CosasDTO mapearCosaAResultado(CosasEntidades cosa) {
        CosasDTO cosaDTO = new CosasDTO();
        cosaDTO.setIdcosa(cosa.getIdcosa());  // Nos aseguramos de que el nombre del método y la propiedad coincidan
        cosaDTO.setTipo(cosa.getTipo());
        cosaDTO.setNombre(cosa.getNombre());
        cosaDTO.setDescripcion(cosa.getDescripcion());
        //cosaDTO.setPropietario(cosa.getPropietario());
        cosaDTO.setStatus(cosa.getStatus());

        PersonasDTO persona = personasFeignClient.obtenerPersonaPorId(cosa.getPropietario());  // Llamada Feign
        cosaDTO.setPropietario(persona);

        return cosaDTO;  // Devolvemos un solo DTO
    }
}
