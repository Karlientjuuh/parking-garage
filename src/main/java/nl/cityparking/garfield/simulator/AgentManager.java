package nl.cityparking.garfield.simulator;

import nl.cityparking.garfield.simulator.agent.Agent;
import nl.cityparking.garfield.simulator.agent.AgentGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * AgentManager handles the generation, modification and querying of Agents in the Simulation. All their logic and
 * behaviour generally speaking gets resolved and parsed here for use in the rest of the simulation. The consequence
 * of that is that while the simulation appears to be "live" and updates happening in real time, a lot of it is
 * actually precomputed and destined to happen, usually a week in advance.
 *
 * @author Jesse
 * @since 1.0
 */
public class AgentManager {
	private AgentGenerator generator = new AgentGenerator();
	private ArrayList<Agent> agents = new ArrayList<>();
	private ArrayList<Agent> commuters = new ArrayList<>();

	/**
	 * Initializes the AgentManager
	 */
	public AgentManager() {
		// TODO: This shouldn't be hard coded, of course.
		for (int i = 0; i < 600; i++) {
			Agent a = generator.generate();
			agents.add(a);

			if (a.isEmployed()) {
				commuters.add(a);
			}
		}

		commuters.sort(Comparator.comparingLong(a -> a.getEmployment().getNextWorkHour(0).getStartHour()));
	}

	/**
	 * Retrieves the list of all Agents present in the simulation, including inactive ones
	 * @return Collection of all Agents
	 */
	public Collection<Agent> getAgents() {
		return agents;
	}

	/**
	 * Retrieves the list of all Agents that are commuters (people with work), this does not include inactive agents.
	 * @return Collection of all commuting Agents
	 */
	public Collection<Agent> getCommuters() {
		return commuters;
	}
}
