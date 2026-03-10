## Instrucciones para GitHub Copilot y agentes de IA

### Contexto del Proyecto

- Proyecto de Automatización de pruebas E2E en Java.
- El proyecto utiliza Gradle como sistema de construcción.
- Framework principal: Serenity BDD.
- Patrón de diseño: Screenplay Model.
- Los casos de prueba se reconocen y ejecutan usando archivos `.feature` escritos en lenguaje Gherkin.

### Buenas Prácticas de Desarrollo

- Mantener el código limpio, modular y reutilizable.
- Seguir el patrón Screenplay para separar tareas, elementos UI, ejecutores, escenarios, etc.
- Escribir métodos y clases con una única responsabilidad.
- Evitar la duplicidad de código; reutilizar componentes y utilidades.
- Documentar métodos y clases con comentarios claros y concisos.
- Escribir pruebas independientes y repetibles.
- Validar los resultados esperados usando aserciones claras.
- Mantener la estructura de carpetas organizada según convenciones de Serenity y Gradle.
- Realizar revisiones de código antes de integrar cambios a la rama principal.

### Importaciones

- **No** se deben realizar importaciones directamente dentro del cuerpo del código (métodos, clases, etc.).
- Todas las importaciones deben declararse al inicio de cada archivo Java, antes de la declaración de la clase.
- Usar las importaciones dentro del código únicamente después de haberlas declarado al inicio.

### Lineamientos de Nomenclatura

- Todas las variables y métodos deben nombrarse en **español**.
- Utilizar el formato **lowerCamelCase** para variables y métodos (ejemplo: `nombreUsuario`, `ingresarCredenciales`).
- Los nombres de clases deben usar PascalCase y estar en español (ejemplo: `PaginaLogin`).

### Uso de Gherkin

- Los archivos `.feature` deben estar escritos en lenguaje Gherkin para describir los escenarios de prueba.
- Utilizar pasos claros y descriptivos en español.
- Mantener la consistencia en la redacción de los escenarios.

### Otros Lineamientos

- Seguir las convenciones de Serenity BDD para la estructura de carpetas y archivos.
- Mantener dependencias actualizadas en el archivo `build.gradle`.
- Priorizar la legibilidad y mantenibilidad del código.
