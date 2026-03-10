package org.example.pasos_deprecado;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import org.example.paginas_deprecado.PaginaRegistro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class PasosRegistroUsuario {

    private PaginaRegistro paginaRegistro;

    @Before
    public void inicializar() {
        WebDriver driver = Serenity.getWebdriverManager().getWebdriver();
        paginaRegistro = new PaginaRegistro(driver);
    }

    @Given("que el usuario está en la página de registro")
    public void usuarioEnPaginaRegistro() {
        paginaRegistro.open();
    }

    @When("el usuario ingresa {string} en el campo nombre")
    public void ingresarNombre(String nombreCompleto) {
        paginaRegistro.ingresarNombreCompleto(nombreCompleto);
    }

    @And("el usuario ingresa {string} en el campo correo electrónico")
    public void ingresarCorreo(String correo) {
        paginaRegistro.ingresarCorreo(correo);
    }

    @And("el usuario ingresa {string} en el campo contraseña")
    public void ingresarContrasena(String contrasena) {
        paginaRegistro.ingresarContrasena(contrasena);
    }

    @And("el usuario ingresa {string} en el campo confirmación de contraseña")
    public void ingresarConfirmacionContrasena(String confirmacionContrasena) {
        paginaRegistro.ingresarConfirmacionContrasena(confirmacionContrasena);
    }

    @And("hace clic en el botón \"Crear Cuenta\"")
    public void hacerClicEnCrearCuenta() {
        paginaRegistro.hacerClicEnCrearCuenta();
    }

    @Then("la cuenta se crea a través de Firebase Authentication")
    public void cuentaCreadaEnFirebase() {
        new WebDriverWait(paginaRegistro.getDriver(), Duration.ofSeconds(10))
                .until(driver -> !driver.getCurrentUrl().contains("/register"));
    }

    @And("el usuario es redirigido al dashboard")
    public void usuarioRedirigidoAlDashboard() {
        assertThat(paginaRegistro.obtenerUrlActual())
                .as("El usuario debería haber sido redirigido al dashboard tras el registro")
                .contains("/dashboard");
    }

    @Then("se muestra un mensaje de error en el formulario")
    public void seMuestraMensajeDeError() {
        new WebDriverWait(paginaRegistro.getDriver(), Duration.ofSeconds(10))
                .until(driver -> paginaRegistro.mensajeErrorEsVisible());
        assertThat(paginaRegistro.mensajeErrorEsVisible())
                .as("Debería mostrarse un mensaje de error en el formulario")
                .isTrue();
    }

    @And("el usuario permanece en la página de registro")
    public void usuarioPermanecePaginaRegistro() {
        assertThat(paginaRegistro.obtenerUrlActual())
                .as("El usuario debería permanecer en la página de registro")
                .contains("/register");
    }
}
