package org.example.paginas;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import org.example.utilidades.Constantes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@DefaultUrl(Constantes.URL_REGISTRO)
public class PaginaRegistro extends PageObject {

    public PaginaRegistro(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "displayName")
    private WebElement campoNombreCompleto;

    @FindBy(id = "email")
    private WebElement campoCorreo;

    @FindBy(id = "password")
    private WebElement campoContrasena;

    @FindBy(id = "confirmPassword")
    private WebElement campoConfirmacionContrasena;

    @FindBy(xpath = "//button[@type='submit' and text()='Crear Cuenta']")
    private WebElement botonCrearCuenta;

    @FindBy(xpath = "//div[text() = 'El correo electrónico ya está en uso']")
    private WebElement mensajeError;

    public void ingresarNombreCompleto(String nombreCompleto) {
        campoNombreCompleto.sendKeys(nombreCompleto);
    }

    public void ingresarCorreo(String correo) {
        campoCorreo.sendKeys(correo);
    }

    public void ingresarContrasena(String contrasena) {
        campoContrasena.sendKeys(contrasena);
    }

    public void ingresarConfirmacionContrasena(String confirmacionContrasena) {
        campoConfirmacionContrasena.sendKeys(confirmacionContrasena);
    }

    public void hacerClicEnCrearCuenta() {
        botonCrearCuenta.click();
    }

    public String obtenerTextoMensajeError() {
        return mensajeError.getText();
    }

    public boolean mensajeErrorEsVisible() {
        return mensajeError.isDisplayed();
    }

    public String obtenerUrlActual() {
        return getDriver().getCurrentUrl();
    }
}
