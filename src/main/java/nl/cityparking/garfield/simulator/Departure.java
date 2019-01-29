package nl.cityparking.garfield.simulator;

import nl.cityparking.garfield.simulator.agent.Agent;

/**
 * class that defines a departure
 *
 * @author Els Boerema
 */
public class Departure {
	public final long excessTime;
	public final long parkedTime;
	public final Agent agent;

	public Departure(Agent agent, long parkedTime, long excessTime){
		this.agent = agent;
		this.parkedTime = parkedTime;
		this.excessTime = excessTime;
	}
}
