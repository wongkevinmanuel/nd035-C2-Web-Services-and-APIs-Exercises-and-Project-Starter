# Boogle Maps

Este es un Mock que simula un WebService de Maps donde, dada una latitud y longitud, devolverá una dirección aleatoria.

## Instructions

A través de shell se puede iniciar usando
```
$ mvn clean package
```

```
$ java -jar target/boogle-maps-0.0.1-SNAPSHOT.jar
```

El servicio está disponible por defecto en el puerto `9191`. Puede verificarlo en la línea de comando usando

```
$ curl http://localhost:9191/maps\?lat\=20.0\&lon\=30.0
``` 

También puede importarlo como un proyecto de Maven en su IDE preferido y ejecutar la clase `BoogleMapsApplication`.