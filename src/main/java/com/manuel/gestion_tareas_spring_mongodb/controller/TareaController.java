package com.manuel.gestion_tareas_spring_mongodb.controller;

import com.manuel.gestion_tareas_spring_mongodb.model.Tarea;
import com.manuel.gestion_tareas_spring_mongodb.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    
    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }
    
    @GetMapping
    public List<Tarea> getAllTareas() {
        System.out.println("Obteniendo todas las tareas");
        List<Tarea> tareas = tareaService.findAll();
        System.out.println("Se encontraron " + tareas.size() + " tareas");
        return tareas;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable String id) {
        Optional<Tarea> tarea = tareaService.findById(id);
        return tarea.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Tarea createTarea(@RequestBody Tarea tarea) {
        System.out.println("Creando nueva tarea: " + tarea.getTitulo());
        Tarea nuevaTarea = tareaService.crearTareaConHilo(tarea);
        System.out.println("Tarea creada con id: " + nuevaTarea.getId());
        return nuevaTarea;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable String id, @RequestBody Tarea tarea) {
        Optional<Tarea> tareaExistente = tareaService.findById(id);
        if (!tareaExistente.isEmpty()) {
            Tarea tareaActual = tareaExistente.get();

            if (tarea.getTitulo() != null) {
                tareaActual.setTitulo(tarea.getTitulo());
            }
            if (tarea.getDescripcion() != null) {
                tareaActual.setDescripcion(tarea.getDescripcion());
            }
            if (tarea.getEstado() != null) {
                tareaActual.setEstado(tarea.getEstado());
            }

            return ResponseEntity.ok(tareaService.save(tareaActual));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable String id) {
        System.out.println("Eliminando tarea con id: " + id);
        tareaService.deleteById(id);
        System.out.println("Tarea eliminada exitosamente");
        return ResponseEntity.noContent().build();
    }
    
}
