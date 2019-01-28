package nl.cityparking.garfield.simulator;

import nl.cityparking.garfield.simulator.agent.Agent;
import nl.cityparking.garfield.simulator.agent.Employment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ArrivalManager is responsible for creating commute schedules. In essence, the ArrivalManager makes the simulation
 * feel alive, cause it essentially handles the out and influx of agents into the garage.
 *
 * @author Jesse
 * @since 1.0
 */
public class ArrivalManager {
	private ArrayList<Arrival> arrivals;
	private int index = 0;
	private long nextArrival = 0;

	/**
	 * Generates a collection of Arrivals, based upon the information about the Agent, such as whether or not they
	 * work, when they work, and other conditions.
	 *
	 * @param agents A collection of agents to create the Arrivals from.
	 * @param startOfWeek The simulation minute upon which this week starts.
	 */
	public void generate(Collection<Agent> agents, long startOfWeek) {
		arrivals = new ArrayList<>();
		index = 0;
		nextArrival = 0;

		for (Agent agent: agents) {
			if (agent.isEmployed()) {
				Employment employment = agent.getEmployment();

				// For each work day, we want to add an arrival.
				for (Employment.ScheduleEntry scheduleEntry : employment.getSchedule()) {
					long adjustedArrivalMinute = scheduleEntry.getStartHour() * 60 - ThreadLocalRandom.current().nextLong(60);
					long adjustedDepartureMinute = scheduleEntry.getEndHour() * 60 - ThreadLocalRandom.current().nextLong(30);
					arrivals.add(new Arrival(adjustedArrivalMinute + startOfWeek, adjustedDepartureMinute + startOfWeek, agent));
				}
			}
		}

		arrivals.sort(Comparator.comparingLong(a -> a.arrivalMinute));
		nextArrival = arrivals.get(0).arrivalMinute;
	}

	/**
	 * Returns a collection of arrivals that are arriving on the given simulator minute.
	 * @param minuteOfWeek The simulator minute of the week.
	 * @return Collection of Arrivals
	 */
	public Collection<Arrival> getArrivals(long minuteOfWeek) {
		ArrayList<Arrival> arrivals = new ArrayList<>();

		while (nextArrival == minuteOfWeek && index < this.arrivals.size()) {
			Arrival arrival = this.arrivals.get(index);

			if (arrival.arrivalMinute > minuteOfWeek) {
				nextArrival = arrival.arrivalMinute;
				break;
			} else if (arrival.arrivalMinute == minuteOfWeek) {
				arrivals.add(arrival);
			}

			index++;
		}

		return arrivals;
	}
}
