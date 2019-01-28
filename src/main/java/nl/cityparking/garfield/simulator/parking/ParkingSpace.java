package nl.cityparking.garfield.simulator.parking;

import nl.cityparking.garfield.simulator.agent.Agent;

/**
 * Abstraction of a parking space in the garage.
 *
 * @author Jesse
 * @since 1.0
 */
public class ParkingSpace {
	private Agent occupant = null;
	private long occupiedUntil = -1;
	private ParkingSpaceType spaceType = ParkingSpaceType.OPEN;

	public boolean isOccupied() {
		return occupant != null;
	}

	public void setOccupant(Agent agent, long until) {
		this.occupiedUntil = until;
		this.occupant = agent;
	}

	public Agent free() {
		Agent formerOccupant = this.occupant;
		this.occupiedUntil = -1;
		this.occupant = null;
		return formerOccupant;
	}

	public Agent getOccupant() {
		return occupant;
	}

	public long getOccupiedUntil() {
		return occupiedUntil;
	}

	public ParkingSpaceType getSpaceType() {
		return spaceType;
	}
}
