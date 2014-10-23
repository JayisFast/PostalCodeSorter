package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import org.junit.*;
import static org.junit.Assert.*;
import postalcode.*;

/**
 * jUnit class to support testing of classes PostalCode and PostalCodeIndex Partially complete . . . to be completed by students in CST8130
 *
 */
public class JUnitPostalCodeLoadSortTest {
	private static PostalCodeIndex postalCodeIndex;
	private static long originalChecksum;

	public JUnitPostalCodeLoadSortTest() {
	}

	@BeforeClass
	public static void setUpClass() throws FileNotFoundException {
		postalCodeIndex = new PostalCodeIndex();
		//postalCodeIndex.loadSort(new File("postal_codes_short.csv"));
		postalCodeIndex.loadSort(new File("postal_codes.csv"));
		originalChecksum = calculateChecksum(postalCodeIndex.getCodeOrderList());
	}

	@Ignore
	@AfterClass
	// performed once-and-only once after the test class is instantiated and after all test routines have terminated
	public static void tearDownClass() {
	}

	@Ignore
	@Before
	// performed before each test
	public void setUp() {
	}

	@Ignore
	@After
	// performed after each test
	public void tearDown() {
	}

	@Test
	// Ensures that cityOrderList is being sorted correctly, without any data loss.
	public void sortCityTest() {
		int check; // temporarily holds the value of the comparison between current and prior cities.

		PostalCode previous = null;
		for (PostalCode current : postalCodeIndex.getCityOrderList()) {
			if (previous == null) {
				previous = current; // gets the loop rolling. Sets current as previous, and then current changes to the next object in cityOrderList, while previous retains the old value.
				continue;
			}// end if
			else {
				check = current.getCity().compareTo(previous.getCity()); // compares the two cities, then assigns the difference to check.
				assertTrue(check >= 0);// asserts that each current is greater than previous, through the check
				previous = current;// sets the current to replace the previous, and begins the loop with the next object.
			}// end else
		}// end for



		assertEquals(calculateChecksum(postalCodeIndex.getCityOrderList()), originalChecksum);// asserts that the checksum of the sorted cityOrderList is the same as the unsorted codeOrderList.

	} // end sortCityTest()

	@Test
	// this case is virtually identical except for a few neccessary changes.
	public void sortLatitudeTest() {

		PostalCode previous = null;
		for (PostalCode current : postalCodeIndex.getLatitudeOrderList()) {
			if (previous == null) {
				previous = current;
				continue;
			}// end if
			else {
				assertTrue(current.getLatitude() >= previous.getLatitude());// since lat and long are both doubles, we can compare them easier with a single assert statement, with no need for a check
																																		// variable.
				previous = current;
			}// end else
		}// end for

		assertEquals(calculateChecksum(postalCodeIndex.getLatitudeOrderList()), originalChecksum);// asserts that the checksum of the sorted cityOrderList is the same as the unsorted codeOrderList.


	}// end sortLatitudeOrderTest

	// identical to sortLatitudeTest(), only comparing Longitude.
	@Test
	public void sortLongitudeTest() {

		PostalCode previous = null;
		for (PostalCode current : postalCodeIndex.getLongitudeOrderList()) {
			if (previous == null) {
				previous = current;
				continue;
			}// end if
			else {
				assertTrue(current.getLongitude() >= previous.getLongitude());
				previous = current;
			}// end else
		}// end for

		assertEquals(calculateChecksum(postalCodeIndex.getLongitudeOrderList()), originalChecksum);// asserts that the checksum of the sorted cityOrderList is the same as the unsorted codeOrderList.


	}// end sortLatitudeOrderTest

	@Test
	// virtually identical to sortCityOrderTest()
	public void sortCodeOrderTest() {
		int check;

		PostalCode previous = null;
		for (PostalCode current : postalCodeIndex.getCodeOrderList()) {
			if (previous == null) {
				previous = current;
				continue;
			}// end if
			else {
				check = current.getCode().compareTo(previous.getCode());
				assertTrue(check >= 0);
				previous = current;
			}// end else
		}// end for

		assertEquals(calculateChecksum(postalCodeIndex.getCodeOrderList()), originalChecksum);// asserts that the checksum of the sorted cityOrderList is the same as the unsorted codeOrderList.


	}// end sortCodeOrderTest

	/**
	 * A static method that sweeps through the supplied list, performing a summation of the hashcode value for each item in the list. The final long-value captures a unique value for all the aggregated
	 * data. It can be used to compare with checksums for other lists. If the checksums match, then the data sets are deemed to contain the same collection of items (though in a different order). NOTE:
	 * This is NOT a jUnit @Test case. It is a helper method that is used by other parts of the jUnit class.
	 *
	 * @param list
	 *          The List can be a reference to any of the lists.
	 * @return
	 */
	private static long calculateChecksum(ObservableList<PostalCode> list) {
		long checksum = 0;

		for (PostalCode current : list) {
			checksum += current.hashCode();
		}

		return checksum;
	} // end calculateChecksum()
} // end class JUnitPostalCodeLoadSortTest