package nl.cityparking.garfield.gui;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import nl.cityparking.garfield.TestModelEconomic;

import java.text.NumberFormat;
import java.time.LocalDate;

public class EconomicViewController {
	public LineChart<Number, Number> incomeChart;
	public TextField inputField;
	public Button confirmButton;
	public TableView<TestModelEconomic> overView;

	private LongProperty minutes = new SimpleLongProperty();
	private LongProperty carsIn = new SimpleLongProperty();
	private LongProperty carsOut = new SimpleLongProperty();
	private ObservableList<TestModelEconomic> data;

	private final static Callback<TableColumn<TestModelEconomic, Number>, TableCell<TestModelEconomic, Number>> currencyFormatFactory = tc -> new TableCell<>() {
		@Override
		protected void updateItem(Number value, boolean empty) {
			super.updateItem(value, empty);

			if (value == null || empty) {
				setText("");
			} else {
				setText(NumberFormat.getCurrencyInstance().format(value));
			}
		}
	};


	@FXML
	private void initialize() {
		TableColumn<TestModelEconomic, Number> total = new TableColumn<>("Total");
		TableColumn<TestModelEconomic, LocalDate> date = new TableColumn<>("Date");
		TableColumn<TestModelEconomic, Number> expense = new TableColumn<>("Expense");
		TableColumn<TestModelEconomic, Number> income = new TableColumn<>("Income");
		overView.getColumns().add(date);
		overView.getColumns().add(expense);
		overView.getColumns().add(income);
		overView.getColumns().add(total);

		data = FXCollections.observableArrayList(
				new TestModelEconomic(),
				new TestModelEconomic(),
				new TestModelEconomic(),
				new TestModelEconomic(),
				new TestModelEconomic(),
				new TestModelEconomic(),
				new TestModelEconomic(),
				new TestModelEconomic(),
				new TestModelEconomic()
		);


		total.setCellValueFactory(new PropertyValueFactory<>("total"));

		total.setCellFactory(currencyFormatFactory);
		income.setCellFactory(currencyFormatFactory);
		expense.setCellFactory(currencyFormatFactory);
		income.setCellValueFactory(new PropertyValueFactory<>("income"));
		expense.setCellValueFactory(new PropertyValueFactory<>("expense"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));

		overView.setItems(data);

		data.get(0).setIncome(100);
		data.get(1).setIncome(50);
		data.get(2).setIncome(475);
		data.get(3).setIncome(178);
		data.get(4).setIncome(5);
		data.get(0).setExpense(433);
		data.get(1).setExpense(987);
		data.get(2).setExpense(45);
		data.get(3).setExpense(17);




	}
	public long getMinutes() {
		return minutes.get();
	}

	public LongProperty minutesProperty() {
		return minutes;
	}

	public void setMinutes(long minutes) {
		this.minutes.set(minutes);
	}

	public long getCarsIn() {
		return carsIn.get();
	}

	public LongProperty carsInProperty() {
		return carsIn;
	}

	public void setCarsIn(long carsIn) {
		this.carsIn.set(carsIn);
	}

	public long getCarsOut() {
		return carsOut.get();
	}

	public LongProperty carsOutProperty() {
		return carsOut;
	}

	public void setCarsOut(long carsOut) {
		this.carsOut.set(carsOut);
	}

	public ObservableList<TestModelEconomic> getData() {
		return data;
	}

	public void setData(ObservableList<TestModelEconomic> data) {
		this.data = data;
	}
}
