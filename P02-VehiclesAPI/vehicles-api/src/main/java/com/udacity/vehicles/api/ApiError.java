package com.udacity.vehicles.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * Declares methods to return errors and other messages from the API.
 * Declara métodos para devolver errores y otros mensajes de la API
 */
// @JsonInclude Indicar cuándo se puede serializar la propiedad anotada.
// Aplica las reglas especificadas se aplican a todas las propiedades de la clase.
// JsonInclude.Include.NON_NULL sirve para no incluir durante la serialización
// no se serializaran valores null
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiError {

    private final String message;
    private final List<String> errors;

    ApiError(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
