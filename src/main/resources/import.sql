INSERT INTO usuarios(id_user, name) VALUES (1,'Adrian');
INSERT INTO usuarios(id_user, name) VALUES (2,'Anabel');
INSERT INTO usuarios(id_user, name) VALUES (3,'Miguel');

INSERT INTO coches(id_car, marca, model, user_id_user) VALUES (1, 'Seat', 'Leon',1); 
INSERT INTO coches(id_car, marca, model, user_id_user) VALUES (2, 'Mercedes', 'CLA',1); 
INSERT INTO coches(id_car, marca, model, user_id_user) VALUES (3, 'Peugeot', '208',2); 
INSERT INTO coches(id_car, marca, model, user_id_user) VALUES (4, 'Audi', 'Q3',2); 

INSERT INTO coches(id_car, marca, model, user_id_user) VALUES (5, 'Ford', 'Mustang',3); 

INSERT INTO alquileres(id_rent, fin_alquiler, inicio_alquiler, precio, car_id_car, user_id_user) VALUES (1, NOW(), NOW(), 1000, 1, 1);
INSERT INTO alquileres(id_rent, fin_alquiler, inicio_alquiler, precio, car_id_car, user_id_user) VALUES (2, NOW(), NOW(), 1000, 2, 2);
INSERT INTO alquileres(id_rent, fin_alquiler, inicio_alquiler, precio, car_id_car, user_id_user) VALUES (3, NOW(), NOW(), 1000, 3, 3);
INSERT INTO alquileres(id_rent, fin_alquiler, inicio_alquiler, precio, car_id_car, user_id_user) VALUES (4, NOW(), NOW(), 1000, 4, 1);