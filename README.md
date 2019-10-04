# Alquiler de coches
Proyecto final para el curso de ATSistemas.

## Descripción del proyecto
Se nos pide crear un proyecto en Spring que gestione el alquiler de coches.
Nuestro proyecto estará formado por las clases: User, Car y Rent.

### Clases proyecto

#### User
La clase User hace referencia a la persona que alquila un coche. Esta clase tendrá los atributos:
  - idUser: integer.
  - name: string.
  
#### Car
La clase Car hace referencia al coche que puede alquilar una persona. Estos coches pertenecen a personas que prestan su coche en alquiler.
Esta clase contendrá los siguientes atributos:
  - idCar: integer.
  - marca: string.
  - model: string.
  - user_id_user: integer

#### Rent
En la clase Rent se guardarán los datos relacionados con el alquiler. Un alquiler solo puede ser realizado por una persona y un coche solo puede pertener a una persona en un mismo periodo de tiempo.
Los atributos de dicha clase serán:
  - idRent: integer.
  - initRent: LocalDate.
  - finalRent: LocalDate.
  - price: double.
  - user_id_user: integer.
  - car_id_car: integer.
