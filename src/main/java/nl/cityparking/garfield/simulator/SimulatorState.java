package nl.cityparking.garfield.simulator;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.cityparking.garfield.simulator.economy.Report;

public class SimulatorState {
	private LongProperty simulatorMinutes = new SimpleLongProperty();
	private LongProperty carsTotalIn = new SimpleLongProperty();
	private LongProperty carsTotalOut = new SimpleLongProperty();
	private ObservableList<Report> reports = FXCollections.observableArrayList();

	public long getSimulatorMinutes() {
		return simulatorMinutes.get();
	}

	public void setSimulatorMinutes(long m) {
		simulatorMinutes.set(m);
	}

	public LongProperty getSimulatorMinutesProperty() {
		return simulatorMinutes;
	}

	public long getCarsTotalIn() {
		return carsTotalIn.get();
	}

	public LongProperty carsTotalInProperty() {
		return carsTotalIn;
	}

	public void setCarsTotalIn(long carsTotalIn) {
		this.carsTotalIn.set(carsTotalIn);
	}

	public long getCarsTotalOut() {
		return carsTotalOut.get();
	}

	public LongProperty carsTotalOutProperty() {
		return carsTotalOut;
	}

	public void setCarsTotalOut(long carsTotalOut) {
		this.carsTotalOut.set(carsTotalOut);
	}

	public ObservableList<Report> getReports() {
		return reports;
	}
}
