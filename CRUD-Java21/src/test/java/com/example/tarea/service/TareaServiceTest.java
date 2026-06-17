package com.example.tarea.service;

import com.example.tarea.dto.TareaDTO;
import com.example.tarea.entity.Tarea;
import com.example.tarea.repository.TareaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private TareaService tareaService;

    private Tarea tarea;
    private TareaDTO tareaDTO;

    @BeforeEach
    void setUp() {
        tarea = new Tarea();
        tarea.setId(1L);
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setDescripcion("Tarea de prueba");

        tareaDTO = new TareaDTO();
        tareaDTO.setId(1L);
        tareaDTO.setFechaCreacion(LocalDateTime.now());
        tareaDTO.setDescripcion("Tarea de prueba");
    }

    @Test
    void testCrearTarea() {
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        TareaDTO resultado = tareaService.crear(tareaDTO);

        assertNotNull(resultado);
        assertEquals("Tarea de prueba", resultado.getDescripcion());
        verify(tareaRepository, times(1)).save(any(Tarea.class));
    }

    @Test
    void testObtenerPorId() {
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tarea));

        TareaDTO resultado = tareaService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tarea de prueba", resultado.getDescripcion());
    }

    @Test
    void testObtenerPorIdNoEncontrada() {
        when(tareaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> tareaService.obtenerPorId(1L));
    }

    @Test
    void testActualizarTarea() {
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tarea));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        tareaDTO.setDescripcion("Descripción actualizada");
        TareaDTO resultado = tareaService.actualizar(1L, tareaDTO);

        assertNotNull(resultado);
        assertEquals("Descripción actualizada", resultado.getDescripcion());
    }

    @Test
    void testEliminarTarea() {
        when(tareaRepository.existsById(1L)).thenReturn(true);

        tareaService.eliminar(1L);

        verify(tareaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCrearTareaConDescripcionVacia() {
        TareaDTO tareaInvalida = new TareaDTO();
        tareaInvalida.setDescripcion("");

        assertThrows(IllegalArgumentException.class, () -> tareaService.crear(tareaInvalida));
    }
}
