package fxlauncher;

import java.io.File;

import postalcode.PostalCode;
import postalcode.PostalCodeIndex;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FXLauncher extends Application {
	PostalCodeIndex postalCodeIndex;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// labels for the tables
		Label label1 = new Label("Sorted by Code");
		Label label2 = new Label("Sorted by City      ");
		Label label3 = new Label("Sorted by Latitude");
		Label label4 = new Label("Sorted by Longitude");

		// creates a vBox and two hBoxes
		VBox vbox = new VBox();
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();

		primaryStage.setTitle("Postal Code Analyzer"); // sets the title of the window.
		postalCodeIndex = new PostalCodeIndex();// creates PostalCodeIndex object
		postalCodeIndex.loadSort(new File("postal_codes.csv"));// calls loadSort

		// creates the 4 ListViews, and sets their width to be 365px, which is just wide enough to fully display the longest code.
 		ListView<PostalCode> codeOrderListView = new ListView<>(postalCodeIndex.getCodeOrderList());
		codeOrderListView.setPrefWidth(365.0);

		ListView<PostalCode> cityOrderListView = new ListView<>(postalCodeIndex.getCityOrderList());
		cityOrderListView.setPrefWidth(365.0);

		ListView<PostalCode> latitudeOrderListView = new ListView<>(postalCodeIndex.getLatitudeOrderList());
		latitudeOrderListView.setPrefWidth(365.0);

		ListView<PostalCode> longitudeOrderListView = new ListView<>(postalCodeIndex.getLongitudeOrderList());
		longitudeOrderListView.setPrefWidth(365.0);

		hbox1.getChildren().addAll(label1, label2, label3, label4);// adds all 4 header lables to the first hBox
		hbox1.setSpacing(270);// sets the spacing between the labels
		hbox2.getChildren().addAll(codeOrderListView, cityOrderListView, latitudeOrderListView, longitudeOrderListView);// adds all 4 tables to the second hBox
		vbox.getChildren().addAll(hbox1, hbox2);// adds both hBoxes into the single vBox, so that the labels will be vertically above the tables.
		Scene scene = new Scene(vbox);// creates the scene on the vBox

		primaryStage.setScene(scene);// sets the scene

		primaryStage.show();// and sets the stage and shows the window.
	}//end method start
}//end class FXLauncher