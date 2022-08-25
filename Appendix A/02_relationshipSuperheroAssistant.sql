ALTER TABLE superhero DROP COLUMN IF EXISTS Assistant_id;
ALTER TABLE superhero DROP COLUMN IF EXISTS Power_id;
ALTER TABLE power DROP COLUMN IF EXISTS Superhero_id;
ALTER TABLE assistant DROP COLUMN IF EXISTS Superhero_id;
ALTER TABLE superhero ADD COLUMN Assistant_id int REFERENCES assistant;
ALTER TABLE superhero ADD COLUMN Power_id int REFERENCES power;
ALTER TABLE power ADD COLUMN Superhero_id int REFERENCES superhero;
ALTER TABLE assistant ADD COLUMN Superhero_id int REFERENCES superhero

ALTER TABLE superhero ADD CONSTRAINT fk_power FOREIGN KEY(power_id) REFERENCES power(id) ON DELETE CASCADE;
ALTER TABLE power ADD CONSTRAINT fk_superhero FOREIGN KEY(superhero_id) REFERENCES superhero(id) ON DELETE CASCADE;
ALTER TABLE assistant ADD CONSTRAINT fk_superhero FOREIGN KEY(superhero_id) REFERENCES superhero(id) ON DELETE CASCADE;
