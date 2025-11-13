# Marah

## Guía Rápida: Usando la Plantilla

El sistema consta de dos partes principales:

1.  Backend (Scala): Servidor API HTTP que entrega el estado del juego y maneja las acciones.
2.  Frontend (React): Visualizador interactivo con mapa basado en cuadrícula, visualización de entidades y menús de acciones.
    El frontend se comunica con el backend a través de dos endpoints principales:

-   `GET /state` - Obtiene el estado actual del juego
-   `POST /actions` - Ejecuta una acción por su ID

### Qué Necesitan Implementar los Estudiantes

#### Clase `GameController`

Este es el controlador principal de la lógica del juego. Actualmente está vacío y necesita contener el estado y la lógica de tu juego.

#### Extender la clase abstracta correspondiente para el juego

-   `GridGame`
    -   `gridSize: (Int, Int)`—Las dimensiones de tu cuadrícula (ancho, alto)
    -   `cells: List[Cell]`—Todas las celdas a renderizar en la cuadrícula
    -   `score: List[ScoreView]`—Puntuaciones/estadísticas a mostrar en el menú inferior
    -   `topBarMessage: Option[String]`—Instrucciones o feedback para el jugador, mostrado en la barra superior
    -   `menuActions: List[Action]`—Acciones globales disponibles desde el menú inferior
-   `StackGame`
    -   `direction: "vertical" | "horizontal"`—La dirección del stack (pila)
    -   `stack: List[StackCell]`—Todas las celdas a renderizar en la cuadrícula
    -   `score: List[ScoreView]`—Puntuaciones/estadísticas a mostrar en el menú inferior
    -   `topBarMessage: Option[String]`—Instrucciones o feedback para el jugador, mostrado en la barra superior
    -   `menuActions: List[Action]`—Acciones globales disponibles desde el menú inferior

#### Crear Acciones

Las acciones representan las interacciones del jugador. Cada acción debe extender el trait `Action` e implementar:

-   `name: String`—Nombre a mostrar en el botón de la acción
-   `doAction(c: GameController): ActionResult`—La lógica a ejecutar cuando se hace clic

Devuelve `Success(message)` o `Failure(message)` para proporcionar feedback al jugador.

#### Construir el Estado de Tu Juego

Usa las clases de componentes proporcionadas para estructurar tu juego:

-   **Cell**—Representa una celda en la cuadrícula con:
    -   `label: Option[String]`—Texto mostrado en la celda
    -   `x: Int`, `y: Int`—Coordenadas de la cuadrícula (indexadas desde 0)
    -   `entities: List[CellEntity]`—Objetos del juego en esta celda
    -   `actions: List[Action]`—Acciones disponibles al hacer clic en esta celda
    -   `img: Option[String]`—Nombre del archivo de imagen de fondo (opcional)

-   **CellEntity**—Representa objetos del juego (personajes, ítems, etc.) con:
    -   `name: String`—Nombre de la entidad
    -   `attributes: List[CellEntityAttribute]`—Propiedades mostradas en el tooltip al pasar el cursor
    -   `actions: List[Action]`—Acciones específicas de esta entidad
    -   `img: Option[String]`—Nombre del archivo del sprite de la entidad (opcional) CellEntity.scala:5-20

-   **CellEntityAttribute**—Pares clave-valor para las propiedades de la entidad (ej., HP, Ataque, Nivel)
    -   `name: String`—Nombre del atributo
    -   `value: String`—Valor del atributo

-   **ScoreView**—Información de estado mostrada en el menú inferior
    -   `name: String`—Nombre a mostrar
    -   `value: String`—Valor a mostrar

Para el caso de `StackGame`:

- **StackCell**—Representa una celda dentro de un `StackGame`. Es idéntica a `Cell` pero omitiendo los atributos `x` e `y`.
    -   `label: Option[String]`—Texto mostrado en la celda
    -   `x: Int`, `y: Int`—Coordenadas de la cuadrícula (indexadas desde 0)
    -   `entities: List[CellEntity]`—Objetos del juego en esta celda
    -   `actions: List[Action]`—Acciones disponibles al hacer clic en esta celda
    -   `img: Option[String]`—Nombre del archivo de imagen de fondo (opcional)