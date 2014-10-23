package postalcode;

import java.util.Comparator;
import java.util.Scanner;

public class PostalCode {
	private String code;
	private String city;
	private String province;
	private double latitude;
	private double longitude;

	public PostalCode(Scanner input) {
		input.nextInt(); // Tosses the line count
		code = input.next();
		city = input.next();
		province = input.next();
		input.next(); // Tosses provincial abbreviation
		input.next(); // Tosses the "D"
		latitude = input.nextDouble();
		longitude = input.nextDouble();
	}// end constructor

	// The following 4 classes are Comparator classes meant to organize the orderLists by their own unique variables.
	public static class CompareCode implements Comparator<PostalCode> {
		public final static CompareCode instance = new CompareCode();

		private CompareCode() {}//private prevents creation of a CompareCode object outside of this class

		// compares code strings, returns the comparison in a positive value (ob1 is greater than ob2), a negative value (ob2 is greater than ob1), or 0 (ob1 == ob2)
		@Override
		public int compare(PostalCode code1, PostalCode code2) {
			return code1.code.compareTo(code2.code);
		}// end compare
	}// end CompareCode

	public static class CompareCity implements Comparator<PostalCode> {
		public final static CompareCity instance = new CompareCity();

		private CompareCity() {}

		@Override
		public int compare(PostalCode city1, PostalCode city2) {
			return city1.city.compareTo(city2.city);
		}// end compare
	}// end CompareCity

	public static class CompareLatitude implements Comparator<PostalCode> {
		public final static CompareLatitude instance = new CompareLatitude();

		private CompareLatitude() {}

		// Since lat and long are both doubles, we can't use compareTo() for them, and have to resort to basic >, < operators to return values.
		@Override
		public int compare(PostalCode latitude1, PostalCode latitude2) {
			if (latitude1.latitude > latitude2.latitude)
				return 1;// if the first value is greater, compare needs a positive int.
			else if (latitude1.latitude < latitude2.latitude)
				return -1;// if the second value is greater, compare needs a negative int
			else
				return 0;// in rare cases when values are equal, compare needs a 0. This is left last as it is least likely to happen.
		}// end compare
	}// end CompareLatitude

	public static class CompareLongitude implements Comparator<PostalCode> {
		public final static CompareLongitude instance = new CompareLongitude();

		private CompareLongitude() {}

		@Override
		public int compare(PostalCode longitude1, PostalCode longitude2) {
			if (longitude1.longitude > longitude2.longitude)
				return 1;
			else if (longitude1.longitude < longitude2.longitude)
				return -1;
			else
				return 0;
		}// end compare
	}// end CompareLatitude

	// getter used only in jUnit testing to compare variables
	public String getCity() {
		return city;
	}// end getCtiy getter

	// getter used only in jUnit testing to compare variables
	public String getCode() {
		return code;
	}// end getCode getter

	// getter used only in jUnit testing to compare variables
	public double getLatitude() {
		return latitude;
	}// end getLatitude getter

	// getter used only in jUnit testing to compare variables
	public double getLongitude() {
		return longitude;
	}// end getLongitude getter

	@Override
	public String toString() {
		return String.format("%s %s %s %.3f %.3f", code, city, province, latitude, longitude);
	}// end toString
}// end Postal Code
