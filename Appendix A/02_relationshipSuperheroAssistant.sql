ALTER TABLE superhero DROP COLUMN IF EXISTS assistant_id;
ALTER TABLE superhero DROP COLUMN IF EXISTS power_id;
ALTER TABLE power DROP COLUMN IF EXISTS superhero_id;
ALTER TABLE assistant DROP COLUMN IF EXISTS superhero_id;
ALTER TABLE superhero ADD COLUMN assistant_id int REFERENCES assistant;
ALTER TABLE superhero ADD COLUMN power_id int REFERENCES power;
ALTER TABLE power ADD COLUMN superhero_id int REFERENCES superhero;
ALTER TABLE assistant ADD COLUMN superhero_id int REFERENCES superhero