# FOROHUB APIRest
![FOROHUB]()

Bienvenido a la APIRest de FOROHUB. Diseñada para facilitar la interacción en comunidades de aprendizaje. Nuestra API proporciona todas las herramientas necesarias para gestionar tópicos, respuestas y usuarios de manera eficiente y segura. ¡Únete a la conversación y enriquece tu conocimiento hoy mismo!

![Java](https://img.shields.io/badge/Java-17-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-green?logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)
![Swagger](https://img.shields.io/badge/Swagger-2.0-orange?logo=swagger)
![JWT Token](https://img.shields.io/badge/JWT-Token-red?logo=jsonwebtoken)


## Tabla de Contenido
1. [Requisitos](#requisitos)
2. [Endpoints](#endpoints)
   - [Autores](#autores)
       - [Registrar Autor](#registrar-autor)
       - [Listar Autores](#listar-autores)
   - [Autenticación](#autenticación)
       - [Login](#login)
   - [Tópicos](#tópicos)
       - [Listar Tópicos](#listar-tópicos)
       - [Registrar Tópico](#registrar-tópico)
       - [Obtener Tópico por ID](#obtener-tópico-por-id)
       - [Actualizar Tópico](#actualizar-tópico)
       - [Eliminar Tópico](#eliminar-tópico)
   - [Respuestas](#respuestas)
       - [Agregar Respuesta a un Tópico](#agregar-respuesta-a-un-tópico)
       - [Editar Respuesta](#editar-respuesta)
       - [Eliminar Respuesta](#eliminar-respuesta)
3. [Roles y Permisos](#roles-y-permisos)
4. [Pasos para Utilizar FOROHUB en tu servidor local](#pasos-para-utilizar-forohub-en-tu-servidor-local)
    - [Clonar el Repositorio](#clonar-el-repositorio)
    - [Configurar la Base de Datos](#configurar-la-base-de-datos)
    - [Configurar IntelliJ IDEA](#configurar-intellij-idea)
    - [Ejecutar la Aplicación](#ejecutar-la-aplicación)
5. [Uso de la API](#uso-de-la-api)
6. [Notas Adicionales](#notas-adicionales)


## Requisitos

Asegúrate de tener instalado lo siguiente:

- **MySQL:** Necesitarás un servidor MySQL configurado con las siguientes credenciales:
    - **Host:** `${DB_HOST}`
    - **Nombre de la Base de Datos:** `${DB_NAME}`
    - **Usuario:** `${DB_MY_USER}`
    - **Contraseña:** `${DB_MY_PASS}`
    - **Clave JWT token:** `${API_SECRET}`

- **Java 17:** Debes tener Java Development Kit (JDK) 17 o superior instalado en tu sistema.

- **IntelliJ IDEA:** Recomendamos usar IntelliJ IDEA como entorno de desarrollo. Puedes descargarlo desde [jetbrains.com/idea](https://www.jetbrains.com/idea/).

## Endpoints

### Autores

#### Registrar Autor

- **Endpoint:** `/autor`
- **Método:** `POST`
- **Resumen:** Registre un autor en la base de datos.
- **Cuerpo de la solicitud:**
    - `DatosAltaAutor`: Datos para el registro del autor.
- **Respuesta:**
    - `200 OK`: Autor registrado exitosamente.

#### Listar Autores

- **Endpoint:** `/autores`
- **Método:** `GET`
- **Resumen:** Obtener una lista de autores.
- **Parámetros:**
    - `paginacion` (query, requerido): Parámetros de paginación.
- **Respuesta:**
    - `200 OK`: Lista de autores.

### Autenticación

#### Login

- **Endpoint:** `/login`
- **Método:** `POST`
- **Resumen:** Valide el login del usuario.
- **Cuerpo de la solicitud:**
    - `DatosAutenticacionAutor`: Credenciales del usuario.
- **Respuesta:**
    - `200 OK`: Usuario autenticado exitosamente.

### Tópicos

#### Listar Tópicos

- **Endpoint:** `/topicos`
- **Método:** `GET`
- **Resumen:** Obtenga una lista de tópicos.
- **Parámetros:**
    - `paginacion` (query, requerido): Parámetros de paginación.
- **Respuesta:**
    - `200 OK`: Lista de tópicos.

#### Registrar Tópico

- **Endpoint:** `/topicos`
- **Método:** `POST`
- **Resumen:** Registre un nuevo tópico en la base de datos.
- **Cuerpo de la solicitud:**
    - `DatosRegistroTopico`: Datos para el registro del tópico.
- **Respuesta:**
    - `200 OK`: Tópico registrado exitosamente.

#### Obtener Tópico por ID

- **Endpoint:** `/topicos/{id}`
- **Método:** `GET`
- **Resumen:** Obtenga los datos de un tópico específico por ID.
- **Parámetros:**
    - `id` (path, requerido): ID del tópico.
- **Respuesta:**
    - `200 OK`: Datos del tópico solicitado.

#### Actualizar Tópico

- **Endpoint:** `/topicos/{id}`
- **Método:** `PUT`
- **Resumen:** Actualiza un tópico específico por ID.
- **Parámetros:**
    - `id` (path, requerido): ID del tópico.
- **Cuerpo de la solicitud:**
    - `DatosActualizacionTopico`: Datos para la actualización del tópico.
- **Respuesta:**
    - `200 OK`: Tópico actualizado exitosamente.

#### Eliminar Tópico

- **Endpoint:** `/topicos/{id}`
- **Método:** `DELETE`
- **Resumen:** Elimine un tópico específico por ID y sus respuestas.
- **Parámetros:**
    - `id` (path, requerido): ID del tópico.
- **Cuerpo de la solicitud:**
    - `DatosActualizacionTopico`: Datos necesarios para la eliminación.
- **Respuesta:**
    - `200 OK`: Tópico eliminado exitosamente.

### Respuestas

#### Agregar Respuesta a un Tópico

- **Endpoint:** `/topicos/{topicoId}/respuestas`
- **Método:** `PUT`
- **Resumen:** Agrega respuestas a un tópico.
- **Parámetros:**
    - `topicoId` (path, requerido): ID del tópico.
- **Cuerpo de la solicitud:**
    - `DatosGeneracionDeRespuesta`: Datos para la creación de la respuesta.
- **Respuesta:**
    - `200 OK`: Respuesta agregada exitosamente.

#### Editar Respuesta

- **Endpoint:** `/topicos/{topicoId}/respuestas/{respuestaId}`
- **Método:** `PUT`
- **Resumen:** Edite una respuesta existente en la base de datos.
- **Parámetros:**
    - `topicoId` (path, requerido): ID del tópico.
    - `respuestaId` (path, requerido): ID de la respuesta.
- **Cuerpo de la solicitud:**
    - `DatosEdicionRespuesta`: Datos para la edición de la respuesta.
- **Respuesta:**
    - `200 OK`: Respuesta editada exitosamente.

#### Eliminar Respuesta

- **Endpoint:** `/topicos/{topicoId}/respuestas/{respuestaId}`
- **Método:** `DELETE`
- **Resumen:** Elimine una respuesta existente en la base de datos.
- **Parámetros:**
    - `topicoId` (path, requerido): ID del tópico.
    - `respuestaId` (path, requerido): ID de la respuesta.
- **Cuerpo de la solicitud:**
    - `DatosEliminacionRespuesta`: Datos necesarios para la eliminación.
- **Respuesta:**
    - `200 OK`: Respuesta eliminada exitosamente.

## Reglas de Acceso:

Esta API REST de ForoHub implementa un sistema de permisos basado en roles para asegurar el acceso adecuado a sus recursos. A continuación se detallan las reglas de permisos según el tipo de usuario:

### Acceso Público:
- Las rutas de documentación y Swagger están abiertas para todos los usuarios.
- Rutas permitidas: `/swagger-ui.html`, `/v3/api-docs/**`, `/swagger-ui/**`.

### Autenticación y Autorización:
- Se requiere autenticación para todas las demás rutas.
- Usuarios autenticados tienen acceso a recursos protegidos según su rol.

## Roles y Permisos:

- **ESTUDIANTE:** Puede crear nuevos tópicos y editar sus propias respuestas.
- **MODERADOR:** Similar a ESTUDIANTE, con permisos adicionales para eliminar tópicos.
- **INSTRUCTOR:** Puede crear y editar respuestas propias.
- **ADMINISTRADOR:** Tiene acceso completo a todos los recursos y operaciones de la API.

## Detalle de Permisos por Ruta:

- **POST /topicos:** ESTUDIANTE y MODERADOR pueden crear nuevos tópicos.
- **POST /respuestas:** INSTRUCTOR, ESTUDIANTE y MODERADOR pueden crear nuevas respuestas.
- **PUT /topicos/{id} y PUT /respuestas/{id}:** ESTUDIANTE y MODERADOR pueden editar sus propios tópicos y respuestas.
- **PUT /respuestas/{id}:** INSTRUCTOR, ESTUDIANTE y MODERADOR pueden editar respuestas.
- **DELETE /respuestas/{id}:** INSTRUCTOR, ESTUDIANTE, MODERADOR y ADMINISTRADOR pueden eliminar respuestas.
- **DELETE /topicos/{id}:** ESTUDIANTE, MODERADOR y ADMINISTRADOR pueden eliminar tópicos.
- **GET /topicos/** y **GET /respuestas/**:** Acceso público para leer tópicos y respuestas.
- **GET /autores:** Solo ADMINISTRADOR puede ver la lista completa de autores.

## Pasos para Utilizar FOROHUB en tu servidor local

### Clonar el Repositorio

Clona el repositorio ForoHub desde GitHub:

git clone https://github.com/tu-usuario/forohub.git


### Configurar la Base de Datos

- Crea una base de datos en tu servidor MySQL con el nombre `${DB_NAME}`.
- Asegúrate de que las credenciales `${DB_MY_USER}` y `${DB_MY_PASS}` tengan permisos adecuados sobre esta base de datos.

### Configurar IntelliJ IDEA

- Abre IntelliJ IDEA y selecciona `File > Open` para abrir el proyecto clonado.
- Configura las variables de entorno en IntelliJ IDEA:
    - Ve a `Run > Edit Configurations...`
    - En la ventana de configuración, asegúrate de definir las variables de entorno: `DB_HOST`, `DB_NAME`, `DB_MY_USER`, y `DB_MY_PASS` con los valores correspondientes.

### Ejecutar la Aplicación

- Dentro de IntelliJ IDEA, ejecuta la aplicación Spring Boot.
- IntelliJ IDEA iniciará el servidor en el puerto `8080`, según la configuración en `application.yml`.

### Uso de la API

Una vez que la aplicación esté en funcionamiento, puedes acceder a la API REST de ForoHub:

- La documentación de la API está disponible en:
    - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Notas Adicionales

- Asegúrate de mantener seguras tus credenciales y configuraciones sensibles.
- Para cualquier problema o consulta técnica, consulta la documentación de Spring Boot o contacta al equipo de desarrollo.

¡Listo! Ahora puedes comenzar a usar ForoHub en tu entorno local.
