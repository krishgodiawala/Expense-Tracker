import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a helper class
 * 
 * @author krishgodiawala
 *
 */
public class Helper {
	private final static String DELIMITER = ",";

	/**
	 * This method loads the categories from the txt file
	 * 
	 * @return
	 */
	public static ArrayList<Category> loadCategories() {
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			Scanner kbd = new Scanner(new File("categories.txt"));
			while (kbd.hasNextLine()) {
				String tokens[] = kbd.nextLine().split(DELIMITER);
				categories.add(new Category(tokens));
			}
			kbd.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return categories;
	}

	/**
	 * Method rounds the double value to the number places requested
	 * 
	 * @param value
	 *            The value to be rounded
	 * @param places
	 *            The precision
	 * @return The rounded double
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
