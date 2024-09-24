package com.sistemaTareas.Sistema_de_tareas.service;

import com.sistemaTareas.Sistema_de_tareas.model.Tarea;

import java.util.List;

public interface ITareaService {

    public List<Tarea> listAllTareas();

    public Tarea findById(int idTarea);

    public void saveTarea(Tarea tarea);

    public void deleteTarea(Tarea tarea);
}
