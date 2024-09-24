package com.sistemaTareas.Sistema_de_tareas;

import com.sistemaTareas.Sistema_de_tareas.view.SistemasTareasFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaDeTareasApplication {

	public static void main(String[] args) {

		//SpringApplication.run(SistemaDeTareasApplication.class, args);
		Application.launch(SistemasTareasFx.class, args);
	}

}
