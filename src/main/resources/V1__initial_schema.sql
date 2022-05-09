create table if not exists cw_owner
(
	owner_id        serial not null primary key,
	phone           varchar(20),
	first_name      varchar(30),
	second_name     varchar(30),
	third_name      varchar(30),
	region          varchar(30),
	city            varchar(30),
	street          varchar(30),
	building        varchar(30),
	flat            varchar(30)
);

create table if not exists carwash
(
	carwash_id      serial not null primary key,
	owner_id        serial references cw_owner(owner_id),
	carwash_num     varchar(20),
	region          varchar(30),
	city            varchar(30),
	street          varchar(30),
	building        varchar(30),
	phone           varchar(20)
);

create table if not exists status_cash
(
	trans_id        serial not null primary key,
	data_time       timestamp,
	owner_id        serial references cw_owner(owner_id),
	carwash_id      serial references carwash(carwash_id),
	work_status     varchar(5),
	post_status     varchar(40),
	cash_income     int,
	bank_income     int
);

INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-921-111-11-11', 'f_name1', 's_name1', 't_name1', 'region1', 'city1','street1', 'build1', 'flat1');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-922-222-22-22', 'f_name2', 's_name2', 't_name2', 'region2', 'city2','street2', 'build2', 'flat2');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-923-333-33-33', 'f_name3', 's_name3', 't_name3', 'region3', 'city3','street3', 'build3', 'flat3');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-924-444-44-44', 'f_name4', 's_name4', 't_name4', 'region4', 'city4','street4', 'build4', 'flat4');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-925-555-55-55', 'f_name5', 's_name5', 't_name5', 'region5', 'city5','street5', 'build5', 'flat5');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-926-666-66-66', 'f_name6', 's_name6', 't_name6', 'region6', 'city6','street6', 'build6', 'flat6');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-927-777-77-77', 'f_name7', 's_name7', 't_name7', 'region7', 'city7','street7', 'build7', 'flat7');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-928-888-88-88', 'f_name8', 's_name8', 't_name8', 'region8', 'city8','street8', 'build8', 'flat8');
INSERT INTO cw_owner (phone, first_name, second_name, third_name, region, city, street, building, flat) VALUES ('8-929-999-99-99', 'f_name9', 's_name9', 't_name9', 'region9', 'city9','street9', 'build9', 'flat9');


insert into carwash (owner_id, carwash_num, region, city, street, building, phone) values (1, '0000.000.00000', 'region0', 'city0','street0', 'build0','8-920-000-00-00');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (2, '1111.111.11111', 'region1', 'city1','street1', 'build1','8-921-111-11-11');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (2, '2222.222.22222', 'region2', 'city2','street2', 'build2','8-922-222-22-22');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (3, '3333.333.33333', 'region3', 'city3','street3', 'build3','8-923-333-33-33');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (3, '4444.444.44444', 'region4', 'city4','street4', 'build4','8-924-444-44-44');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (3, '5555.555.55555', 'region5', 'city5','street5', 'build5','8-925-555-55-55');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (6, '6666.666.66666', 'region6', 'city6','street6', 'build6','8-926-666-66-66');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (7, '7777.777.77777', 'region7', 'city7','street7', 'build7','8-927-777-77-77');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (8, '8888.888.88888', 'region8', 'city8','street8', 'build8','8-928-888-88-88');
INSERT INTO carwash (owner_id, carwash_num, region, city, street, building, phone) VALUES (9, '9999.999.99999', 'region9', 'city9','street9', 'build9','9-929-999-99-99');