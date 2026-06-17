package com.example.tarea.service;

import com.example.tarea.dto.TareaDTO;
import com.example.tarea.entity.Tarea;
import com.example.tarea.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;

    /**
     * Obtiene todas las tareas
     */
    public List<TareaDTO> obtenerTodas() {
        return tareaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una tarea por ID
     */
    public TareaDTO obtenerPorId(Long id) {
        return tareaRepository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
    }

    /**
     * Crea una nueva tarea
     */
    public TareaDTO crear(TareaDTO tareaDTO) {
        if (tareaDTO.getDescripcion() == null || tareaDTO.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tarea no puede estar vacía");
        }

        Tarea tarea = new Tarea();
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setFechaCreacion(LocalDateTime.now());

        Tarea tareaGuardada = tareaRepository.save(tarea);
        return convertirADTO(tareaGuardada);
    }

    /**
     * Actualiza una tarea existente
     */
    public TareaDTO actualizar(Long id, TareaDTO tareaDTO) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));

        if (tareaDTO.getDescripcion() != null && !tareaDTO.getDescripcion().trim().isEmpty()) {
            tarea.setDescripcion(tareaDTO.getDescripcion());
        }

        Tarea tareaActualizada = tareaRepository.save(tarea);
        return convertirADTO(tareaActualizada);
    }

    /**
     * Elimina una tarea
     */
    public void eliminar(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new IllegalArgumentException("Tarea no encontrada con ID: " + id);
        }
        tareaRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Tarea a DTO
     */
    private TareaDTO convertirADTO(Tarea tarea) {
        return new TareaDTO(
                tarea.getId(),
                tarea.getFechaCreacion(),
                tarea.getDescripcion()
        );
    }
}
