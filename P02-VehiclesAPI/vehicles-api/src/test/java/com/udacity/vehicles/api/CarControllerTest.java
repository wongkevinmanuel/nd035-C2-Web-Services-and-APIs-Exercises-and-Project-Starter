package com.udacity.vehicles.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.service.CarService;
import java.net.URI;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Implements testing of the CarController class.
 * Implementa las pruebas de la clase CarController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarControllerTest {

    //Forma poderosa de probar rapidamente
    //Controladores MVC sin necesidad de
    //iniciar un servidor HTTP completo.
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> json;

    //Crea un Mock de carService
    //q es dependencia del controlador CarController
    @MockBean
    private CarService carService;

    @MockBean
    private PriceClient priceClient;

    @MockBean
    private MapsClient mapsClient;

    /**
     * Creates pre-requisites for testing, such as an example car.
     * Crea requisitos previos para las pruebas, como un coche de ejemplo.
     */
    @Before
    public void setup() {
        Car car = getCar();
        car.setId(1L);
        given(carService.save(any())).willReturn(car);
        given(carService.findById(any())).willReturn(car);
        given(carService.list()).willReturn(Collections.singletonList(car));
    }

    /**
     * Tests for successful creation of new car in the system
     * @throws Exception when car creation fails in the system
     *
     * Pruebas para la creación exitosa de un nuevo automóvil
     * en el sistema @throws Excepción cuando falla la creación
     * de un automóvil en el sistema
     */
    @Test
    public void createCar() throws Exception {
        Car car = getCar();
        //simula una solocitud Http
        mvc.perform(
                post(new URI("/cars"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
        //andExpect son expectativas en las respuestas HTTP
        //recibidas de la clase de controlador.

        //Se usa para verificar la cantidad de veces q se ha llamado
        //a un metodo simulado
        //verify(carService, times(1)).retrieveLocation();
    }

    /**
     * Tests if the read operation appropriately returns a list of vehicles.
     * @throws Exception if the read operation of the vehicle list fails
     *
     * Prueba si la operación de lectura devuelve correctamente una lista
     * de vehículos. @throws Excepción si falla la operación de lectura
     * de la lista de vehículos
     */
    @Test
    public void listCars() throws Exception {
        /**
         * TODO: Add a test to check that the `get` method works by calling
         *   the whole list of vehicles. This should utilize the car from `getCar()`
         *   below (the vehicle will be the first in the list).
         *
         *   TODO: agregue una prueba para verificar que el método `get` funcione
         *   llamando a la lista completa de vehículos. Esto debería utilizar el coche
         *   de `getCar ()` a continuación (el vehículo será el primero en la lista).
         */
        Car auto = getCar();
        URI uri= new URI("/cars");
        mvc.perform(get(uri)
                        .content(json.write(auto).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    /**
     * Tests the read operation for a single car by ID.
     * @throws Exception if the read operation for a single car fails
     *
     * Prueba la operación de lectura para un solo automóvil por ID.
     * @throws Excepción si falla la operación de lectura para un solo automóvil
     */
    @Test
    public void findCar() throws Exception {
        /**
         * TODO: Add a test to check that the `get` method works by calling
         *   a vehicle by ID. This should utilize the car from `getCar()` below.
         *
         *   TODO: Agregue una prueba para verificar que el método `get` funcione
         *   llamando a un vehículo por ID. Esto debería utilizar el coche de
         *   `getCar ()` a continuación.
         */
        Car auto = getCar();
        URI uri= new URI("/cars/1");
        mvc.perform(get(uri)
                        .content(json.write(auto).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    /**
     * Tests the deletion of a single car by ID.
     * @throws Exception if the delete operation of a vehicle fails
     *
     * Prueba la eliminación de un solo automóvil por ID.
     * @throws Excepción si falla la operación de eliminación de un vehículo
     */
    @Test
    public void deleteCar() throws Exception {
        /**
         * TODO: Add a test to check whether a vehicle is appropriately deleted
         *   when the `delete` method is called from the Car Controller. This
         *   should utilize the car from `getCar()` below.
         *
         *   TODO: agregue una prueba para verificar si un vehículo se elimina
         *   correctamente cuando se llama al método `delete` desde el controlador
         *   del automóvil.
         * Esto debería utilizar el coche de `getCar ()` a continuación.
         */
        URI uriDelete = new URI("/cars/1");
        URI uriGet = new URI("/cars");
        mvc.perform(delete(uriDelete)).andExpect(status().isNoContent());
        mvc.perform(get(uriGet)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }


    /**
     * Creates an example Car object for use in testing.
     * @return an example Car object
     *
     * Crea un objeto Car de ejemplo para usarlo en pruebas.
     *       * @return un objeto Car de ejemplo
     */
    private Car getCar() {
        Car car = new Car();
        car.setLocation(new Location(40.730610, -73.935242));
        Details details = new Details();
        Manufacturer manufacturer = new Manufacturer(101, "Chevrolet");
        details.setManufacturer(manufacturer);
        details.setModel("Impala");
        details.setMileage(32280);
        details.setExternalColor("white");
        details.setBody("sedan");
        details.setEngine("3.6L V6");
        details.setFuelType("Gasoline");
        details.setModelYear(2018);
        details.setProductionYear(2018);
        details.setNumberOfDoors(4);
        car.setDetails(details);
        car.setCondition(Condition.USED);
        return car;
    }
}