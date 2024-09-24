package com.sistemaTareas.Sistema_de_tareas.controller;

import com.sistemaTareas.Sistema_de_tareas.model.Tarea;
import com.sistemaTareas.Sistema_de_tareas.service.TareaService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TareaService tareaService;

    //Elementos de la tabla FX
    @FXML
    private TableView<Tarea> tareaTabla;

    @FXML
    private TableColumn<Tarea, Integer> idTareaColumna;

    @FXML
    private TableColumn<Tarea, String> nombreTareaColumna;

    @FXML
    private TableColumn<Tarea, String> encargadoTablaColumna;

    @FXML
    private TableColumn<Tarea, String> estadoTablaColumna;

    private final ObservableList<Tarea> tareaLista = FXCollections.observableArrayList();

    @FXML
    private TextField nombreTareaTexto;

    @FXML
    private TextField encargadoTexto;

    @FXML
    private TextField estadoTexto;

    private Integer idTareaInterno;

    @FXML
    private Button agregarBoton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarTareas();

    }

    //para relacionar cada registro a cargar en la tabla
    //relacionamos que atributo de Tarea se cargara en cada columna
    private void configurarColumnas(){
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        nombreTareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        encargadoTablaColumna.setCellValueFactory(new PropertyValueFactory<>("encargado"));
        estadoTablaColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    private void listarTareas(){
        logger.info("Ejecutando listado de tareas");
        tareaLista.clear();
        //agregar los elementos de la base de datos
        tareaLista.addAll(tareaService.listAllTareas());
        //relacionamos la tabla con la lista
        tareaTabla.setItems(tareaLista);
    }

    public void agregarTarea(){
        if(nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error de validacion", "Debe proprocionar una tarea");
            nombreTareaTexto.requestFocus();
            return;
        }else{
            var tarea = new Tarea();
            recolectarDatosFormulario(tarea);
            tarea.setIdTarea(null);
            tareaService.saveTarea(tarea);
            mostrarMensaje("Informacion","Tarea Añadida");
            limpiarFormulario();
            listarTareas();
        }
    }

    public void deleteTarea(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea != null) {
            logger.info("Registro a eliminar: " + tarea.toString());
            tareaService.deleteTarea(tarea);
            mostrarMensaje("Informacion", "Registro Eliminado: " + tarea.getNombre());
            limpiarFormulario();
            listarTareas();
        }else{
            mostrarMensaje("Error", "No se ha seleccionado ningun registro");
        }
    }

    public void cargarTareaFormulario(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        //verificar que se selecciono un registro
        if(tarea != null){
            agregarBoton.setDisable(true);
            idTareaInterno = tarea.getIdTarea();
            nombreTareaTexto.setText(tarea.getNombre());
            encargadoTexto.setText(tarea.getEncargado());
            estadoTexto.setText(tarea.getEstatus());
        }
    }

    public void limpiarFormulario(){
        idTareaInterno = null;
        nombreTareaTexto.clear();
        encargadoTexto.clear();
        estadoTexto.clear();
        agregarBoton.setDisable(false);
    }

    private void recolectarDatosFormulario(Tarea tarea){
        if (idTareaInterno != null){tarea.setIdTarea(idTareaInterno);}
        tarea.setNombre(nombreTareaTexto.getText());
        tarea.setEncargado(encargadoTexto.getText());
        tarea.setEstatus(estadoTexto.getText());
    }

    public void actualizarTarea(){
        if(idTareaInterno == null){
            mostrarMensaje("Informacion", "Debe seleccionar un registro");
            return;
        }
        if(nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error de validacion", "Debe proporcionar un tarea");
            nombreTareaTexto.requestFocus();
            return;
        }
        var tarea = new Tarea();
        recolectarDatosFormulario(tarea);
        tareaService.saveTarea(tarea);
        mostrarMensaje("Informacion", "Tarea Actualizada");
        limpiarFormulario();
        listarTareas();
    }

    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
