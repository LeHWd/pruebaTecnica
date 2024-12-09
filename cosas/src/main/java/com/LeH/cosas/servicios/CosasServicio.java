package com.LeH.cosas.servicios;

import java.util.List;

import com.LeH.cosas.dto.CosasDTO;



public interface CosasServicio {

    List<CosasDTO> obtenerCosas ();
    List<CosasDTO> cosasPorId(Integer idcosa);
}
