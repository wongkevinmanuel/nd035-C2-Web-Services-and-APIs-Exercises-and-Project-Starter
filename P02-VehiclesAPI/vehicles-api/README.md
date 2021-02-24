# Vehicles API

Una API REST para mantener los datos del vehículo y proporcionar una vista completa de los detalles del vehículo, incluido el precio y la dirección.

## Features

- REST API exploring the main HTTP verbs and features
- Hateoas
- Custom API Error handling using `ControllerAdvice`
- Swagger API docs
- HTTP WebClient
- MVC Test
- Automatic model mapping

## Instructions

#### Run the Code

Para ejecutar correctamente esta aplicación, primero debe iniciar la API de pedidos y la API de servicio.

```
$ mvn clean package
```

```
$ java -jar target/vehicles-api-0.0.1-SNAPSHOT.jar
```

Import it in your favorite IDE as a Maven Project.

## Operations

Swagger UI: http://localhost:8080/swagger-ui.html

### Create a Vehicle

`POST` `/cars`
```json
{
   "condition":"USED",
   "details":{
      "body":"sedan",
      "model":"Impala",
      "manufacturer":{
         "code":101,
         "name":"Chevrolet"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"3.6L V6",
      "mileage":32280,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"white"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242
   }
}
```

### Retrieve a Vehicle

`GET` `/cars/{id}`

Esta función recupera los datos del vehículo de la base de datos y accede al servicio de precios y a Boogle Maps para enriquecer la información del vehículo que se presentará.

### Update a Vehicle

`PUT` `/cars/{id}`

```json
{
   "condition":"USED",
   "details":{
      "body":"sedan",
      "model":"Impala",
      "manufacturer":{
         "code":101,
         "name":"Chevrolet"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"3.6L V6",
      "mileage":32280,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"white"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242
   }
}
```

### Delete a Vehicle

`DELETE` `/cars/{id}`
