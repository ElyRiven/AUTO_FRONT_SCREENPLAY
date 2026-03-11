# Automatización del proyecto Budget Management App con Serenity Screenplay

## Descripción

Este proyecto implementa la automatización de pruebas funcionales de la aplicación web [**Budget Management App**](https://github.com/majoymajo/Budget_Management_App), una aplicación de gestión de presupuestos personal con autenticación mediante Firebase. Las pruebas están diseñadas con el patrón **Screenplay** sobre el framework **Serenity BDD**, combinando **Cucumber** para la definición de escenarios en lenguaje Gherkin y **Selenium WebDriver** para la interacción con el navegador. El objetivo es validar de forma automatizada y reproducible los flujos críticos del frontend relacionados con el registro de usuarios.

---

## Stack Tecnológico

| Herramienta | Versión mínima requerida | Notas                                                                                                                                                                                                                                                    |
| ----------- | ------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Java        | 17                       | El proyecto está compilado con `sourceCompatibility = 17`. Se requiere **Java 17 o superior** para su ejecución.                                                                                                                                         |
| Gradle      | 7.6                      | El proyecto fue generado y probado con **Gradle 9.4.0**. El plugin de Serenity BDD 4.x requiere como mínimo Gradle 7.6; versiones anteriores no son compatibles. Se recomienda usar **Gradle 9.4.0 o superior** para garantizar compatibilidad completa. |

---

## Estructura del proyecto

```
AUTO_FRONT_SCREENPLAY/
├── build.gradle                        # Configuración de dependencias y plugins de Gradle
├── serenity.properties                 # Configuración de Serenity (driver, capturas, nombre del proyecto)
├── gradle.properties                   # Propiedades globales de Gradle
├── settings.gradle                     # Nombre del proyecto Gradle
└── src/
    └── test/
        ├── java/
        │   └── org/example/
        │       ├── hooks/              # Configuración de contexto para los escenarios
        │       │   └── AbrirNavegador.java
        │       ├── questions/          # Preguntas que el actor puede hacer para obtener información del sistema
        │       │   ├── LaUrlActual.java
        │       │   └── LaVisibilidadDelMensajeError.java
        │       ├── runners/            # Suite runners (JUnit Platform + Cucumber engine)
        │       │   └── TestRunnerRegistroUsuario.java
        │       ├── stepdefinitions/    # Step definitions: conectan los pasos Gherkin con las tareas del actor
        │       │   ├── Hooks/          # Hooks de inicialización de escenarios
        │       │   │   └── Hook.java
        │       │   └── StepDefinitionsRegistroUsuario.java
        │       ├── tasks/              # Tareas que el actor puede realizar (interacciones de alto nivel)
        │       │   └── RegistrarUsuario.java
        │       ├── ui/                 # Elementos de la interfaz de usuario (Targets)
        │       │   └── PaginaRegistroUI.java
        │       └── utils/         # Constantes y utilidades compartidas
        │           └── Constantes.java
        └── resources/
            └── features/               # Escenarios de prueba en lenguaje Gherkin
                └── registro_usuario.feature
```

> Los reportes generados por Serenity se publican en `target/site/serenity/` tras cada ejecución.

---

## Ejecución

### Configuración de Firebase

Para que las pruebas automatizadas funcionen correctamente, se requiere contar con un proyecto activo en **Firebase Studio**, accesible desde [https://console.firebase.google.com](https://console.firebase.google.com).

1. **Acceder a Firebase Console**: Ingresar a [https://console.firebase.google.com](https://console.firebase.google.com) y crear o seleccionar un proyecto existente.

2. **Habilitar métodos de autenticación (Sign-in methods)**: En el panel del proyecto, navegar a **Authentication > Sign-in method** y habilitar los siguientes proveedores:
   - **Email/Password**
   - **Google**

3. **Crear usuario de prueba**: En la sección **Authentication > Users**, crear manualmente un usuario con el método **Email/Password** con los siguientes datos:

   | Campo              | Valor                     |
   | ------------------ | ------------------------- |
   | Correo electrónico | `correo.usado@correo.com` |
   | Contraseña         | `Passw0rd!`               |

   Este usuario es necesario para la ejecución correcta del escenario que valida el error al intentar registrar un correo ya existente en el sistema.

> **Nota:** Al ejecutar las pruebas automatizadas, se generan nuevos registros de usuarios en el proyecto de Firebase. Si se desea volver a ejecutar las pruebas sin que estas fallen, se deben eliminar manualmente dichos registros desde la sección **Authentication > Users** del proyecto de Firebase antes de cada ejecución. Los correos generados por la automatización son:
>
> | Nombre             | Correo generado              |
> | ------------------ | ---------------------------- |
> | María Elena Garcés | `maria.garces@correo.com`    |
> | William Estrada    | `william.estrada@correo.com` |
> | Emma Ortega        | `emma.ortega@correo.com`     |

---

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

Para obtener las credenciales de Firebase, ingresar al proyecto en [Firebase Console](https://console.firebase.google.com), luego navegar a **Configuración del proyecto** (ícono de engranaje junto a "Project Overview") > pestaña **General** > sección **"Tus apps"**. Si no hay ninguna app web registrada, hacer clic en **"Agregar app"** y seleccionar el ícono web (`</>`). Firebase mostrará el objeto `firebaseConfig` con todos los valores requeridos (`apiKey`, `authDomain`, `projectId`, `storageBucket`, `messagingSenderId`, `appId`), los cuales deben copiarse en las variables correspondientes del archivo `.env`.

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

O

```bash
./gradlew clean test
```

Este comando limpia compilaciones anteriores, ejecuta todos los escenarios definidos en `src/test/resources/features/` y genera automáticamente los reportes de Serenity al finalizar.

---

## Reportes

Serenity genera un reporte HTML completo con el resultado de cada escenario, capturas de pantalla por acción y trazabilidad hacia los requisitos.

Una vez finalizada la ejecución, se puede acceder a los reportes mediante el archivo:

```
target/site/serenity/index.html
```
