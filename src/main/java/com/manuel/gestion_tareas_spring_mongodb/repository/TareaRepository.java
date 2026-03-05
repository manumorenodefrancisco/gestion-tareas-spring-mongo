package com.manuel.gestion_tareas_spring_mongodb.repository;

import com.manuel.gestion_tareas_spring_mongodb.model.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends MongoRepository<Tarea, String> {

}
