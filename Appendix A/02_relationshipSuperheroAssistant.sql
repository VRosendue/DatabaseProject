ALTER TABLE assistant DROP COLUMN IF EXISTS Superhero_id;

ALTER TABLE assistant ADD COLUMN Superhero_id int REFERENCES superhero;

ALTER TABLE assistant ADD CONSTRAINT fk_superhero FOREIGN KEY (Superhero_id) REFERENCES superhero(Superhero_id) ON DELETE CASCADE;
