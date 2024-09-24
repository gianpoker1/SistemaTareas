package com.sistemaTareas.Sistema_de_tareas.service;

import com.sistemaTareas.Sistema_de_tareas.model.Tarea;
import com.sistemaTareas.Sistema_de_tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService implements ITareaService{

    @Autowired
    TareaRepository tareaRepository;
    @Override
    public List<Tarea> listAllTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea findById(int idTarea) {
        Tarea tarea = tareaRepository.findById(idTarea).orElse(null);
        return tarea;
    }

    @Override
    public void saveTarea(Tarea tarea) {
        tareaRepository.save(tarea);

    }

    @Override
    public void deleteTarea(Tarea tarea) {
        tareaRepository.delete(tarea);
    }
}
