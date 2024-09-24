package com.sistemaTareas.Sistema_de_tareas.view;

import com.sistemaTareas.Sistema_de_tareas.SistemaDeTareasApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class SistemasTareasFx extends Application {

    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init(){
        this.applicationContext =
                new SpringApplicationBuilder(SistemaDeTareasApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //cargamos nuestro index
        FXMLLoader loader =
                new FXMLLoader(SistemaDeTareasApplication.class.getResource(
                        "/templates/index.fxml"
                ));
        //cargamos todos los objetos de spring.
        //Integramos las tegnologias de javafx con spring
        loader.setControllerFactory(applicationContext::getBean);
        //creamos la escena
        Scene escena = new Scene(loader.load());
        //establecemos la escena que queremos mostrar
        stage.setScene(escena);
        stage.show();
    }

    //sobreescribir metodo para detener la aplicacion
    @Override
    public void stop(){
        //cerrar cualquier conexion, por eje: a la DB
        applicationContext.close();
        //cerramos la aplicacion de JavaFx
        Platform.exit();
    }
}
