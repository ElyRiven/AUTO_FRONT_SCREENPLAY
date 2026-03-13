package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;

import org.example.hooks.AbrirNavegador;
import org.example.questions.LaUrlActual;
import org.example.questions.LaVisibilidadDelMensajeError;
import org.example.tasks.RegistrarUsuario;
import org.example.utils.Constantes;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.example.ui.PaginaRegistroUI.BOTON_CREANDO_CUENTA;
import static org.example.ui.PaginaRegistroUI.MENSAJE_ERROR;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class StepDefinitionsRegistroUsuario {

    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private String confirmacionContrasena;

    @Given("que el usuario está en la página de registro")
    public void queElUsuarioEstaEnLaPaginaDeRegistro() {
        OnStage.theActorCalled(Constantes.ACTOR).wasAbleTo(
                AbrirNavegador.enLaUrl(Constantes.URL_REGISTRO)
        );
    }

    @When("el usuario ingresa {string} en el campo nombre")
    public void elUsuarioIngresaEnElCampoNombre(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @And("el usuario ingresa {string} en el campo correo electrónico")
    public void elUsuarioIngresaEnElCampoCorreoElectronico(String correo) {
        this.correo = correo;
    }

    @And("el usuario ingresa {string} en el campo contraseña")
    public void elUsuarioIngresaEnElCampoContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @And("el usuario ingresa {string} en el campo confirmación de contraseña")
    public void elUsuarioIngresaEnElCampoConfirmacionDeContrasena(String confirmacionContrasena) {
        this.confirmacionContrasena = confirmacionContrasena;
    }

    @And("hace clic en el botón \"Crear Cuenta\"")
    public void haceClicEnElBotonCrearCuenta() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarUsuario.conLosDatos(nombreCompleto, correo, contrasena, confirmacionContrasena)
        );
    }

    @Then("la cuenta se crea a través de Firebase Authentication")
    public void laCuentaSeCreaATravesDeFirebaseAuthentication() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(BOTON_CREANDO_CUENTA, isVisible()).forNoMoreThan(Duration.ofSeconds(5))
        );
    }

    @And("el usuario es redirigido al dashboard")
    public void elUsuarioEsRedirigidoAlDashboard() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ExpectedConditions.urlContains("/dashboard")).forNoMoreThan(Duration.ofSeconds(10))
        );
    }

    @Then("se muestra un mensaje de error en el formulario")
    public void seMuestraUnMensajeDeErrorEnElFormulario() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(MENSAJE_ERROR, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Ensure.that(LaVisibilidadDelMensajeError.enElFormulario()).isTrue()
        );
    }

    @And("el usuario permanece en la página de registro")
    public void elUsuarioPermaneceLaPaginaDeRegistro() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(LaUrlActual.delNavegador()).contains("/register")
        );
    }
}
