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

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
/**
 * Implements a REST-based controller for the Vehicles API.
 */

@RestController
@ApiResponses(value={
        @ApiResponse(code = 400, message = "Bad Request response, server cannot or will not process the request due to something that is perceived to be a client error.")
        ,@ApiResponse(code =401, message = "Unauthorized client, the request has not been applied because it lacks valid authentication credentials for the target resource.")
        ,@ApiResponse(code=500, message = "Internal Server Error server error response, The server encountered an unexpected condition that prevented it from fulfilling the request.")
        ,@ApiResponse(code=403,message = "Server understood the request but refuses to authorize it.")
        ,@ApiResponse(code=404,message = "The requested resource could not be found but may be available in the future.")
        ,@ApiResponse(code=200,message = "Standard response for successful HTTP requests.")
        ,@ApiResponse(code=201,message = "The request has been fulfilled, resulting in the creation of a new resource.")
})
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
     * @return list of vehicles
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Resources< Resource<Car> > list() {
        List<Resource<Car>> resources = carService.list()
                                                .stream().map(assembler::toResource)
                                                        .collect(Collectors.toList());
        Link enlace = linkTo(methodOn(CarController.class).list()).withSelfRel();
        return new Resources<>(resources, enlace);
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
         * TODO: Use the `assembler` on that car and return the resulting output.
         */
        Car carro = carService.findById(id);
        return assembler.toResource(carro);
    }

    /**
     * Posts information to create a new vehicle in the system.
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
        /**
         * TODO: Use the `save` method from the Car Service to save the input car.
         * TODO: Use the `assembler` on that saved car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */
        Car autoGuardado = carService.save(car);
        Resource<Car> resource = assembler.toResource(autoGuardado);
        URI uri = new URI(resource.getId().expand().getHref());
        return ResponseEntity.created(uri).body(resource);
    }

    /**
     * Updates the information of a vehicle in the system.
     * @param id The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        /**
         * TODO: Set the id of the input car object to the `id` input.
         * TODO: Save the car using the `save` method from the Car service
         * TODO: Use the `assembler` on that updated car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */
        car.setId(id);
        Car autoActualizado = carService.save(car);
        Resource<Car> resource = assembler.toResource(autoActualizado);
        return ResponseEntity.ok(resource);
    }

    /**
     * Removes a vehicle from the system.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        /**
         * TODO: Use the Car Service to delete the requested vehicle.
         */
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
