package nl.cityparking.garfield.simulator.economy;

import java.util.ArrayList;
import java.util.Collection;

/**
 * class that determines all things concerning money & prices
 *
 * @author Els Boerema
 */
public class Economy {
	private static final long HOURLY_PRICE = 2;
	private static final long PASS_HOURLY_PRICE = 1;
	private static final long WEEKLY_PASS = 10;

	private long funds = 0;
	private Report dailyReport = new Report(0);
	private int currentIndex = 0;
	private ArrayList<Report> financialReports = new ArrayList<>();

	/**
	 * method to calculate the price to be paid by the agent
	 *
	 * @param minutes (which stands for the amount of minutes that an agent was parked
	 * @return ticket (which contains the price to be paid by the agent
	 */
	public long calculateTicket(long minutes) {
		long amountToPay = minutes/60 * HOURLY_PRICE;
		dailyReport.addPayment(amountToPay);
		return amountToPay;
	}

	/**
	 * Method to calculate the price to be paid by passholders
	 *
	 * @param minutes (the amount of minutes an agent was parked)
	 * @return ticket (price to be paid by passholder)
	 */
	public long calculatePassholderTicket(long minutes) {
		return minutes/60 * PASS_HOURLY_PRICE;
	}

	/**
	 * method to calculate the fine that has to be payed if the agent's passed his time
	 *
	 * @param minutes, defined by the currenttime-leavetime, defined in other classes
	 * @return integer of amount of euros that have to be payed by the agent
	 */
	public long calculateFine(long minutes) {
		return 10 + (minutes);
	}

	/**
	 * getter to return the price for a weekly pass
	 * @return int with the price for a weekly pass
	 */
	public long getWeeklyPass(){
		return WEEKLY_PASS;
	}

	public void finalizeReport(long currentTime) {
		dailyReport.close(currentTime);
		financialReports.add(dailyReport);
		dailyReport = new Report(currentTime);
	}

	public Collection<Report> getNewReports() {
		if (currentIndex < financialReports.size()) {
			Collection<Report> reports = financialReports.subList(currentIndex, financialReports.size());
			currentIndex = financialReports.size();
			return reports;
		}

		return new ArrayList<>();
	}
}
