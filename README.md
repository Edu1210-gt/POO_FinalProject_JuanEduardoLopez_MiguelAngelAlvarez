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
23. Permite validar el correo

==========================================================================
                        FUNCIONAMIENTO
===========================================================================
                        
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
  
===================================================================================
                    Instrucciones para ejecutar el programa
===================================================================================


Bienvenido al sistemas de Video Club

instrucciones generales:
Seleccionar siempre la opcion que desea usando el numero correspondiente.
Complete los datos solicitados con cuidado.
Se comete un error, el sistema le pedira reingresar los datos


Menu principal:
1. agregar cliente: 
    - ingrese nombre, apellido y documento
    - El sisteme validara que no exista un cliente con el mismo ID
2. Registrar pelicula
    -Ingrese titulo, genero y precio 
    -Este registro de peliculas es realizado solo por los empleados, para clientes.
3. Realizar alquiler:
    - Ingrese ID y la pelicula que desea alquilar 
    - El sistema genera un ID de alquiler y registra la fecha, ademas hace una factura con los datos pertinentes.
4. Devolver pelicula 
    - Ingrese ID de alquiler, el sistema calculara los dias de mora y costo adicionales si aplica.
    - Mostrar una factura de pago
5. Reportes
    - Listado de clientes
    - Peliculas
    - Alquileres
    - Alquileres Historicos
=============================================================================================

Consejos para utlizar el programa 
*Siempre ingresar datos validos*
*Ante la duda consulte la seccion de reportes*

=============================================================================================

                                Entradas y Salidas
============================================================================================
=============== MAIN MENU (TABLE VIEW) ===============
  MOVIE                         |   CUSTOMER
-------------------------------+-------------------------------
 1. add Movie                  |  9. add Customer
 2. lists of available movies  | 10. find Customer by Id
 3. find Movie by Id           | 11. find customer by Name
 4. search Movies by Title     | 12. update Customer
 5. search Movies by Genre     | 13. List of customers
 6. update Movie               | 14. delete Customer
 7. delete Movie               |
 8. List of movies             |

  RENTAL                       |   RETURN / RENT INFO
-------------------------------+-------------------------------
15. Rent movie                 | 16. Register Return
17. find rent by id            | 18. rent by customer
19. list of rentals            |

  REPORTS                      |   EXIT
-------------------------------+-------------------------------
20. least movie                | 23. Exit
21. most rented movie          |
22. Show movie with rent count |

Enter your choice: 9
--- Register Customer ---
Enter customer ID: C-001
Enter customer name: Juan Perez
Enter customer email: juan.perez@email.com
Enter customer phone number: 3001234567
Customer registered successfully:
Customer{id='C-001', name='Juan Perez', email='juan.perez@email.com', phoneNumber='3001234567'}

Enter your choice: 1
--- Register Movie ---
Enter movie title: The Matrix
Enter movie genre: Acción
Enter rental price: 4.5
Movie registered successfully:
Movie{id='M-457', title='The Matrix', genre='Acción', rentalPrice=4.5, available=true}

Enter your choice: 15
--- Register Rental ---
Enter customer ID: C-001
Enter number of rental days: 3
Enter movie ID: M-457
=========================================
              RENTAL RECEIPT             
=========================================
Rental ID      : R-102
Customer       : Juan Perez
Movie          : The Matrix
Rental Days    : 3
Total Costmoment : $13.5
Return Date    : 2025-12-01
=========================================
         Rental registered successfully!
=========================================

Enter your choice: 16
--- Register Return ---
Enter rental ID: R-102
=========================================
           RETURN RECEIPT                
=========================================
Rental ID       : R-102
Days of Arrears : 0
Cost of Arrears : $0.0
Total to Pay    : $13.5
=========================================
        Return registered successfully!  
=========================================

Enter your choice: 23
Exiting the program. Goodbye!


=======================================================================================
