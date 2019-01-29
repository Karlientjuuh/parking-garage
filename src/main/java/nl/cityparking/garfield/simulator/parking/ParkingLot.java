package nl.cityparking.garfield.simulator.parking;

import nl.cityparking.garfield.simulator.Arrival;
import nl.cityparking.garfield.simulator.Departure;
import nl.cityparking.garfield.simulator.agent.Agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * An abstraction of a physical parking lot. A ParkingLot consists of multiple ParkingSpaces.
 *
 * @author Jesse
 * @since 1.0
 */
public class ParkingLot {
	private ArrayList<ParkingSpace> spaces = new ArrayList<>();
	private TreeSet<Integer> freeSpaces = new TreeSet<>();

	private int rows = 2;

	/**
	 * Initializes a new ParkingLot. Supply zero to the spaces argument creates a lot with no spaces.
	 *
	 * @param spaces The amount of spaces to create. Spaces must be divisable by two
	 */
	public ParkingLot(int spaces) {
		this(spaces, 2);
	}

	public ParkingLot(int spaces, int rows) {
		this.rows = rows;
		assert (spaces % rows == 0);
		resize(spaces);
	}

	/**
	 * Resizes the parking lot, adding or removing new parking spaces. This
	 * method will fail if the size of the lot is reduced, while there are
	 * still occupied spots within the range that is being resized.
	 * @param size The desired, new size of the parking lot.
	 */
	public void resize(int size) {
		if (size > spaces.size()) {
			for (int i = spaces.size(); i < size; i++) {
				this.spaces.add(new ParkingSpace());
				this.freeSpaces.add(i);
			}
		} else if (getLastOccupiedSpace() < size) {
			// These spaces are guaranteed to be free.
			for (int i = size; i < spaces.size(); i++) {
				this.freeSpaces.remove(i);
			}

			spaces.subList(size, spaces.size()).clear();
		}
	}

	/**
	 * Gets the index of the last parking spot with an occupant in it.
	 * @return The index of the occupied space, -1 if no spaces are occupied.
	 */
	public int getLastOccupiedSpace() {
		for (int i = spaces.size() - 1; i >= 0; i--) {
			ParkingSpace space = spaces.get(i);

			if (space.isOccupied()) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the first free parking space.
	 * @return A ParkingSpace instance that is unoccupied, or null if all spaces are occupied.
	 */
	private synchronized int popFirstFreeSpaceIndex() {
		while (freeSpaces.size() > 0) {
			int freeIndex = freeSpaces.first();
			ParkingSpace space = spaces.get(freeIndex);

			if (space.isOccupied()) {
				// This is odd, but we can correct our mistake here.
				// TODO: Maybe throw an exception instead?
				freeSpaces.remove(freeIndex);
			} else {
				freeSpaces.remove(freeIndex);
				return freeIndex;
			}
		}

		return -1;
	}

	/**
	 * Processes an arrival and parks it into this spot.
	 * @param arrival The Arrival to park.
	 */
	public synchronized void parkArrival(Arrival arrival) {
		int index = popFirstFreeSpaceIndex();
		spaces.get(index).setOccupant(arrival.agent, arrival.arrivalMinute, arrival.departureMinute);
	}

	/**
	 * Frees the given space, registering it as available for parking.
	 * @param space The ParkingSpace to free up.
	 * @return The Agent as a Departure.
	 */
	public synchronized Departure freeSpace(ParkingSpace space) {
		int index = spaces.indexOf(space);
		freeSpaces.add(index);

		Departure departure = new Departure(space.getOccupant(), space.getOccupiedUntil() - space.getOccupiedOn(), 0);
		space.free();

		return departure;
	}

	/**
	 * Gets the amount of ParkingSpaces contained within this ParkingLot
	 * @return The amount of parking spaces.
	 */
	public int getSize() {
		return spaces.size();
	}

	/**
	 * Gets the amount of ParkingSpaces that are occupied within this ParkingLot
	 * @return The amount of occupied spaces.
	 */
	public int getAmountOfOccupants() {
		return spaces.size() - freeSpaces.size();
	}

	/**
	 * Gets the amount of empty ParkingSpaces in this ParkingLot.
	 * @return The amount of free spaces.
	 */
	public int getAmountOfFreeSpaces() {
		return freeSpaces.size();
	}

	/**
	 * The amount of rows in this ParkingSpace.
	 * @return The amount of rows.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the amount of rows. The amount of spaces must be divisible by rows. If this is not the case, the value
	 * will not change.
	 * @param rows The amount of rows to divide the parking spaces by.
	 * @return true if successful, false if the amount of spaces is not divisible by rows.
	 */
	public boolean setRows(int rows) {
		if (getSize() % rows == 0) {
			this.rows = rows;
			return true;
		}

		return false;
	}

	/**
	 * Returns all parking spaces in this lot.
	 * @return A Collection of ParkingSpaces.
	 */
	public Collection<ParkingSpace> getSpaces() {
		return spaces;
	}
}
