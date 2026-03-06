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
        return tareaService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable String id) {
        Optional<Tarea> tarea = tareaService.findById(id);
        return tarea.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return tareaService.crearTareaConHilo(tarea);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable String id) {
        tareaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
