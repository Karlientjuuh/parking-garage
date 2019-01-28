package nl.cityparking.garfield.simulator.agent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates Agents for use in the simulation.
 *
 * @author Jesse
 * @since 1.0
 */
public class AgentGenerator {
	public final static String[] MALE_FIRST_NAMES = {
			"Ulysses", "Gavin", "Malcom", "Mitchel", "Dennis", "Felton", "Mohammed", "Hector", "Jeromy", "Theodore",
			"Alfonzo", "Jesse", "Homer", "Salvatore", "Wesley", "Efrain", "Jayson", "Woodrow", "Donald", "Denver",
			"Odis", "Ed", "Kieth", "Chauncey", "Noble", "Angelo", "Chris", "Tyree", "Norbert", "Adan", "Don",
			"Benjamin", "Harrison", "Laurence", "Garth", "Milton", "Alonso", "Clayton", "Sang", "Rufus", "Albert",
			"Arlie", "Bret", "Grant", "Aurelio", "Markus", "Tanner", "Dion", "Hayden"
	};

	public final static String[] FEMALE_FIRST_NAMES = {
			"Debrah", "Eura", "Amber", "Zelma", "Tasha", "Herma", "Rozella", "Yung", "Adell", "Lynette", "Rosalva",
			"Kala", "Yen", "Shanta", "Hyun", "Nereida", "Leandra", "Phuong", "Joycelyn", "Maxie", "Ara", "Angeline",
			"Marlene", "Miesha", "Verla", "Hulda", "Renata", "Elba", "Jesusa", "Maren", "Amiee", "Iva", "Silva",
			"Silvana", "Laura", "Shelli", "Vernice", "Caron", "Delphine", "Raven", "Johnna", "Cherish", "Allegra",
			"Marianne", "Nia", "Denese", "Ashlyn", "Shan", "Jeri", "Suzanna"
	};

	public final static String[] LAST_NAMES = {
			"Nolan", "Chavez", "Garcia", "Carr", "Cook", "Kaiser", "Rollins", "Burch", "Rosales", "Soto", "Bridges",
			"Clayton", "Ritter", "Hays", "Peterson", "Gibbs", "Horne", "Ponce", "James", "Haynes", "Hutchinson",
			"Fowler", "Roberts", "Collins", "Campos", "Boyle", "Mcknight", "Scott", "Valdez", "Woods", "Patel",
			"Porter", "Paul", "Sexton", "Liu", "Salinas", "Cross", "Luna", "Whitaker", "Ramsey", "Shepard", "Mendez",
			"Walton", "Brady", "Navarro", "Norris", "Kramer", "Summers", "Huynh", "Bradley", "Rose", "Gamble", "Lane",
			"Dixon", "Juarez", "Ford", "White", "Dickerson", "Yang", "Fields", "Richard", "Collier", "Watson", "Gray",
			"Durham", "Yu", "Winters", "Le", "Fleming", "Kirk", "Ramirez", "Barr", "Butler", "Perry", "Rubio", "Carson",
			"Mccoy", "Rich", "Harper", "Bowen", "Lara", "Rhodes", "Parrish", "Wade", "Lewis", "Wang", "Coleman",
			"Fuentes", "Benton", "Church", "Smith", "Powers", "Tate", "Mclaughlin", "Riggs", "Terry", "Nielsen",
			"Francis", "Schroeder", "Lowe", "Phillips", "Matthews", "Allison", "Ball", "Brewer", "Marsh", "Holden",
			"Bradshaw", "Andersen", "Gallagher", "Mullen", "Perkins", "Pugh", "Leon", "Schaefer", "Fernandez",
			"Farrell", "Carney", "Owen", "Cochran", "Monroe", "Best", "Mckee", "Bennett", "Levine", "Warner", "Shannon",
			"Blackwell", "Love", "Buchanan", "Holder", "Arnold", "Higgins", "Elliott", "Murphy", "Gardner", "Bryant",
			"Mckay", "Reeves", "Castaneda", "Ortiz", "Dickson", "Mack", "Leblanc", "Barker", "Steele", "Solis",
			"Davenport", "Bass", "Ewing", "Berger", "Jacobson", "Mcmillan", "Conway", "Bautista", "Foster", "Blair",
			"Watkins", "Tyler", "Santos", "Palmer", "Randall", "Dorsey", "May", "Strickland", "Reyes", "Nixon", "Kerr",
			"French", "Key", "Beasley", "Moses", "Lloyd", "Lynn", "Lindsey", "Bean", "Cain", "Ross", "Davies",
			"Anderson", "Hunter", "Melton", "Ray", "King", "Hogan", "Christensen", "Glenn", "Mckinney", "Pollard",
			"Kane", "Mathews", "Chandler", "Crane", "Moore", "Mata", "Figueroa", "Blackburn", "Duffy", "Obrien",
			"Huang"
	};

	/**
	 * Generates an Agent.
	 * @return The generated Agent.
	 */
	public Agent generate() {
		String firstName = (ThreadLocalRandom.current().nextBoolean())
				? FEMALE_FIRST_NAMES[ThreadLocalRandom.current().nextInt(0, FEMALE_FIRST_NAMES.length)]
				: MALE_FIRST_NAMES[ThreadLocalRandom.current().nextInt(0, MALE_FIRST_NAMES.length)];
		String lastName = LAST_NAMES[ThreadLocalRandom.current().nextInt(0, LAST_NAMES.length)];

		Agent agent = new Agent(firstName, lastName);

		// Generate a 9-to-5, monday to friday job for our good agent.
		Employment employment = new Employment();

		for (int i = 5; i >= 0; i--) {
			employment.addWorkingHours(new Employment.ScheduleEntry(
					ThreadLocalRandom.current().nextInt(7, 13) + i*24,
					ThreadLocalRandom.current().nextInt(17, 21) + i*24));
		}

		agent.setEmployment(employment);

		return agent;
	}
}
