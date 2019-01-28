package nl.cityparking.garfield.simulator;

import nl.cityparking.garfield.simulator.agent.Agent;
import nl.cityparking.garfield.simulator.config.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Simulator is an abstraction of a parking garage. It's the primary controller of all the happenings in the simulator
 * and serves as its heart and core. It pumps data around from one place to another, makes sure events are handled and
 * processed properly and makes sure that subsystems of the simulator are called when required.
 *
 * The Simulator puts it all together and presents this data in a way that a frontend such as a GUI can use.
 *
 * @author Jesse
 * @since 1.0
 */
public class Simulator implements Runnable {
	private Configuration conf;
    private SimulatorTime simulationTime;
    private AgentManager agentManager = new AgentManager();
    private ArrivalManager arrivalManager = new ArrivalManager();
    private ParkingManager parkingManager = new ParkingManager();

    private boolean stopping = false;
    private long carsIn = 0;
    private long carsOut = 0;

	/**
	 * Initializes the Simulator and prepares it for runtime.
	 * @param configuration Configuration object with settings to be used by the simulator runtime.
	 */
	public Simulator(Configuration configuration) {
	    conf = configuration;

    	simulationTime = new SimulatorTime(1);
	    simulationTime.setOnTick(this::onTick);
	    simulationTime.setOnMinutePassed(this::onMinutePassed);
	    simulationTime.setOnHourPassed(this::onHourPassed);
	    simulationTime.setOnDayPassed(this::onDayPassed);
	    simulationTime.setOnWeekPassed(this::onWeekPassed);

		// TODO: Allow the layout of the garage to be configured.
	    parkingManager.addFloors(3, 5, 40);
    }

	/**
	 * Starts the simulation. This method blocks until stop is called.
	 */
	public void run() {
		stopping = false;

		while (!stopping) {
        	simulationTime.tick();
        }
    }

	/**
	 * Stops the simulation.
	 */
	public void stop() {
		stopping = true;
	}

    private void onTick() {
    }

    private void onMinutePassed() {
    	// Phase one, get leavers:
	    Collection<Agent> result = parkingManager.getLeavingAgents(simulationTime.getMinutesPassed());
	    carsOut += result.size();

	    // Phase two, get arrivals:
    	Collection<Arrival> arrivals = arrivalManager.getArrivals(simulationTime.getMinutesPassed());
    	for (Arrival arrival: arrivals) {
    		if (parkingManager.handleArrival(arrival)) {
			    carsIn++;
		    }
	    }
    }

	private void onHourPassed() {
	}

	private void onDayPassed() {
    }

    private void onWeekPassed() {
    	arrivalManager.generate(agentManager.getCommuters(), simulationTime.getMinutesPassed());
    }

	public SimulatorTime getSimulationTime() {
		return simulationTime;
	}

	public long getCarsIn() {
		return carsIn;
	}

	public long getCarsOut() {
		return carsOut;
	}

	public ParkingManager getParkingManager() {
    	return parkingManager;
	}
}
