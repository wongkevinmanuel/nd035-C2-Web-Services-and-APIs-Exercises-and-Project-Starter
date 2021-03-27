package com.udacity.vehicles.api;

import com.udacity.vehicles.domain.car.Car;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Maps the CarController to the Car class using HATEOAS
 * Mapea el CarController a la clase Car usando HATEOAS
 */
@Component
public class CarResourceAssembler implements ResourceAssembler<Car, Resource<Car>> {
    /**
     * HATEOAS
     *  El problema central que intenta abordar es la creación de enlaces y el ensamblaje de representación.
     */

    /*
    *
    * */
    @Override
    public Resource<Car> toResource(Car car) {
        //Crear enlaces apuntando a clases de controlador
        Link enlaceAGetId = linkTo(methodOn(CarController.class)
                                                    .get(car.getId())).withSelfRel();
        Link enlaceCars = linkTo(methodOn(CarController.class)
                                                    .list()).withRel("cars");
        return new Resource<>(car
                        , enlaceAGetId
                        , enlaceCars);

    }
}
