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

	/**
	 * Initializes a new Agent
	 * @param firstName The first name of the Agent.
	 * @param lastName The last name of the Agent.
	 */
	public Agent(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Returns the full name of the Agent.
	 * @return The full name.
	 */
	public String getName() {
		return firstName + " " + lastName;
	}

	/**
	 * Gets the first name of the Agent.
	 * @return The first name of the Agent.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name of the Agent.
	 * @return The last name of the Agent.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the employment of this Agent, replacing it for whatever old employment it had.
	 * @param employment The Employment to assign to this agent. Null to make the Agent unemployed.
	 */
	public void setEmployment(Employment employment) {
		this.employment = employment;
	}

	/**
	 * Gets the Agent's employment.
	 * @return The Employment of the Agent, or null if unemployed.
	 */
	public Employment getEmployment() {
		return employment;
	}

	/**
	 * Gets the Agent's employment status.
	 * @return true for employed, false for unemployed.
	 */
	public boolean isEmployed() {
		return employment != null;
	}
}
