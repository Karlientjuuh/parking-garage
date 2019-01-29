package nl.cityparking.garfield;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import java.time.LocalDate;

public class TestModelEconomic {
	private LongProperty income = new SimpleLongProperty();
	private LongProperty expense = new SimpleLongProperty();
	private LocalDate date;

	public TestModelEconomic(LocalDate date){
		this.date = date;
	}

	public LocalDate getDate(){
		return date;
	}

	public  long getTotal() {
		return income.get() - expense.get();
	}

	public long getIncome() {
		return income.get();
	}

	public LongProperty incomeProperty() {
		return income;
	}

	public void setIncome(long income) {
		this.income.set(income);
	}

	public long getExpense() {
		return expense.get();
	}

	public LongProperty expenseProperty() {
		return expense;
	}

	public void setExpense(long expense) {
		this.expense.set(expense);
	}


}
