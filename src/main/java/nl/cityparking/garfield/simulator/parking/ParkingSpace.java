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

	/**
	 * Checks if the ParkingSpace is occupied or not.
	 *
	 * @return true if occupied, false if available.
	 */
	public boolean isOccupied() {
		return occupant != null;
	}

	/**
	 * Sets the occupant of this ParkingSpace.
	 *
	 * @param agent The Agent to put on this spot.
	 * @param until Set until how long the ParkingSpace is reserved for this occupant.
	 */
	public void setOccupant(Agent agent, long until) {
		this.occupiedUntil = until;
		this.occupant = agent;
	}

	/**
	 * Removes the Agent from this ParkingSpace, freeing it up for another Agent to occupy.
	 * @return The Agent that occupied this space.
	 */
	public Agent free() {
		Agent formerOccupant = this.occupant;
		this.occupiedUntil = -1;
		this.occupant = null;
		return formerOccupant;
	}

	/**
	 * Gets the current occupying Agent.
	 *
	 * @return The occupying Agent, or null if the spot is free.
	 */
	public Agent getOccupant() {
		return occupant;
	}

	/**
	 * Returns when this spot will free up.
	 *
	 * @return The time in simulation minutes when this spot frees up.
	 */
	public long getOccupiedUntil() {
		return occupiedUntil;
	}

	/**
	 * Returns the type of this parking space.
	 *
	 * @return The ParkingSpaceType specifying the type of this ParkingSpace.
	 */
	public ParkingSpaceType getSpaceType() {
		return spaceType;
	}
}
