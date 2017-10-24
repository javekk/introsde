package examples;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import examples.pojos.HealthProfile;
import examples.pojos.Person;


public class HealthProfileReader {
	
	public static Map<String,Person> database = new HashMap<String,Person>();
	
	static
    {
    	Person pallino = new Person();
		Person pallo = new Person("Pinco","Pallo");
		HealthProfile hp = new HealthProfile(68.0,1.72);
		Person john = new Person("John","Doe",hp);
		
		database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
		database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
		database.put(john.getFirstname()+" "+john.getLastname(), john);
    }
	/**
	 * The health profile reader gets information from the command line about
	 * weight and height and calculates the BMI of the person based on this 
	 * parameters
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//initializeDatabase();
		int argCount = args.length;
		if (argCount == 0) {
			System.out.println("I cannot create people out of thing air. Give me at least a name and lastname.");
		} else if (argCount < 2) {
			System.out.println("Are you sure you gave me a first and lastname?");
		} else if (argCount == 2) {
			String fname = args[0];
			String lname = args[1];
			// read the person from the DB
			Person p= database.get(fname+" "+lname);
			if (p!=null) { 
				System.out.println(fname+" "+lname+"'s health profile is: "+p.gethProfile().toString());
			} else {
				System.out.println(fname+" "+lname+" is not in the database");
			}
		}
		createPerson("Cane", "Dog", new Date(System.currentTimeMillis()));
		long pId = database.get("Cane" + " " + "Dog").getId();
		displayHealthProfile(pId);
		updateHealthProfile(pId, 192. , 123.);
		// add the case where there are 3 parameters, the third being a string that matches "weight", "height" or "bmi"
	}
	
	public static void createPerson(String firstname, String lastname, Date birthdate) {
		Person p = new Person(firstname, lastname, birthdate);
		database.put(p.getFirstname()+ " " + p.getLastname(), p);
	}
	
	public static void displayHealthProfile(Long personId) {
		boolean gotIt = false;
		for (Map.Entry<String, Person> entry : database.entrySet()){
			if(entry.getValue() instanceof Person) {
				Person p = (Person) entry.getValue();
				if(p.getId() == personId) {
					System.out.println(p.getFirstname()
										+" "
										+ p.getLastname()
										+"'s health profile is: "
										+p.gethProfile().toString());
					gotIt=true;
				}
			}
		}
		if(!gotIt) {
			System.out.println(personId + " is not in the database");
		}
	}
	
	public static void updateHealthProfile(Long personId, Double height, Double weight) {
		boolean gotIt = false;
		for (Map.Entry<String, Person> entry : database.entrySet()){
			if(entry.getValue() instanceof Person) {
				Person p = (Person) entry.getValue();
				if(p.getId() == personId) {
					p.sethProfile(new HealthProfile(weight, height));
					System.out.println(p.getFirstname()
										+" "
										+ p.getLastname()
										+" new health profile is: "
										+p.gethProfile().toString());
					gotIt=true;
				}
			}
		}
		if(!gotIt) {
			System.out.println(personId + " is not in the database");
		}
	}
	
	
	//public static void initializeDatabase() {
	//	Person pallino = new Person();
	//	Person pallo = new Person("Pinco","Pallo");
	//	HealthProfile hp = new HealthProfile(68.0,1.72);
	//	Person john = new Person("John","Doe",hp);
	//	
	//	database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
	//	database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
	//	database.put(john.getFirstname()+" "+john.getLastname(), john);
	//}
}