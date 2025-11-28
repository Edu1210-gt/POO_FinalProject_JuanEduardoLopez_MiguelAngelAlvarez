==================================================================
           🎬 MOVIE RENTAL SYSTEM — FINAL OOP PROJECT
==================================================================

                Juan Eduardo Lopez Sanchez - 37696
                Migue Angel Alvarez Ramirez - 53645


                            OBJETIVO
Diseñar e implementar un sistema que gestione el registro de películas 
y clientes, así como el proceso de alquiler y devolución, aplicando los 
principios de la programación orientada a objetos y utilizando 
persistencia mediante archivos serializados.


                            ALCANCE
El sistema permite:

1. Añadir películas
2. Buscar películas por ID
3. Buscar películas por título
4. Buscar películas por género
5. Mostrar películas disponibles
6. Actualizar películas
7. Eliminar películas
8. Mostrar todas las películas
9. Añadir clientes
10. Encontrar cliente por ID
11. Buscar cliente por nombre
12. Actualizar cliente
13. Eliminar cliente
14. Mostrar todos los clientes
15. Rentar una película
16. Devolver una película
17. Encontrar una renta por ID
18. Buscar rentas por cliente
19. Mostrar todas las rentas
20. Mostrar la película menos rentada
21. Mostrar todas las películas con su cantidad de rentas
22. Mostrar la película más rentada


                        FUNCIONAMIENTO
Al iniciar el programa, el sistema carga la información previamente 
guardada desde archivos serializados utilizando ObjectInputStream.

Luego, se muestra un menú principal donde el usuario puede seleccionar 
diferentes opciones para gestionar películas, clientes y alquileres.

El usuario interactúa mediante una interfaz de consola, ingresando el 
número correspondiente a cada operación. Se emplean bloques try-catch 
para manejar entradas no válidas.

Cuando se registra una película, cliente o renta, el sistema almacena 
los datos en una lista interna (ArrayList).

Cuando se realiza un alquiler, el sistema verifica que la película esté 
disponible, crea un objeto Rental y actualiza su estado.  
Al devolver una película, esta vuelve a marcarse como disponible.

Cada vez que se realiza un cambio (registro, alquiler o devolución), el 
sistema guarda la nueva información en archivos .dat usando 
ObjectOutputStream, garantizando que los datos se mantengan disponibles 
para futuras ejecuciones.

A través de la clase ReturnMovie, el sistema calcula si la renta está en 
mora o si se entrega a tiempo. Si hay mora, se genera una multa según 
los días de retraso.

En cualquier momento, el usuario puede listar películas, consultar 
disponibilidad o ver los clientes registrados.

El sistema termina únicamente cuando el usuario selecciona la opción 
"Salir", y al finalizar, se guarda toda la información actualizada.

El sistema también permite consultar estadísticas como:  
- La película menos rentada  
- La película más rentada  
- Todas las películas con el número total de veces que han sido rentadas
  





