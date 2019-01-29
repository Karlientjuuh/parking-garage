package nl.cityparking.garfield.gui;

import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import nl.cityparking.garfield.simulator.economy.Report;

import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * Creates the economic overview part of the interface.
 * @author Allard, Karlien
 */
public class EconomicViewController {
	public LineChart<String, Number> incomeChart;
	public TextField inputField;
	public Button confirmButton;
	public TableView<Report> overView;

	private LineChart.Series<String, Number> incomeSeries = new LineChart.Series<>();
	private LineChart.Series<String, Number> expensesSeries = new LineChart.Series<>();
	private LineChart.Series<String, Number> totalSeries = new LineChart.Series<>();

	private LongProperty carsIn = new SimpleLongProperty();
	private LongProperty carsOut = new SimpleLongProperty();
	private ObservableList<Report> data;

	private final static Callback<TableColumn<Report, Number>, TableCell<Report, Number>> currencyFormatFactory = tc -> new TableCell<>() {
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

	/**
	 * Initializes the economic overview.
	 * Sets the standard data in the graph and table.
	 */
	@FXML
	private void initialize() {
		TableColumn<Report, Number> total = new TableColumn<>("Total");
		TableColumn<Report, LocalDate> date = new TableColumn<>("Date");
		TableColumn<Report, Number> expense = new TableColumn<>("Expenses");
		TableColumn<Report, Number> income = new TableColumn<>("Income");

		overView.getColumns().add(date);
		overView.getColumns().add(expense);
		overView.getColumns().add(income);
		overView.getColumns().add(total);

		total.setCellFactory(currencyFormatFactory);
		income.setCellFactory(currencyFormatFactory);
		expense.setCellFactory(currencyFormatFactory);
		total.setCellValueFactory(new PropertyValueFactory<>("total"));
		income.setCellValueFactory(new PropertyValueFactory<>("income"));
		expense.setCellValueFactory(new PropertyValueFactory<>("expenses"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));

		incomeSeries.setName("Income");
		expensesSeries.setName("Expenses");
		totalSeries.setName("Total");
		incomeChart.getData().add(incomeSeries);
		incomeChart.getData().add(expensesSeries);
		incomeChart.getData().add(totalSeries);
	}

	/**
	 * @return The amount of cars that get in to the garage
	 */
	public long getCarsIn() {
		return carsIn.get();
	}

	/**
	 * @return The amount of cars that get in to the garage
	 */
	public LongProperty carsInProperty() {
		return carsIn;
	}

	/**
	 * Sets the amount of cars that get into the garage
	 * @param carsIn
	 */
	public void setCarsIn(long carsIn) {
		this.carsIn.set(carsIn);
	}

	/**
	 * @return The amount of cars that get out of the garage
	 */
	public long getCarsOut() {
		return carsOut.get();
	}

	public LongProperty carsOutProperty() {
		return carsOut;
	}

	/**
	 * Sets the amount of cars that get out of the garage
	 * @param carsOut
	 */
	public void setCarsOut(long carsOut) {
		this.carsOut.set(carsOut);
	}

	/**
	 * @return The data stored in the list: Report
	 */
	public ObservableList<Report> getData() {
		return data;
	}

	/**
	 * Sets the data into the Report list. This function listens to the list to see if there has changed anything, if yes, it will update the view of economics.
	 * @param data
	 */
	public void setData(ObservableList<Report> data) {
		this.data = data;
		overView.setItems(this.data);

		// Empty out the series so we can populate it with new data.
		incomeSeries.getData().removeAll(incomeSeries.getData());
		expensesSeries.getData().removeAll(expensesSeries.getData());
		totalSeries.getData().removeAll(totalSeries.getData());

		this.data.addListener((ListChangeListener<? super Report>) observable -> {
			while (observable.next()) {
				Report report = data.get(observable.getFrom());

				incomeSeries.getData().add(new XYChart.Data<>(report.getDate().toString(), report.getIncome()));
				expensesSeries.getData().add(new XYChart.Data<>(report.getDate().toString(), report.getExpenses()));
				totalSeries.getData().add(new XYChart.Data<>(report.getDate().toString(), report.getTotal()));
			}
		});
	}
}
