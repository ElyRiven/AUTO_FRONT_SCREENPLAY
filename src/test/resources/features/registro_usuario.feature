Feature: Registro de Usuario

  Scenario Outline: Registro exitoso con credenciales válidas
    Given que el usuario está en la página de registro
    When el usuario ingresa "<nombreCompleto>" en el campo nombre
    And el usuario ingresa "<correo>" en el campo correo electrónico
    And el usuario ingresa "<contrasena>" en el campo contraseña
    And el usuario ingresa "<confirmacionContrasena>" en el campo confirmación de contraseña
    And hace clic en el botón "Crear Cuenta"
    Then la cuenta se crea a través de Firebase Authentication
    And el usuario es redirigido al dashboard

    Examples:
      | nombreCompleto   | correo                      | contrasena    | confirmacionContrasena |
      | Ana García López | ana.garcia@correo.com       | Passw0rd!     | Passw0rd!              |
      | Carlos Martínez  | carlos.martinez@correo.com  | S3gur0#2026   | S3gur0#2026            |
      | Laura Rodríguez  | laura.rodriguez@correo.com  | T3stUs3r@     | T3stUs3r@              |

  Scenario: Registro falla cuando el correo ya está registrado
    Given que el usuario está en la página de registro
    When el usuario ingresa "Correo Usado" en el campo nombre
    And el usuario ingresa "dianelizbty@gmail.com" en el campo correo electrónico
    And el usuario ingresa "Passw0rd!" en el campo contraseña
    And el usuario ingresa "Passw0rd!" en el campo confirmación de contraseña
    And hace clic en el botón "Crear Cuenta"
    Then se muestra un mensaje de error en el formulario
    And el usuario permanece en la página de registro
