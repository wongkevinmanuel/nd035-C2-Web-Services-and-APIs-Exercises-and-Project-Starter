package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.Price;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 *
 * Implementa el servicio de automóviles para crear,
 * leer, actualizar o eliminar información
 * sobre vehículos, así como recopilar datos
 * de ubicación y precios relacionados cuando lo desee.
 *
 */
@Service
public class CarService {

    private final CarRepository repository;
    private MapsClient clienteMapa;
    private PriceClient clientePrecio;

    private Car car;

    public CarService(CarRepository repository, MapsClient mC, PriceClient pC) {
        /**
         * TODO: Add the Maps and Pricing Web Clients you create
         *   in `VehiclesApiApplication` as arguments and set them here.
         *   agregue los mapas y los clientes web de precios que cree
         *   en `VehiclesApiApplication` como argumentos y configúrelos aquí.
         */
        this.repository = repository;
        this.clienteMapa = mC;
        this.clientePrecio = pC;
    }

    /**
     * Gathers a list of all vehicles
     * Reúne una lista de todos los vehículos.
     * @return a list of all vehicles in the CarRepository
     *          una lista de todos los vehículos en CarRepository
     */
    public List<Car> list() {

        return repository.findAll();
    }


    private void setLocalizacion(Location localizacionLatLong){
        Location localizacion = new Location();
        try{
            localizacion = clienteMapa.getAddress(localizacionLatLong);
        }catch (Exception ex){   }
        car.setLocation(localizacion);
    }

    private void setPrecio(Long id){
        String precio= "";
        try{
            precio = clientePrecio.getPrice(id);
        }catch (Exception ex){
            precio = "Error unexpected.";
        }

        if(precio.isEmpty())
            precio = "Error unexpected.";

        car.setPrice(precio);
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * Obtiene la información del automóvil por ID (o lanza una excepción si no existe)
     * @param id the ID number of the car to gather information on
     *          el número de identificación del automóvil para recopilar información
     * @return the requested car's information, including location and price
     *           la información del automóvil solicitado, incluida la ubicación y el precio
     */
    public Car findById(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         * Busque el coche por ID en el "repositorio" si existe.
         *   If it does not exist, throw a CarNotFoundException
         *   Si no existe, lanza una CarNotFoundException
         *   Remove the below code as part of your implementation.
         *  Elimine el siguiente código como parte de su implementación.
         */
        car = new Car();
        Optional optionalCarro = repository.findById(id);

        if(optionalCarro.isEmpty())
            throw new CarNotFoundException();

        car = (Car) optionalCarro.get();
        setPrecio(id);
        setLocalizacion(car.getLocation());

        /**
         * TODO: Use the Pricing Web client you create in `VehiclesApiApplication`
         *   to get the price based on the `id` input'
         *   Usa el cliente web de precios que creaste en `VehiclesApiApplication`
         *   para obtener el precio basado en la entrada de` id`
         * TODO: Set the price of the car (Establecer el precio del automóvil)
         * Note: The car class file uses @transient, meaning you will need to call
         *   the pricing service each time to get the price.
         *  El archivo de clase de automóvil usa @transient,
         *  lo que significa que deberá llamar al servicio de precios
         *  cada vez para obtener el precio.
         */


        /**
         * TODO: Use the Maps Web client you create in `VehiclesApiApplication`
         *   to get the address for the vehicle. You should access the location
         *   from the car object and feed it to the Maps service.
         * TODO: Set the location of the vehicle, including the address information
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         * TODO: Utilice el cliente de Maps Web que creó en `VehiclesApiApplication`
         * para obtener la dirección del vehículo. Debe acceder a la ubicación
         * desde el objeto del automóvil y enviarlo al servicio de Mapas.
         * TODO: establece la ubicación del vehículo, incluida la información de la dirección
         * Nota: El archivo de clase de ubicación también usa @transient para la dirección,
         * lo que significa que se debe llamar al servicio de Mapas cada vez para la dirección.
         */
        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * Crea o actualiza un vehículo, en función de la existencia previa del coche.
     * @param car A car object, which can be either new or existing
     *            Un objeto de automóvil, que puede ser nuevo o existente.
     * @return the new/updated car is stored in the repository
     *           el coche nuevo / actualizado se almacena en el repositorio
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         * Encuentra el coche por ID en el "repositorio" si existe.
         *  If it does not exist, throw a CarNotFoundException
         *  Si no existe, lanza una CarNotFoundException
         */
        Car carro = new Car();
        Optional optionalCarro = repository.findById(id);

        if (optionalCarro.isEmpty())
            throw new CarNotFoundException();

        carro = (Car) optionalCarro.get();
        repository.delete(car);
        /**
         * TODO: Delete the car from the repository.
         *      Elimina el carro del repositorui
         */


    }
}
