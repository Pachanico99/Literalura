Desarrollo del challenge de Alura Literalura.

OBJETIVO: 
- Desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. 
- Los libros se buscarán a través de una API específica.
- La información sobre la API y las opciones de interacción con el usuario se detallará en la columna "Backlog"/"Listo para iniciar".

Tecnologias:
- JAVA con jdk 17
- Postgres
- Spring 3.2.6 | Maven proyect
    -Dependecias:
      . Spring Data JPA
      . PostgreSQL Driver
      . Spring Boot DevTools
      . JSON

Api:
Se consumira la api https://gutendex.com/ para obtener informacion de los libros.

Cuando se realiza un solictud a la api, esta devuelve el siguiente formato:

![image](https://github.com/Pachanico99/Literalura/assets/156742223/c8cfb7b1-8f23-425b-8e5f-5b3e6dafe36a)

Donde:
- count: contiene el total de libros que maneja la api.
- next: contiene la url para visualizar los siguientes libros 32 libros (si estan disponibles).
- previus: contiene la url para visualizar los anteriores libros 32 libros (si estan disponibles).
- results: contiene un array de 32 libros.

La informacion que no interesa se encuentra en results. Cada elemento de este array contiene el siguiente formato:

![image](https://github.com/Pachanico99/Literalura/assets/156742223/d2143c90-98b6-4a53-9f12-0f351d3888ea)

Libro / Book:
- title: titulo del libro.
- download_count: contiene las descarga del libro.
- authors: contiene los autores que escribieron el libro.
- languages: contiene los idiomas a los que fue traducido el libro.

Autor / Author:
- name: nombre del autor.
- deadYear: año del fallecimiento (puede ser null).
- bornYear: año de nacimiento (puede ser null).

Idiomas / Languages:
- contiene string que tiene el codigo del idioma.

Creacion de la DB:

![image](https://github.com/Pachanico99/Literalura/assets/156742223/6fd10071-4299-40b3-aea3-4d2b28351199)

Aclaracion:
La tabla languages tiene solo la PK, porque se buscara (en un futuro) otra api que agrege complementos a este (pais, nombre del idioma, etc).

Relaciones:
Cada libro puede tener uno o mas autores. Y cada autor puede tener uno o mas libros escritos.
Cada libro puede tener uno o mas idiomas. Y cada idioma tiene uno o mas libros.

Literalura, su interracion con el usuario se lleva acabo mediante la consola con un menu de opciones.

Pruebas:

Manu:

![image](https://github.com/Pachanico99/Literalura/assets/156742223/c344bef9-b434-42e1-b311-8f18bcdbe8ec)


1- Busca mediante la entrada por teclado del usuario del titulo de un libro en la api y la agrega a la DB.

![image](https://github.com/Pachanico99/Literalura/assets/156742223/9a500a37-11a6-46d1-938b-9eb2aa6cb9eb)

2- Busca mediante la entrada por teclado del usuario del titulo y nombre de author de un libro en la api y la agrega a la DB.

![image](https://github.com/Pachanico99/Literalura/assets/156742223/4e5e3fa0-ca04-4c0e-b8c5-15e6293668bc)

El ejemplo ingresado busca ver la opcion cuando no se encuentra el libro.

3- Lista todos los libros que estan en la DB, siguiendo el formato:

![image](https://github.com/Pachanico99/Literalura/assets/156742223/c9c71dac-2f63-4cd8-8248-b699ee065b1d)

4- Lista todos los autores que estan en la DB, siguiendo el formato:

![image](https://github.com/Pachanico99/Literalura/assets/156742223/c953a721-a8c0-45df-8ca4-d3ca4e628995)

5- Lista todos los idiomas que estan en la DB, siguiendo el formato:

![image](https://github.com/Pachanico99/Literalura/assets/156742223/1e8efcfb-69c9-4c36-9eca-460cb88c7f45)

6- Lista a todos los autores de la DB que estuvieron vivos en un año ingresado.

![image](https://github.com/Pachanico99/Literalura/assets/156742223/623e050d-ec34-4676-a237-d6d783f11103)

7- Lista a todos los libros de la DB que esta traducidos al idioma ingresado.

![image](https://github.com/Pachanico99/Literalura/assets/156742223/74ea2371-86db-4d15-9207-4bf14fa1d8dd)

8- Lista el libro con mas descargas.

9- Muestra los 10 libros con mas descargas (de mayor a menor).

10- Busca en la DB el libro, mediante un titulo ingresado.

11- Busca en la DB el autor, mediante el nombre ingresado.

12- Lista todos los libros escritor por un autor, ingresado el nombre de este.

13- Entra en el menu de estadisticas.

![image](https://github.com/Pachanico99/Literalura/assets/156742223/b5d3d1c7-0bf3-42f5-a43c-2b8550cc32e8)

13.1- Lista el porcentaje de libro por idiomas.

![image](https://github.com/Pachanico99/Literalura/assets/156742223/6c394941-76ab-49ef-b68a-000cd2759754)

13.2- Lista el porcentaje de libros por autor (con el siguiente formato).

![image](https://github.com/Pachanico99/Literalura/assets/156742223/09191187-23f1-4a65-8892-612fdfb14e16)

13.3- Devuelve el autor con mas libros descargados.

13.4- Devuelve el promedio de descargas por libro

13.5- Devuele la cantidad de descargar mas alta de un libro

13.6- Devuele la cantidad de descargar menos alta de un libro

13.7- Devuelve el total de descargas (la suma de la descargas de todos los libros).

