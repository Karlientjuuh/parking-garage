package nl.cityparking.garfield.gui.simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import nl.cityparking.garfield.simulator.parking.ParkingFloor;
import nl.cityparking.garfield.simulator.parking.ParkingLot;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GarageView {
	@FXML
	private GridPane floorGrid;

	private List<ParkingFloor> floors = null;
	private ArrayList<ParkingLotController> controllers = new ArrayList<>();

	public void setFloors(List<ParkingFloor> floors) {
		this.floors = floors;

		// Make sure our grid is empty first!
		floorGrid.getRowConstraints().removeAll();
		floorGrid.getColumnConstraints().removeAll();
		floorGrid.getChildren().removeAll();

		for (int row = 0; row < floors.size(); row++) {
			ParkingFloor floor = floors.get(row);

			for (int column = 0; column < floor.getParkingLots().size(); column++) {
				ParkingLot lot = floor.getParkingLots().get(column);

				try {
					URL resource = this.getClass().getResource("/views/parkingLot.fxml");
					FXMLLoader loader = new FXMLLoader(resource);
					Node parkingLotView = loader.load();
					ParkingLotController controller = loader.getController();

					controller.setParkingLot(lot);
					controllers.add(controller);

					GridPane.setValignment(parkingLotView, VPos.CENTER);
					GridPane.setHalignment(parkingLotView, HPos.CENTER);
					floorGrid.add(parkingLotView, column, row);

					// Make sure it is positioned properly.
					if (column + 1 > floorGrid.getColumnConstraints().size()) {
						ColumnConstraints columnConstraints = new ColumnConstraints();
						columnConstraints.setHalignment(HPos.CENTER);
						columnConstraints.setHgrow(Priority.ALWAYS);
						floorGrid.getColumnConstraints().add(columnConstraints);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setValignment(VPos.CENTER);
			rowConstraints.setVgrow(Priority.ALWAYS);
			floorGrid.getRowConstraints().add(rowConstraints);
		}
	}

	public void update() {
		for (ParkingLotController controller: controllers) {
			controller.updateCanvas();
		}
	}
}
