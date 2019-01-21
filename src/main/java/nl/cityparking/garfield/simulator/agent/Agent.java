package nl.cityparking.garfield.simulator.agent;

/**
 * Agent is the most granular object that is simulated. An agent in the
 * simulator is most easily represented as a person. For the sake of the
 * simulation engine however, this is irrelevant. Thus the agent can be thought
 * of as a more abstract thing.
 *
 * Agents have agency, in this sense represented as their behaviour. Their
 * behaviour decides for example how likely they are to have a parking pass
 * and for how long they will park their car, and when they are most likely to
 * spawn (arrive) at the garage. But also where they park their car, and how.
 *
 * Agents are the lifeblood of the simulator. Almost all logic depends on them
 * in some way, they are what makes everything move and seem 'alive.'
 *
 * @author Jesse
 * @since 1.0-RELEASE
 */
public class Agent {
	private final String firstName;
	private final String lastName;
	private Employment employment = null;

	public Agent(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}

	public Employment getEmployment() {
		return employment;
	}

	public boolean isEmployed() {
		return employment != null;
	}
}
