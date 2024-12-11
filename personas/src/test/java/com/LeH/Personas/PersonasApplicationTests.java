package com.LeH.personas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.LeH.personas.Servicies.PersonaSerImpl;

import com.LeH.personas.client.PersonasFeign;
import com.LeH.personas.dto.PersonasDTO;
import com.LeH.personas.dto.PersonasDetalladoDTO;
import com.LeH.personas.entities.PersonaEntities;
import com.LeH.personas.repo.PersonaRepo;


@ExtendWith(MockitoExtension.class)
class PersonasApplicationTests {

	@Mock
    private PersonaRepo personaRepo;  // Mockeamos el repositorio

    @Mock
    private PersonasFeign personasFeign;  // Mockeamos el cliente Feign

    @InjectMocks
    private PersonaSerImpl personaSerImpl;  // Servicio que estamos probando

    private PersonaEntities persona;

    @BeforeEach
    void setUp() {
        persona = new PersonaEntities();
        persona.setIdpersona(1);
        persona.setNombres("Juan");
        persona.setApellidos("Perez");
        persona.setEdad(30);
        persona.setGenero("Masculino");
        persona.setStatus("Activo");
    }

	@Test
    void testObtenerPersonas() {
        // Simulamos el comportamiento del repositorio
        when(personaRepo.findAll()).thenReturn(Arrays.asList(persona));

        // Llamamos al método del servicio
        List<PersonasDTO> personasDTO = personaSerImpl.obtenerPersonas();

        // Verificamos que el resultado no sea nulo y tenga un tamaño esperado
        assertNotNull(personasDTO);
        assertEquals(1, personasDTO.size());
        assertEquals(1, personasDTO.get(0).getIdpersona());
    }

	 @Test
    void testObtenerPersonaPorId() {
         // Simulamos el comportamiento del repositorio para buscar por id
    when(personaRepo.findByIdpersona(1)).thenReturn(Optional.of(persona));

    // Llamamos al método del servicio
    Optional<PersonasDTO> personaDTO = personaSerImpl.obtenerPersonaPorId(1);

    // Verificamos que la persona se obtuvo correctamente
    assertTrue(personaDTO.isPresent());
    assertEquals("Juan", personaDTO.get().getNombres());
    }

    @Test
    void testObtenerPersonaDetallada() {
        // Simulamos el comportamiento del repositorio
        when(personaRepo.findByIdpersona(1)).thenReturn(Optional.of(persona));

        // Simulamos la respuesta de Feign
        when(personasFeign.obtenerCosasPorPropietario(1)).thenReturn(Arrays.asList());

        // Llamamos al método del servicio
        PersonasDetalladoDTO personaDetallada = personaSerImpl.obtenerPersonaDetallada(1);

        // Verificamos que los detalles de la persona se hayan obtenido correctamente
        assertNotNull(personaDetallada);
        assertEquals("Juan", personaDetallada.getNombres());
    }

    @Test
    void testObtenerTodasLasPersonasDetalladas() {
        // Simulamos el comportamiento del repositorio
        when(personaRepo.findAll()).thenReturn(Arrays.asList(persona));

        // Simulamos la respuesta de Feign
        when(personasFeign.obtenerCosasPorPropietario(1)).thenReturn(Arrays.asList());

        // Se consume método del servicio
        List<PersonasDetalladoDTO> personasDetalladas = personaSerImpl.obtenerTodasLasPersonasDetalladas();

        // Verificamos que la lista no esté vacía
        assertNotNull(personasDetalladas);
        assertEquals(1, personasDetalladas.size());
        assertEquals("Juan", personasDetalladas.get(0).getNombres());
    }

}
