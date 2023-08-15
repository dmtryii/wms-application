-- city
INSERT INTO city (name, country_name)
VALUES ('Kiev', 'Ukraine'),
       ('Lviv', 'Ukraine');

-- item
INSERT INTO item (name, price, description)
VALUES ('coffee beans', 22.3, 'power desc'),
       ('tea leaves', 5.5, 'tea desc'),
       ('microcircuits', 112.53, 'coffee desc');

-- category
INSERT INTO category (name, description)
VALUES ('food', 'food desc'),
       ('electrical engineering', 'electrical engineering desc');

-- product
INSERT INTO product (name, price, description, category_id)
VALUES ('coffee', 33.5, 'coffee desc', 1),
       ('tea', 20.5, 'tea desc', 1),
       ('motherboard', 523, 'coffee desc', 2);

-- address
INSERT INTO address (street_name, street_numbers, details, city_id)
VALUES ('Super street', '3A', 'Super details', 1),
       ('Second street', '10E', 'Second details', 2);

-- company
INSERT INTO company (name, rating, address_id)
VALUES ('Cool company', 0.0, 1),
       ('Second company', 5.3, 2);

-- warehouse
INSERT INTO warehouse (name, address_id)
VALUES ('Cool warehouse', 1),
       ('Second warehouse', 2);


---- USERS ----
INSERT INTO contacts (email, phone, first_name, last_name, bio)
VALUES ('dmtryii@gmail.com', '380979999999', 'Dmytro', 'Trokhymenko', 'my bio'),
       ('anna@gmail.com', '380938888888', 'Anna', 'Smith', 'anna bio'),
       ('bob@gmail.com', '380937777777', 'Bob', 'Muller', 'bob bio'),
       ('tom@gmail.com', '380936666666', 'Tom', 'Jones', 'tom bio'),
       ('sem@gmail.com', '380983333333', 'Sem', 'Taylor', 'sem bio'),
       ('alex@gmail.com', '380964444444', 'Alex', 'Brown', 'alex bio');

INSERT INTO users (username, password, create_data, contacts_id, address_id)
VALUES ('dmtryii', '$2a$10$rIxjcfKJ5P5k0Cc8/2quH.P1ygj49K/Ijp/QEN5FvlkJZlgy4tSlS', NOW(), 1, 1),
       ('anna', '$2a$10$bLxi3Y7FQDJk3T9ydshDyOd4x3f2M2QioqMKv7b3DBlbLNJAOmp8S', NOW(), 2, 2),
       ('bob', '$2a$10$I59RTL0RNp6ufM5ulqeQ/ujLxUdw2s1u78aCTdZzyP7rDlX2EOIq2', NOW(), 3, 1),
       ('tom', '$2a$10$he3ptuoods/kbdpnCsG4Q.OJ7Y5OdO.M8F.58yChqen9VJNippd5S', NOW(), 4, 2),
       ('sem', '$2a$10$LIcccl/miXdmhTOmw.wqI.UWWNDgfetojz2PiFssGTjh4jyaTdvDS', NOW(), 5, 1),
       ('alex', '$2a$10$RMAa8spmeu4DMWI5vskBq.jBzuAQazBc1x0J8n36DfSdHvKVNbdAS', NOW(), 6, 2);


---- USER ROLE ----

INSERT INTO manager(user_id, who_created_id, create_data)
VALUES (2, 1, NOW());

INSERT INTO supplier(user_id, company_id, who_created_id, create_data)
VALUES (3, 1, 1, NOW());

INSERT INTO employee(user_id, warehouse_id, who_created_id, create_data)
VALUES (4, 1, 1, NOW()),
       (5, 1, 1, NOW());

INSERT INTO user_role (roles, user_id)
VALUES (0, 1), (4, 1),
       (1, 2), (4, 2), -- anna -- manager
       (2, 3), (4, 3), -- bob  -- supplier
       (3, 4), (4, 4), -- tom  -- employee
       (3, 5), (4, 5), -- sem  -- employee
       (4, 6);
