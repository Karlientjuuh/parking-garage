package nl.cityparking.garfield.simulator.parking;

import nl.cityparking.garfield.simulator.Arrival;

import java.util.ArrayList;

/**
 * Abstraction of a garage's floor, which is divided into parking lots with spaces.
 *
 * @author Jesse
 * @since 1.0
 */
public class ParkingFloor {
	private ArrayList<ParkingLot> parkingLots = new ArrayList<>();

	/**
	 * Creates a new ParkingFloor. Setting count to zero will initialize an empty floor.
	 *
	 * @param count The amount of lots.
	 * @param lotSize The size of each lot.
	 */
	public ParkingFloor(int count, int lotSize) {
		addLots(count, lotSize);
	}

	/**
	 * Adds new lots to the ParkingFloor. Supplying zero as the lotSize creates an empty lot.
	 *
	 * @param count The amount of lots to add.
	 * @param lotSize The size of each lot.
	 */
	public void addLots(int count, int lotSize) {
		for (int i = 0; i < count; i++) {
			parkingLots.add(new ParkingLot(lotSize));
		}
	}

	/**
	 * Retrieves the ParkingLots associated with this floor.
	 * @return The ParkingLots on this floor.
	 */
	public ArrayList<ParkingLot> getParkingLots() {
		return parkingLots;
	}

	/**
	 * Processes an Arrival and parks the contained Agent into a ParkingSpace.
	 * @param arrival The Arrival to process.
	 */
	public void parkArrival(Arrival arrival) {
		for (ParkingLot lot: parkingLots) {
			if (lot.getAmountOfFreeSpaces() > 0) {
				lot.parkArrival(arrival);
				return;
			}
		}
	}

	/**
	 * Returns the amount of spaces that are unoccupied on this floor.
	 *
	 * @return The amount of empty spaces across all lots.
	 */
	public long getFreeSpots() {
		return parkingLots.stream()
				.mapToLong(ParkingLot::getAmountOfFreeSpaces)
				.sum();
	}
}
