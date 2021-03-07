package com.udacity.vehicles.api;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.service.CarService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
class CarController {

    private final CarService carService;
    private final CarResourceAssembler assembler;

    CarController(CarService carService, CarResourceAssembler assembler) {
        this.carService = carService;
        this.assembler = assembler;
    }

    /**
     * Creates a list to store any vehicles.
     * Crea una lista para almacenar cualquier vehículo.
     * @return list of vehicles
     */
    @GetMapping
    Resources<Resource<Car>> list() {
        List<Resource<Car>> resources = carService.list().stream().map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }

    /**
     * Gets information of a specific car by ID.
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
    @GetMapping("/{id}")
    Resource<Car> get(@PathVariable Long id) {
        /**
         * TODO: Use the `findById` method from the Car Service to get car information.
         * Utilice el método `findById` del Servicio de Automóviles para obtener información sobre el automóvil.
         * TODO: Use the `assembler` on that car and return the resulting output.
         * Use el `ensamblador` en ese automóvil y devuelva la salida resultante
         *   Update the first line as part of the above implementing.
         *   Actualice la primera línea como parte de la implementación anterior.
         */
        carService.findById(id);
        return assembler.toResource(new Car());
    }

    /**
     * Posts information to create a new vehicle in the system.
     * Publica información para crear un nuevo vehículo en el sistema.
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     *          URISyntaxException si la solicitud contiene campos o sintaxis no válidos
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
        /**
         * TODO: Use the `save` method from the Car Service to save the input car.
         * Utilice el método `save` del Servicio de Automóviles para guardar el automóvil de entrada.
         * TODO: Use the `assembler` on that saved car and return as part of the response.
         * Use el "ensamblador" en ese auto guardado y devuélvalo como parte de la respuesta.
         *   Update the first line as part of the above implementing.
         *
         */
        Resource<Car> resource = assembler.toResource(new Car());
        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    /**
     * Updates the information of a vehicle in the system.
     * Actualiza la información de un vehículo en el sistema.
     * @param id The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     * Respuesta de que el vehículo se actualizó en el sistema.
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        /**
         * TODO: Set the id of the input car object to the `id` input.
         * Establezca la identificación del objeto de coche de entrada en la entrada `id`.
         * TODO: Save the car using the `save` method from the Car service
         * Guarde el automóvil utilizando el método `save` del servicio de automóviles
         * TODO: Use the `assembler` on that updated car and return as part of the response.
         * Use el `ensamblador` en ese auto actualizado y devuélvalo como parte de la respuesta.
         *   Update the first line as part of the above implementing.
         */
        Resource<Car> resource = assembler.toResource(new Car());
        return ResponseEntity.ok(resource);
    }

    /**
     * Removes a vehicle from the system.
     * Elimina un vehículo del sistema.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     * respuesta de que el vehículo relacionado ya no está en el sistema
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        /**
         * TODO: Use the Car Service to delete the requested vehicle.
         * Utilice el servicio de automóvil para eliminar el vehículo solicitado.
         */
        return ResponseEntity.noContent().build();
    }
}
