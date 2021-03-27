package com.udacity.pricing.service;

import com.udacity.pricing.domain.price.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Implements the pricing service to get prices for each vehicle..
 * Implementa el servicio de tarificación para obtener precios de cada vehículo.
 */
public class PricingService {

    /**
     * Holds {ID: Price} pairings (current implementation allows for 20 vehicles)
     * Tiene emparejamientos de {ID: Price} (la implementación actual permite 20 vehículos)
     * ********************************************************************************************
     * Map = Un objeto que asigna claves a valores. Un mapa no puede contener claves duplicadas;
     * cada clave se puede asignar a un valor como máximo.
     * LongStream = Una secuencia de elementos primitivos de
     * valor largo que soportan operaciones agregadas secuenciales y paralelas.
     * range = Devuelve un LongStream secuencial ordenado desde startInclusive (inclusive) hasta endExclusive (exclusivo) en un paso incremental de 1.
     * mapToObj = Devuelve una secuencia con valor de objeto que consta de los resultados de aplicar la función dada a los elementos de esta secuencia.
     * collect = Realiza una operación de reducción mutable en los elementos de esta secuencia mediante un recopilador.
     * Collectors.toMap = Devuelve un recopilador que acumula elementos en un mapa cuyas claves y valores son el resultado de aplicar las funciones de
     * mapeo proporcionadas a los elementos de entrada.
     */
    private static final Map<Long, Price> PRICES = LongStream
            .range(1, 20)
            .mapToObj(i -> new Price("USD", randomPrice(), i))
            .collect(Collectors.toMap(Price::getVehicleId, p -> p));
    // Price :: getVehicleId hace referencia a un metodo

    /**
     * If a valid vehicle ID, gets the price of the vehicle from the stored array.
     * Si es una identificación de vehículo válida, obtiene el precio del vehículo de la matriz almacenada.
     * @param vehicleId ID number of the vehicle the price is requested for.
     *                  Número de identificación del vehículo por el que se solicita el precio.
     * @return price of the requested vehicle
     *          precio de devolución del vehículo solicitado
     * @throws PriceException vehicleID was not found
     *                                   no se encontró
     */
    public static Price getPrice(Long vehicleId) throws PriceException {
        if (!PRICES.containsKey(vehicleId)) {
            throw new PriceException("Cannot find price for Vehicle " + vehicleId);
        }

        return PRICES.get(vehicleId);
    }

    /**
     * Gets a random price to fill in for a given vehicle ID.
     * Obtiene un precio aleatorio para completar para una identificación de vehículo determinada.
     * @return random price for a vehicle
     *          precio aleatorio de un vehiculo
     */
    private static BigDecimal randomPrice() {
        BigDecimal granDecimal =
                new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5))
                            .multiply(new BigDecimal(5000d))
                            .setScale(2, RoundingMode.HALF_UP);
        return granDecimal;
    }
}
