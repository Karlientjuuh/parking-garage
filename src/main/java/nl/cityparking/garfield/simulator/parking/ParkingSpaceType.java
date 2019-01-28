package nl.cityparking.garfield.simulator.parking;

/**
 * Enumeration of the types that a ParkingSpace can be.
 *
 * @author Jesse
 * @since 1.0
 * @see ParkingSpace
 */
public enum ParkingSpaceType {
	/**
	 * Specifies a ParkingSpace that is open to all.
	 */
	OPEN,

	/**
	 * Specifies a ParkingSpace that is open to pass holders only.
	 */
	PASS_HOLDER_ONLY,

	/**
	 * Specifies a ParkingSpace that is open to disabled Agents only.
	 */
	DISABLED_ONLY
}
