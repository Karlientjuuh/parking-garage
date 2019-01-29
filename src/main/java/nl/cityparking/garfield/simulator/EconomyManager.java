package nl.cityparking.garfield.simulator;

import nl.cityparking.garfield.simulator.economy.Economy;
import java.util.Collection;

/**
 * Class that manages all things concerning economy
 *
 * @author Els Boerema
 */
public class EconomyManager {
	private Economy economy = new Economy();

	/**
	 * Method that processes the payment for each agent, decreasing the agent's wealth, increasing the company's funds.
	 * @param departures A collection containing agents that are departing the parking garage.
	 */
	public void processPayments(Collection<Departure> departures) {
		for (Departure departure : departures) {
			long price = economy.calculateTicket(departure.parkedTime);
			departure.agent.setWealth(departure.agent.getWealth() - price);
		}
	}

	public Economy getEconomy() {
		return economy;
	}
}
