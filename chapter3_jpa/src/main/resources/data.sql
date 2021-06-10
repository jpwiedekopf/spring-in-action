-- noinspection SqlWithoutWhereForFile

DELETE
FROM TACO_ORDER_TACOS;
DELETE
FROM TACO_INGREDIENTS;
DELETE
FROM TACO;
DELETE
FROM TACO_ORDER;
DELETE
FROM INGREDIENT;
INSERT INTO Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP'),
       ('COTO', 'Corn Tortilla', 'WRAP'),
       ('GRBF', 'Ground Beef', 'PROTEIN'),
       ('CARN', 'Carnitas', 'PROTEIN'),
       ('VGCK', 'Vegan Chicken', 'PROTEIN'),
       ('TMTO', 'Tomato', 'VEGGIES'),
       ('LETC', 'Lettuce', 'VEGGIES'),
       ('CHED', 'Cheddar', 'CHEESE'),
       ('JACK', 'Monterrey Jack', 'CHEESE'),
       ('MOZA', 'Mozzarella', 'CHEESE'),
       ('SLSA', 'Salsa', 'SAUCE'),
       ('SRCR', 'Sour Cream', 'SAUCE');