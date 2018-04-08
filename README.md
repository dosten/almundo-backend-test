Almundo Backend Test
====================

Explicación
-----------

La clase `Dispatcher` hace uso principalmente de dos componentes propios de java.

Por un lado hace uso de `ExecutorService` con el cual creo un pool de threads
con una cantidad máxima fija, la cual es configurable mediante el constructor
de la clase, esto me permite definir cuantas llamas concurrentes puede soportar
el dispatcher, si el dispatcher está completo, entonces las nuevas llamadas se
van a encolar.

Por otro lado hace uso de `PriorityBlockingQueue` en la cual defino una queue de
empleados, los cuales tienen un rol, el cual tiene a su vez una prioridad
(como estamos hablando de un call center, los operadores tienen la mayor
prioridad y los directores tiene la menor prioridad).
Con cada llamada nueva, se toma un empleado con la mayor prioridad y se lo
asigna a esa llamada, una vez terminada la llamada se vuelve a agregar al
empleado a la queue para que pueda tomar nuevas llamadas.

Internamente el dispatcher no hace ningún manejo sobre la duración de la
llamada, esa tarea queda para el que implemente el dispatcher, en el caso de los
tests, se simula una duración random entre 5 y 10 segundos, luego se termina la
llamada.

Puntos Extra
------------

- Cuando no hay ningún empleado disponible, simplemente se continua esperando
  indefinidamente a que uno este disponible, si cancelamos la llamada hacemos
  que el cliente pierda su "lugar" en la lista de espera, por lo que muevo esa
  responsabilidad al cliente.

- Cuando se reciben más llamadas de las que soporta el dispatcher, lo que se
  hace es dejar en espera a las llamadas extra y una vez que otra llamada
  termine el empleado tomara la más antigua de las llamadas
  (el que primero llamo, primero es atendido).

Tests
-----

Se encuentran disponibles varios tests unitarios para probar la funcionalidad
del dispatcher (requisitos y puntos extra), para ejecutarlos, se debe correr:

```bash
$ mvn test
```
