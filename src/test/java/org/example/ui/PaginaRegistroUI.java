package org.example.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaRegistroUI {

    public static final Target CAMPO_NOMBRE_COMPLETO = Target.the("campo nombre completo")
            .located(By.id("displayName"));

    public static final Target CAMPO_CORREO = Target.the("campo correo electrónico")
            .located(By.id("email"));

    public static final Target CAMPO_CONTRASENA = Target.the("campo contraseña")
            .located(By.id("password"));

    public static final Target CAMPO_CONFIRMACION_CONTRASENA = Target.the("campo confirmación de contraseña")
            .located(By.id("confirmPassword"));

    public static final Target BOTON_CREAR_CUENTA = Target.the("botón Crear Cuenta")
            .locatedBy("//button[@type='submit' and text()='Crear Cuenta']");

    public static final Target MENSAJE_ERROR = Target.the("mensaje de error")
            .locatedBy("//div[text() = 'El correo electrónico ya está en uso']");
}
