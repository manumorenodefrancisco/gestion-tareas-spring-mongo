package com.manuel.gestion_tareas_spring_mongodb.service;

import com.manuel.gestion_tareas_spring_mongodb.model.Tarea;
import com.manuel.gestion_tareas_spring_mongodb.repository.TareaRepository;

public class HiloRunnable implements Runnable {
    
    private final Tarea tarea;
    private final TareaRepository tareaRepository;
    
    public HiloRunnable(Tarea tarea, TareaRepository tareaRepository) {
        this.tarea = tarea;
        this.tareaRepository = tareaRepository;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            tarea.setEstado(Tarea.Estado.COMPLETADA);
            tarea.setfechacompletada(java.time.Instant.now());
            tareaRepository.save(tarea);
            System.out.println("Task completed: " + tarea.getTitulo());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error processing task: " + e.getMessage());
        }
    }
}
