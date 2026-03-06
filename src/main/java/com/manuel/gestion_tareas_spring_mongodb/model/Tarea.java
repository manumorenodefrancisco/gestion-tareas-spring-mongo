package com.manuel.gestion_tareas_spring_mongodb.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "tareas")//@Entity
public class Tarea {
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private Instant fechaCreacion;
    private Instant fechacompletada;

    public enum Estado { PENDIENTE, COMPLETADA }

    public Tarea() {}

    public Tarea(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = Estado.PENDIENTE;
        this.fechaCreacion = Instant.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Instant getfechacompletada() { return fechacompletada; }
    public void setfechacompletada(Instant fechacompletada) { this.fechacompletada = fechacompletada; }
}