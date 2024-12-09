package com.LeH.cosas.servicios;

import java.util.List;

import com.LeH.cosas.dto.CosasDTO;
import com.LeH.cosas.dto.CosasDetalladoDTO;



public interface CosasServicio {

    List<CosasDTO> obtenerCosas ();
    List<CosasDTO> cosasPorId(Integer idcosa);
    //List<CosasDTO> cosasPorPropietario(Integer propietario);
    List<CosasDetalladoDTO> cosasDetallado();
}
