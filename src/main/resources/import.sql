-- delete from Taco;
-- delete from Taco_Order;
-- delete from Ingredient;

insert ignore into Ingredient (id, name, type) values ('FLTO', 'Flour Tortilla', 'WRAP');
insert ignore into Ingredient (id, name, type) values ('COTO', 'Corn Tortilla', 'WRAP');
insert ignore into Ingredient (id, name, type) values ('GRBF', 'Ground Beef', 'PROTEIN');
insert ignore into Ingredient (id, name, type) values ('CARN', 'Carnitas', 'PROTEIN');
insert ignore into Ingredient (id, name, type) values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert ignore into Ingredient (id, name, type) values ('LETC', 'Lettuce', 'VEGGIES');
insert ignore into Ingredient (id, name, type) values ('CHED', 'Cheddar', 'CHEESE');
insert ignore into Ingredient (id, name, type) values ('JACK', 'Monterrey Jack', 'CHEESE');
insert ignore into Ingredient (id, name, type) values ('SLSA', 'Salsa', 'SAUCE');
insert ignore into Ingredient (id, name, type) values ('SRCR', 'Sour Cream', 'SAUCE');

-- users
-- delete from authorities;
-- delete from users;

insert ignore into users (username, password, fullName, street, city, zipCode, phoneNumber, accountValid, credentialsValid, active, enabled) values ('admin', '$2a$10$DEwupH7UgLAPa6BG8llBcOcsrg0vYgZUzDn8nDznJSqGmHlE2ioiC', 'John Doe', 'New York', 'Lincoln st.', '01000', '555662777', 1, 1, 1, 1);
insert ignore into authorities (username, authority) values ('admin', 'ADMIN');
