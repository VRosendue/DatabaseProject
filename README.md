# DatabaseProject

DatabaseProject is a program that enables the user to pull data (specifically CRUD)
from a Postgres local server. 
Furthermore it has the capabilities of searching for advanced specifics in the given database.


## Usage

CRUD related queries. Examples could be:

#Returns a customer from the database by inputting the Primary Key --
GetActors.findCustomerById(INSERT INT ID HERE);

#Returns all customers in the database --
GetActors.getCustomer();

#Returns a specific customer by searching for the first name -- 
GetActors.findCustomerByName("INSERT NAME HERE");

#Creates a customer by inputting the specific fields --
GetActors.createCustomer(ID, "firstname", "lastname", "country", "postalcode", "phone number", "email");

#Updates a customer by inputting the values you want to update - Input an id int to target the specific customer you want to update --
GetActors.updateCustomer("firstname", "lastname", "country", "postalcode", "phone", "email", ID INT you want to update)

#Allows the user to limit and offset the search by inputting the specific number -- 
GetActors.limitCustomerSearch(LIMIT BY INT, OFFSET BY INT) 

#Returns the country with the most customers -- 
GetActors.countryWithMostCustomers();

```

Written in Collaboration with MGylden
