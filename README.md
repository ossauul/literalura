# Litelalura
## Descripción
Litelalura es una aplicación desarrollada en Java utilizando Spring Boot y PostgreSQL, que permite gestionar un catálogo de libros. Los usuarios pueden interactuar con el sistema a través de una interfaz de línea de comandos (CLI), buscando libros en línea a través de la API de Gutendex, y administrando los libros y autores almacenados en una base de datos. Proyecto hecho para **challenge spring boot literalura**
## Objetivos del Proyecto
- **Catálogo de Libros**: Crear un catálogo donde se puedan buscar, agregar y listar libros.
- **Interacción con el Usuario**: Permitir la interacción textual con los usuarios a través de la consola.
- **Integración con API Externa**: Utilizar la API de Gutendex para buscar y agregar libros al catálogo.
- **Gestión de Autores**: Administrar la información de autores y filtrar por diferentes criterios.
## Características Principales
- **Buscar Libro en Internet**: Permite buscar un libro por su nombre utilizando la API de Gutendex. Si el libro se encuentra, se guarda en la base de datos junto con su autor.
- **Mostrar Todos los Libros Guardados**: Lista todos los libros almacenados en la base de datos.
- **Mostrar Libros por Idioma**: Filtra y muestra todos los libros guardados en la base de datos por un idioma específico (Español, Inglés, Francés, Finlandés).
- **Mostrar Todos los Autores**: Lista todos los autores almacenados en la base de datos.
- **Mostrar Autores Vivos en un Año Determinado**: Muestra todos los autores que estaban vivos en un año específico.
- **Mostrar Autores Vivos entre Dos Años Determinados**: Lista los autores que estaban vivos entre dos años especificados por el usuario.
- **Mostrar Top 10 Libros Más Descargados**: Muestra los 10 libros más descargados de la base de datos.
- **Búsqueda de Autor por Nombre**: Permite buscar un autor por su nombre.
- **Búsqueda de Autor por Año de Nacimiento**: Filtra y muestra los autores según su año de nacimiento.
- **Búsqueda de Autor por Año de Fallecimiento**: Filtra y muestra los autores según su año de fallecimiento.
## Estructura del Proyecto
El proyecto está compuesto por las siguientes clases principales:
- **Libro**: Representa la entidad de un libro con atributos como título, idioma, género y número de descargas.
- **Autor**: Representa la entidad de un autor con atributos como nombre, fecha de nacimiento y fecha de fallecimiento.
## Requisitos del Sistema
- Java: JDK 11 o superior.
- Spring Boot: 2.7.x o superior.
- PostgreSQL: 12 o superior.
- Maven: 3.6.x o superior.
## API Gutendex
El proyecto utiliza la API de Gutendex para buscar y agregar libros. La API proporciona acceso a una gran base de datos de libros de dominio público. https://github.com/garethbjohnson/gutendex?tab=readme-ov-file para mas informacion de su API.
