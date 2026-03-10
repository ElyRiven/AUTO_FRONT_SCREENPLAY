package org.example.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.example.ui.PaginaRegistroUI;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegistrarUsuario implements Task {

    private final String nombreCompleto;
    private final String correo;
    private final String contrasena;
    private final String confirmacionContrasena;

    public RegistrarUsuario(String nombreCompleto, String correo, String contrasena, String confirmacionContrasena) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.confirmacionContrasena = confirmacionContrasena;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(nombreCompleto).into(PaginaRegistroUI.CAMPO_NOMBRE_COMPLETO),
                Enter.theValue(correo).into(PaginaRegistroUI.CAMPO_CORREO),
                Enter.theValue(contrasena).into(PaginaRegistroUI.CAMPO_CONTRASENA),
                Enter.theValue(confirmacionContrasena).into(PaginaRegistroUI.CAMPO_CONFIRMACION_CONTRASENA),
                Click.on(PaginaRegistroUI.BOTON_CREAR_CUENTA)
        );
    }

    public static RegistrarUsuario conLosDatos(String nombreCompleto, String correo, String contrasena, String confirmacionContrasena) {
        return instrumented(RegistrarUsuario.class, nombreCompleto, correo, contrasena, confirmacionContrasena);
    }
}
