package com.udacity.vehicles.client.prices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Implements a class to interface with the Pricing Client for price data.
 * Implementa una clase para interactuar con el Cliente de precios para
 * obtener datos de precios.
 */
@Component
public class PriceClient {

    private static final Logger log = LoggerFactory.getLogger(PriceClient.class);

    private final WebClient client;

    public PriceClient(WebClient pricing) {
        this.client = pricing;
    }

    // In a real-world application we'll want to add some resilience
    // to this method with retries/CB/failover capabilities
    // We may also want to cache the results so we don't need to
    // do a request every time
    /*
    * En una aplicación del mundo real, querremos agregar algo de
    * resistencia a este método con reintentos / CB / capacidades
    * de conmutación por error.
    * También es posible que deseemos almacenar en caché los resultados
    * para que no tengamos que hacer una solicitud cada vez
    * */

    /**
     * Gets a vehicle price from the pricing client, given vehicle ID.
     * Obtiene el precio del vehículo del cliente de fijación de precios,
     * dado el ID del vehículo.
     * @param vehicleId ID number of the vehicle for which to get the price
     * @return Currency and price of the requested vehicle,
     *   error message that the vehicle ID is invalid, or note that the
     *   service is down.
     *   Moneda y precio del vehículo solicitado,
     *   mensaje de error de que la identificación del vehículo no es válida
     *   o tenga en cuenta que el servicio no funciona.
     */
    public String getPrice(Long vehicleId) {
        try {
            Price price = client
                    .get()
                    .uri(uriBuilder -> uriBuilder.path("/prices")
                            //.path("services/price/")
                            .queryParam("vehicleId", vehicleId)
                            .build()
                    )
                    .retrieve().bodyToMono(Price.class).block();
            String  precio =  "MONEDA: " + price.getCurrency() + " PRECIO: " + price.getPrice();
            return String.format("%s %s", price.getCurrency(), price.getPrice());
        } catch (Exception e) {
            String error = "Unexpected error retrieving price for vehicle {}";
            log.error(error, vehicleId, e);
            return error+vehicleId;
        }
    }
}
