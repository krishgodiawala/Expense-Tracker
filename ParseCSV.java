import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class parses the csv and returns a list of the transactions
 * 
 * @author krishgodiawala
 *
 */
public class ParseCSV {
	private final static String DELIMITER = ",";

	public ParseCSV() {

	}

	/**
	 * Method to parse through the csv file and return a list of the
	 * transactions
	 * 
	 * @param file
	 *            the file to be parsed
	 * @return the list of transactions
	 */
	public ArrayList<Transaction> parse(String file) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		ArrayList<Category> categories = Helper.loadCategories();

		try {
			Scanner kbd = new Scanner(new File(file));
			while (kbd.hasNextLine()) {
				String tokens[] = kbd.nextLine().split(DELIMITER);

				transactions
						.add(new Transaction(tokens[0], tokens[1], Double
								.parseDouble(tokens[2]), categorize(categories,
								tokens)));
			}
			kbd.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return transactions;
	}

	/**
	 * This method categorizes the transaction
	 * 
	 * @param categories
	 *            The various categories
	 * @param tokens
	 *            The transaction split as tokens
	 */
	private String categorize(ArrayList<Category> categories, String tokens[]) {
		for (int j = 0; j < categories.size(); j++) {
			if (categories.get(j).membership(tokens[1]) == true)
				return categories.get(j).name;
		}
		return "miscellaneous";
	}
}
