CREATE TABLE superheronpowers(
	superhero_id int references superhero NOT NULL,
	power_id int references power NOT NULL,
	PRIMARY KEY (superhero_id, power_id));