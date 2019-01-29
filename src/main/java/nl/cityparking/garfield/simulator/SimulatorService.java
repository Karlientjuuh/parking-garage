package nl.cityparking.garfield.simulator;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import nl.cityparking.garfield.simulator.economy.Report;

import java.util.Collection;

/**
 * SimulatorService is a scheduled service that can be run on the JavaFX
 * application thread. It polls the Simulator on regular intervals for its
 * current state.
 *
 * @author Jesse
 * @version 1.0
 */
public class SimulatorService extends ScheduledService<Long> {
	private Simulator simulator;

	public SimulatorService(Simulator simulator) {
		this.simulator = simulator;
	}

	@Override
	protected Task<Long> createTask() {
		return new Task<>() {
			@Override
			protected Long call() throws Exception {
				return simulator.getSimulationTime().getMinutesPassed();
			}
		};
	}
}
