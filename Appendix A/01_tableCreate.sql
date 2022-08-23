CREATE TABLE Superhero(
	Id SERIAL PRIMARY KEY,
	Name TEXT NOT NULL,
	Alias TEXT NOT NULL,
	Origin TEXT NOT NULL);
	
CREATE TABLE Assistant(
	Id SERIAL PRIMARY KEY,
	Name TEXT NOT NULL);
		
CREATE TABLE Power(
	Id SERIAL PRIMARY KEY,
	Name TEXT NOT NULL,
	Description TEXT NOT NULL);
