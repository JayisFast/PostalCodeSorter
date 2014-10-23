package postalcode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostalCodeIndex {
	// initializes all the lists, and named according to their eventual sorting method.
	private ObservableList<PostalCode> codeOrderList = FXCollections.observableList(new ArrayList<>(760000));
	private ObservableList<PostalCode> cityOrderList;
	private ObservableList<PostalCode> latitudeOrderList;
	private ObservableList<PostalCode> longitudeOrderList;
	
	//getters for each of the lists
	public ObservableList<PostalCode> getCodeOrderList() { return FXCollections.unmodifiableObservableList(codeOrderList); }
	public ObservableList<PostalCode> getCityOrderList() { return FXCollections.unmodifiableObservableList(cityOrderList); }
	public ObservableList<PostalCode> getLatitudeOrderList() { return FXCollections.unmodifiableObservableList(latitudeOrderList); } 
	public ObservableList<PostalCode> getLongitudeOrderList() { return FXCollections.unmodifiableObservableList(longitudeOrderList); } 
	
	// loadSort() is responsible for populating codeOrderList by the raw data, and correctly populating each variable for each PostalCode object.
	public void loadSort(File inputFile) {
		try (Scanner input = new Scanner(inputFile)) {
			input.useDelimiter("\\||\\r\\n"); // Delimiter to indicate what character each string ends with
			input.nextLine();// tosses the first line of useless header information
			while (input.hasNext())
				codeOrderList.add(new PostalCode(input)); // adds a reference-to the newly populated PostalCode object to codeOrderList
		}// end while
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}// end catch

		// the following three lines each populate the various orderLists with the unorganzed code just populated above.
		cityOrderList = FXCollections.observableArrayList(codeOrderList);
		latitudeOrderList = FXCollections.observableArrayList(codeOrderList);
		longitudeOrderList = FXCollections.observableArrayList(codeOrderList);

		// Finally, the unsorted collections each call their unique sorting class in PostalCode.class.
		Collections.sort(codeOrderList, PostalCode.CompareCode.instance);
		Collections.sort(cityOrderList, PostalCode.CompareCity.instance);
		Collections.sort(latitudeOrderList, PostalCode.CompareLatitude.instance);
		Collections.sort(longitudeOrderList, PostalCode.CompareLongitude.instance);
	}// end method load
}// end class PostalCodeIndex