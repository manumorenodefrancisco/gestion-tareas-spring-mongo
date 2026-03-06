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
        System.out.println("Iniciando hilo para procesar tarea: " + tarea.getTitulo());
        try {
            System.out.println("Esperando 10s para simular tarea...");
            Thread.sleep(10000);
            tarea.setEstado(Tarea.Estado.COMPLETADA);
            tarea.setfechacompletada(java.time.Instant.now());
            tareaRepository.save(tarea);
            System.out.println("Tarea completada exitosamente: " + tarea.getTitulo());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error procesando tarea: " + e.getMessage());
        }
    }
}
