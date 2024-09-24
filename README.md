# Sistema de Manejo de Tareas

Este proyecto es una aplicación de escritorio desarrollada en Java utilizando Spring Boot y JavaFX para gestionar tareas en una base de datos MySQL. La aplicación ofrece una interfaz gráfica que permite listar, agregar, actualizar y eliminar tareas.

## Características

- **Listar Tareas**: Muestra una tabla con todas las tareas almacenadas en la base de datos.
- **Agregar Tarea**: Permite agregar una nueva tarea a la base de datos.
- **Actualizar Tarea**: Permite actualizar la información de una tarea existente.
- **Eliminar Tarea**: Permite eliminar una tarea de la base de datos.

## Tecnologías Utilizadas

- **Spring Boot**: Para la creación de la aplicación.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **MySQL**: Como sistema de gestión de base de datos.
- **JavaFX**: Para la interfaz gráfica de usuario.
- **Lombok**: Para simplificar el modelo de datos.

## Instrucciones de Instalación
### Requisitos Previos

1. **Java Development Kit (JDK)**: Asegúrate de tener instalado el JDK 11 o superior.
2. **Maven**: Asegúrate de tener instalado Maven.
3. **MySQL**: Asegúrate de tener instalado MySQL y una base de datos llamada `tasks_db`.

### Clonar el Repositorio

1. Clona el repositorio utilizando el siguiente comando:
   ```sh
   git clone https://github.com/gianpoker1/SistemaTareas
   cd SistemaTareas

### Configurar la Base de Datos MySQL
1. Asegúrate de tener MySQL instalado y en funcionamiento.
2. Crea una base de datos llamada tasks_db.
3. Configura las credenciales de la base de datos en el archivo
```
  src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/tasks_db
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Compilar la Aplicación
1. Abre una terminal y navega hasta el directorio del proyecto.
2. Ejecuta el siguiente comando para compilar la aplicación
```
mvn clean package
```
Este comando generará un archivo JAR en el directorio target.

### Ejecutar la Aplicación
Una vez compilada la aplicación, ejecuta el siguiente comando para iniciar la aplicación:
```
java -jar target/Sistema_de_tareas-1.0-SNAPSHOT.jar
```

#### Estructura del Proyecto

```markdown

El proyecto sigue la estructura típica de una aplicación Spring Boot. Aquí tienes una descripción de los paquetes y clases principales:

- **com.sistemaTareas.Sistema_de_tareas**:
  - `SistemaDeTareasApplication.java`: Clase principal de la aplicación Spring Boot.

- **com.sistemaTareas.Sistema_de_tareas.controller**:
  - `IndexController.java`: Controlador que maneja la lógica de la interfaz gráfica.

- **com.sistemaTareas.Sistema_de_tareas.model**:
  - `Tarea.java`: Clase de entidad que representa una tarea. Utiliza Lombok para simplificar el código.

- **com.sistemaTareas.Sistema_de_tareas.repository**:
  - `TareaRepository.java`: Interfaz que extiende `JpaRepository` para realizar operaciones CRUD en la base de datos.

- **com.sistemaTareas.Sistema_de_tareas.service**:
  - `ITareaService.java`: Interfaz que define los métodos del servicio de tareas.
  - `TareaService.java`: Implementación de `ITareaService` que utiliza `TareaRepository` para interactuar con la base de datos.

- **com.sistemaTareas.Sistema_de_tareas.view**:
  - `SistemasTareasFx.java`: Clase principal que inicia la aplicación JavaFX.
  - `index.fxml`: Archivo FXML que define la interfaz gráfica.

