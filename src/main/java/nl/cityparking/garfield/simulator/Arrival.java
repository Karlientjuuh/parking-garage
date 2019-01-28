package nl.cityparking.garfield.simulator;

import nl.cityparking.garfield.simulator.agent.Agent;

/**
 * Arrival describes an Agent that has or is going to arrive to the parking garage.
 *
 * @author Jesse
 * @since 1.0
 */
public class Arrival {
	/**
	 * The simulation minute which the Agent arrives on.
	 */
	public final long arrivalMinute;

	/**
	 * The simulation which the Agent leaves on.
	 */
	public final long departureMinute;

	/**
	 * The arriving Agent.
	 */
	public final Agent agent;

	/**
	 * Initializes a new Arrival object.
	 *
	 * @param arrivalMinute The simulation minute that this Arrival will arrive on.
	 * @param departureMinute The simulation minute that this Arrival will leave on.
	 * @param agent The arriving Agent.
	 */
	public Arrival(long arrivalMinute, long departureMinute, Agent agent) {
		this.arrivalMinute = arrivalMinute;
		this.departureMinute = departureMinute;
		this.agent = agent;
	}
}
