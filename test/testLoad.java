package test;
import java.io.File;

import postalcode.PostalCodeIndex;

public class testLoad {

	public static void main(String[] args) {
		PostalCodeIndex postalCodeIndex = new PostalCodeIndex();
		postalCodeIndex.loadSort(new File("postal_codes.csv"));
		System.out.println("Done");
	}

}
