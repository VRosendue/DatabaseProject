package com.postgres;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.models.Actor;
import com.models.GetActors;
import com.operations.*;

@SpringBootApplication
public class DatabaseProjectApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DatabaseProjectApplication.class, args);
		System.out.println(Operations.bigSpenderCustomer(null, null, null));
		
		//GetActors.countryWithMostCustomers(); - Appendix B opgave 7 - Returns the country with the most customers
		//GetActors.updateCustomer("Mathias", "Gylden", "Denmark", "3670", "64523", "Math.Gyld@net", 60); - Appendix B opgave 6 
                //GetActors.createCustomer(60, "Testerson", "Testthis", "Denmark", "3670", "279384", "test@test"); - Appendix B opgave 5
		//GetActors.limitCustomerSearch(5, 2); - Appendix B opgave 4 - Limit, offset
		//GetActors.findCustomerByName("Enrique"); //Appendix B opgave 3 - VIRKER NOGENLUNDE KUNNE BRUGE ET FIX
		//GetActors.findCustomerById(50); //Appendix B opgave 2 - Read specific customer by ID
		
		//GetActors.getCustomer(); //Appendix B opgave 1 - Read all customers in the database
		
		/*Actor conn  = new Actor();
		conn.test();*/ //Connection test
		
		
	}


	}

