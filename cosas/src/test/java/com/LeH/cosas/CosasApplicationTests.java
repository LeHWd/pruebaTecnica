package com.LeH.cosas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.LeH.cosas.cliente.CosasFeign;
import com.LeH.cosas.dto.CosasDTO;
import com.LeH.cosas.dto.CosasDetalladoDTO;
import com.LeH.cosas.dto.PersonasDTO;
import com.LeH.cosas.entidades.CosasEntidades;
import com.LeH.cosas.repositorio.CosasRepositorio;
import com.LeH.cosas.servicios.CosasImpl;


@ExtendWith(MockitoExtension.class)
class CosasApplicationTests {

	@InjectMocks
    private CosasImpl cosasImpl;

    @Mock
    private CosasRepositorio cosasRepositorio;

    @Mock
    private CosasFeign cosasFeign;

	@Test
	void testObtenerCosas() {
    // Simula datos de salida del repositorio
    CosasEntidades entidad = new CosasEntidades();
    entidad.setIdcosa(1);
    entidad.setTipo("Electrónico");
    entidad.setNombre("Smartphone");
    entidad.setDescripcion("Un dispositivo móvil avanzado");
    entidad.setPropietario(101);
    entidad.setStatus("Disponible");

    // Configura el mock para el repositorio
    when(cosasRepositorio.encontrarCosas()).thenReturn(Collections.singletonList(entidad));

    // Llama al método del servicio
    List<CosasDTO> resultado = cosasImpl.obtenerCosas();

    // Validaciones generales
    assertNotNull(resultado, "El resultado no debe ser nulo");
    assertEquals(1, resultado.size(), "El tamaño de la lista debe ser 1");

    // Validaciones de los campos del DTO
    CosasDTO dto = resultado.get(0);
    assertEquals(1, dto.getIdcosa(), "El ID de la cosa debe ser 1");
    assertEquals("Electrónico", dto.getTipo(), "El tipo debe ser 'Electrónico'");
    assertEquals("Smartphone", dto.getNombre(), "El nombre debe ser 'Smartphone'");
    assertEquals("Un dispositivo móvil avanzado", dto.getDescripcion(), "La descripción no coincide");
    assertEquals(101, dto.getPropietario(), "El propietario debe ser 101");
    assertEquals("Disponible", dto.getStatus(), "El estado debe ser 'Disponible'");
}

@Test
void testCosasPorId() {
    // Simula datos de salida del repositorio
    CosasEntidades entidad = new CosasEntidades();
    entidad.setIdcosa(2);
    entidad.setTipo("Mueble");
    entidad.setNombre("Silla");
    entidad.setDescripcion("Silla ergonómica de oficina");
    entidad.setPropietario(102);
    entidad.setStatus("Disponible");

    // Configura el mock para el repositorio
    when(cosasRepositorio.CosaPorId(2)).thenReturn(Collections.singletonList(entidad));

    // Llama al método del servicio
    List<CosasDTO> resultado = cosasImpl.cosasPorId(2);

    // Validaciones generales
    assertNotNull(resultado, "El resultado no debe ser nulo");
    assertEquals(1, resultado.size(), "El tamaño de la lista debe ser 1");

    // Validaciones de los campos del DTO
    CosasDTO dto = resultado.get(0);
    assertEquals(2, dto.getIdcosa(), "El ID de la cosa debe ser 2");
    assertEquals("Mueble", dto.getTipo(), "El tipo debe ser 'Mueble'");
    assertEquals("Silla", dto.getNombre(), "El nombre debe ser 'Silla'");
    assertEquals("Silla ergonómica de oficina", dto.getDescripcion(), "La descripción no coincide");
    assertEquals(102, dto.getPropietario(), "El propietario debe ser 102");
    assertEquals("Disponible", dto.getStatus(), "El estado debe ser 'Disponible'");
}

@Test
void testCosasDetalladas() {
    // Datos simulados para la entidad
    CosasEntidades entidad = new CosasEntidades();
    entidad.setIdcosa(1);
    entidad.setTipo("Electrónico");
    entidad.setNombre("Teléfono");
    entidad.setDescripcion("Teléfono inteligente");
    entidad.setPropietario(100);
    entidad.setStatus("En uso");

    // Datos simulados para PersonasDTO
    PersonasDTO personaSimulada = new PersonasDTO();
    personaSimulada.setIdpersona(100);
    personaSimulada.setNombres("Juan");
    personaSimulada.setApellidos("Pérez");
    personaSimulada.setEdad(30);
    personaSimulada.setGenero("Masculino");
    personaSimulada.setStatus("Activo");

    // Configuración de los mocks
    when(cosasRepositorio.encontrarCosas()).thenReturn(Collections.singletonList(entidad));
    when(cosasFeign.obtenerPersonaPorId(100)).thenReturn(personaSimulada);

    // Llamada al método
    List<CosasDetalladoDTO> resultado = cosasImpl.cosasDetalladas();

    // Validaciones generales
    assertNotNull(resultado, "El resultado no debe ser nulo");
    assertEquals(1, resultado.size(), "El tamaño de la lista debe ser 1");

    // Validaciones de los campos del DTO
    CosasDetalladoDTO dto = resultado.get(0);
    assertEquals(1, dto.getIdcosa(), "El ID de la cosa debe ser 1");
    assertEquals("Electrónico", dto.getTipo(), "El tipo debe ser 'Electrónico'");
    assertEquals("Teléfono", dto.getNombre(), "El nombre debe ser 'Teléfono'");
    assertEquals("Teléfono inteligente", dto.getDescripcion(), "La descripción no coincide");
    assertEquals("En uso", dto.getStatus(), "El estado debe ser 'En uso'");

    // Validaciones del propietario
    assertNotNull(dto.getPropietario(), "El propietario no debe ser nulo");
    assertEquals(100, dto.getPropietario().getIdpersona(), "El ID del propietario no coincide");
    assertEquals("Juan", dto.getPropietario().getNombres(), "El nombre del propietario no coincide");
    assertEquals("Pérez", dto.getPropietario().getApellidos(), "El apellido del propietario no coincide");
    assertEquals(30, dto.getPropietario().getEdad(), "La edad del propietario no coincide");
    assertEquals("Masculino", dto.getPropietario().getGenero(), "El género del propietario no coincide");
    assertEquals("Activo", dto.getPropietario().getStatus(), "El estado del propietario no coincide");
}

@Test
void testCosasDetalladoPorPropietario() {
    // Datos simulados para la entidad
    CosasEntidades entidad = new CosasEntidades();
    entidad.setIdcosa(1);
    entidad.setTipo("Electrodoméstico");
    entidad.setNombre("Refrigerador");
    entidad.setDescripcion("Refrigerador de doble puerta");
    entidad.setPropietario(300);
    entidad.setStatus("Disponible");

    // Datos simulados para PersonasDTO
    PersonasDTO personaSimulada = new PersonasDTO();
    personaSimulada.setIdpersona(300);
    personaSimulada.setNombres("Carlos");
    personaSimulada.setApellidos("López");
    personaSimulada.setEdad(35);
    personaSimulada.setGenero("Masculino");
    personaSimulada.setStatus("Activo");

    // Configuración de los mocks
    when(cosasRepositorio.CosasPorPropietario(300)).thenReturn(Collections.singletonList(entidad));
    when(cosasFeign.obtenerPersonaPorId(300)).thenReturn(personaSimulada);

    // Llamada al método
    List<CosasDetalladoDTO> resultado = cosasImpl.cosasDetallado(300);

    // Validaciones generales
    assertNotNull(resultado, "El resultado no debe ser nulo");
    assertEquals(1, resultado.size(), "El tamaño de la lista debe ser 1");

    // Validaciones de los campos del DTO
    CosasDetalladoDTO dto = resultado.get(0);
    assertEquals(1, dto.getIdcosa(), "El ID de la cosa debe ser 1");
    assertEquals("Electrodoméstico", dto.getTipo(), "El tipo debe ser 'Electrodoméstico'");
    assertEquals("Refrigerador", dto.getNombre(), "El nombre debe ser 'Refrigerador'");
    assertEquals("Refrigerador de doble puerta", dto.getDescripcion(), "La descripción no coincide");
    assertEquals("Disponible", dto.getStatus(), "El estado debe ser 'Disponible'");

    // Validaciones del propietario
    assertNotNull(dto.getPropietario(), "El propietario no debe ser nulo");
    assertEquals(300, dto.getPropietario().getIdpersona(), "El ID del propietario no coincide");
    assertEquals("Carlos", dto.getPropietario().getNombres(), "El nombre del propietario no coincide");
    assertEquals("López", dto.getPropietario().getApellidos(), "El apellido del propietario no coincide");
    assertEquals(35, dto.getPropietario().getEdad(), "La edad del propietario no coincide");
    assertEquals("Masculino", dto.getPropietario().getGenero(), "El género del propietario no coincide");
    assertEquals("Activo", dto.getPropietario().getStatus(), "El estado del propietario no coincide");
}

@Test
void testObtenerCosasPorPropietario() {
    // Datos simulados
    CosasEntidades entidad = new CosasEntidades();
    entidad.setIdcosa(3);
    entidad.setTipo("Vehículo");
    entidad.setNombre("Carro");
    entidad.setDescripcion("Carro eléctrico");
    entidad.setPropietario(300);
    entidad.setStatus("Nuevo");

    // Configuración del mock
    when(cosasRepositorio.CosasPorPropietario(300)).thenReturn(Collections.singletonList(entidad));

    // Llamada al método
    List<CosasDTO> resultado = cosasImpl.obtenerCosasPorPropietario(300);

    // Validaciones generales
    assertNotNull(resultado, "El resultado no debe ser nulo");
    assertEquals(1, resultado.size(), "El tamaño de la lista debe ser 1");

    // Validaciones de los campos del DTO
    CosasDTO dto = resultado.get(0);
    assertEquals(3, dto.getIdcosa(), "El ID de la cosa debe ser 3");
    assertEquals("Vehículo", dto.getTipo(), "El tipo debe ser 'Vehículo'");
    assertEquals("Carro", dto.getNombre(), "El nombre debe ser 'Carro'");
    assertEquals("Carro eléctrico", dto.getDescripcion(), "La descripción no coincide");
    assertEquals(300, dto.getPropietario(), "El propietario debe ser 300");
    assertEquals("Nuevo", dto.getStatus(), "El estado debe ser 'Nuevo'");
}


}
