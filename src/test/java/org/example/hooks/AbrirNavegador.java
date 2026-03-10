package org.example.hooks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AbrirNavegador implements Task {

    private final String url;

    public AbrirNavegador(String url) {
        this.url = url;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(url)
        );
    }

    public static AbrirNavegador enLaUrl(String url) {
        return instrumented(AbrirNavegador.class, url);
    }
}
