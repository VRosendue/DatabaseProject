CREATE TABLE superheronpowers(
	Superhero_id int references superhero NOT NULL,
	Power_id int references power NOT NULL,
	PRIMARY KEY (Superhero_id, Power_id));