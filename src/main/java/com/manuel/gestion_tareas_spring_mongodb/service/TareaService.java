package com.manuel.gestion_tareas_spring_mongodb.service;

import com.manuel.gestion_tareas_spring_mongodb.model.Tarea;
import com.manuel.gestion_tareas_spring_mongodb.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> findById(String id) {
        return tareaRepository.findById(id);
    }

    public Tarea save(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea crearTareaConHilo(Tarea tarea) {
        System.out.println("Guardando tarea en base de datos...");
        Tarea nuevaTarea = tareaRepository.save(tarea);
        HiloRunnable hiloRunnable = new HiloRunnable(nuevaTarea, tareaRepository);
        Thread hilo = new Thread(hiloRunnable);
        hilo.start();
        return nuevaTarea;
    }

    public void deleteById(String id) {
        tareaRepository.deleteById(id);
    }

}
