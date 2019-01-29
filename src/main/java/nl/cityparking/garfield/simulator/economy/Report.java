package nl.cityparking.garfield.simulator.economy;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

public class Report {
	private long startTime;
	private long endTime;
	private long income;
	private long expenses;
	private boolean finalized = false;

	public Report(long currentTime) {
		startTime = currentTime;
	}

	public void close(long currenTime) {
		this.expenses = ThreadLocalRandom.current().nextLong(4000, 6000);

		endTime = currenTime;
		finalized = true;
	}

	public void addExpense(long expense) {
		if (!finalized) {
			expenses += expense;
		}
	}

	public void addPayment(long payment) {
		if (!finalized) {
			income += payment;
		}
	}

	public LocalDate getDate() {
		return LocalDate
				.of(1990, Month.JANUARY, 1)
				.plusDays((endTime) / (60 * 24));
	}

	public long getStartTime() {
		return startTime;
	}

	public long getIncome() {
		return income;
	}

	public long getExpenses() {
		return expenses;
	}

	public long getTotal() {
		return income - expenses;
	}

	public long getEndTime() {
		return endTime;
	}
}
