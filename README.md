# Automatización del proyecto Budget Management App con Serenity POM

## Descripción

Este proyecto implementa la automatización de pruebas funcionales de la aplicación web [**Budget Management App**](https://github.com/majoymajo/Budget_Management_App), una aplicación de gestión de presupuestos personal con autenticación mediante Firebase. Las pruebas están diseñadas con el patrón **Page Object Model (POM)** sobre el framework **Serenity BDD**, combinando **Cucumber** para la definición de escenarios en lenguaje Gherkin y **Selenium WebDriver** para la interacción con el navegador. El objetivo es validar de forma automatizada y reproducible los flujos críticos del frontend relacionados con el registro de usuarios.

---

## Estructura del proyecto

```
AUTO_FRONT_POM_FACTORY/
├── build.gradle                        # Configuración de dependencias y plugins de Gradle
├── serenity.properties                 # Configuración de Serenity (driver, capturas, nombre del proyecto)
├── gradle.properties                   # Propiedades globales de Gradle
├── settings.gradle                     # Nombre del proyecto Gradle
└── src/
    └── test/
        ├── java/
        │   └── org/example/
        │       ├── ejecutores/         # Suite runners (JUnit Platform + Cucumber engine)
        │       │   └── EjecutorRegistroUsuario.java
        │       ├── paginas/            # Page Objects: encapsulan los elementos y acciones de cada página
        │       │   └── PaginaRegistro.java
        │       ├── pasos/              # Step definitions: conectan los pasos Gherkin con los Page Objects
        │       │   └── PasosRegistroUsuario.java
        │       └── utilidades/         # Constantes y utilidades compartidas
        │           └── Constantes.java
        └── resources/
            └── features/               # Escenarios de prueba en lenguaje Gherkin
                └── registro_usuario.feature
```

> Los reportes generados por Serenity se publican en `target/site/serenity/` tras cada ejecución.

---

## Ejecución

### Levantamiento del Proyecto Budget Management App

Para levantar el proyecto objetivo de las pruebas automatizadas, se requiere seguir los siguientes pasos:

- Clonar el repositorio del mismo, localizado en el siguiente [enlace de GitHub](https://github.com/majoymajo/Budget_Management_App) o ejecutar el siguiente comando:

```bash
git clone https://github.com/majoymajo/Budget_Management_App
```

- Configurar el archivo `.env` en la raíz del proyecto con el siguiente contenido:

```environment
# Database credentials

DB_USERNAME=appuser
DB_PASSWORD=666
MYSQL_ROOT_PASSWORD=123456

# RabbitMQ credentials

RABBITMQ_DEFAULT_USER=guest
RABBITMQ_DEFAULT_PASS=guest

# Obtener las credenciales en el enlace: console.firebase.google.com
# Firebase Configuration

VITE_FIREBASE_API_KEY=your_api_key_here
VITE_FIREBASE_AUTH_DOMAIN=your_project_id.firebaseapp.com
VITE_FIREBASE_PROJECT_ID=your_project_id
VITE_FIREBASE_STORAGE_BUCKET=your_project_id.appspot.com
VITE_FIREBASE_MESSAGING_SENDER_ID=your_sender_id
VITE_FIREBASE_APP_ID=your_app_id

# API Configuration

VITE_API_TRANSACTIONS_URL=http://localhost:8081/api
VITE_API_REPORTS_URL=http://localhost:8082/api
```

- Ejecutar el archivo docker-compose.yml con el siguiente comando:

```bash
docker compose up -d
```

- Comprobar la correcta ejecución del proyecto, accediendo a la URL `http://localhost:3000`

### Ejecución de Pruebas

Una vez que el proyecto esté levantado localmente, procedemos a la ejecución de las pruebas implementadas en el presente repositorio, ejecutando el siguiente comando:

```bash
gradle clean test
```

Este comando limpia compilaciones anteriores, ejecuta todos los escenarios definidos en `src/test/resources/features/` y genera automáticamente los reportes de Serenity al finalizar.

---

## Reportes

Serenity genera un reporte HTML completo con el resultado de cada escenario, capturas de pantalla por acción y trazabilidad hacia los requisitos.

Una vez finalizada la ejecución, se puede acceder a los reportes mediante el archivo:

```
target/site/serenity/index.html
```
