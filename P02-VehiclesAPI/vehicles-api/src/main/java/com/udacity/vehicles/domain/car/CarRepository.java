package com.udacity.vehicles.domain.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

/*  Este repositorio proporciona un tipo de persistencia de
    datos mientras se ejecuta el servicio web,
    principalmente relacionado con la información del
    vehículo recibida en CarService.
    JpaRepository
    JpaRepository proporciona algunos métodos relacionados
    con JPA, como vaciar el contexto de persistencia
     y eliminar registros en un lote.

     Para reducir gran cantidad de codigo repetitivo.
     Al extender JpaRepository se crean automaticamente
     consultas sencillas entre bastidores.

     No es necesario implementar la interfaz CarRepository
     xq Spring crea una implementacion sobre la marcha
     cada vez q se ejecuta la aplicacion.
 * */
}
