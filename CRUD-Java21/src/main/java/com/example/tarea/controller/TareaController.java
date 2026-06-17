package com.example.tarea.controller;

import com.example.tarea.dto.TareaDTO;
import com.example.tarea.service.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TareaController {

    private final TareaService tareaService;

    /**
     * GET /api/tareas
     * Obtiene todas las tareas
     */
    @GetMapping
    public ResponseEntity<List<TareaDTO>> obtenerTodas() {
        return ResponseEntity.ok(tareaService.obtenerTodas());
    }

    /**
     * GET /api/tareas/{id}
     * Obtiene una tarea por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> obtenerPorId(@PathVariable Long id) {
        try {
            TareaDTO tarea = tareaService.obtenerPorId(id);
            return ResponseEntity.ok(tarea);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /api/tareas
     * Crea una nueva tarea
     */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody TareaDTO tareaDTO) {
        try {
            TareaDTO nuevaTarea = tareaService.crear(tareaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * PUT /api/tareas/{id}
     * Actualiza una tarea existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TareaDTO tareaDTO) {
        try {
            TareaDTO tareaActualizada = tareaService.actualizar(id, tareaDTO);
            return ResponseEntity.ok(tareaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(e.getMessage().contains("no encontrada") ? 
                    HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * DELETE /api/tareas/{id}
     * Elimina una tarea
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            tareaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Clase interna para respuestas de error
     */
    public static class ErrorResponse {
        public String mensaje;

        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
