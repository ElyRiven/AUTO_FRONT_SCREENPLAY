package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import org.example.ui.PaginaRegistroUI;

public class LaVisibilidadDelMensajeError implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(PaginaRegistroUI.MENSAJE_ERROR).answeredBy(actor);
    }

    public static LaVisibilidadDelMensajeError enElFormulario() {
        return new LaVisibilidadDelMensajeError();
    }
}
