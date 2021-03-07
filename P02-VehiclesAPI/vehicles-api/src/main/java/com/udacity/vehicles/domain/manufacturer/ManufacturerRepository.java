package com.udacity.vehicles.domain.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* Este repositorio proporciona un tipo de persistencia
* de datos mientras se ejecuta el servicio web,
* principalmente para almacenar información del
* fabricante como la inicializada en VehiclesApiApplication.
 * */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

}
