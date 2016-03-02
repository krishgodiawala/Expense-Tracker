import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main class that handles the control flow of the program. This
 * class takes input from the user and produces the corresponding outputs.
 * 
 * @author Krish Godiawala
 *
 */

public class ExpenseTracker {
	private static final String input = "transactions-person-A.csv";

	public ExpenseTracker() {

	}

	/**
	 * The main method
	 * 
	 * @param args
	 *            the transaction csv file
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			usage(1);
		}
		ParseCSV parser = new ParseCSV();
		ArrayList<Transaction> trans;
		try {
			if (args.length != 1)
				trans = parser.parse(input);
			else
				trans = parser.parse(args[0]);
			InspectExpenses expenses = new InspectExpenses();
			expenses.yearlyMonthlyExpenses(trans);
			ExpenseTracker trackExpenses = new ExpenseTracker();
			trackExpenses.userInterface(expenses);

		} catch (Exception e) {
			usage(4);
		}

	}

	/**
	 * This method communicates with the user and takes user input.
	 * 
	 * @param expenses
	 *            Object of the InspectExpenses class so method calls can be
	 *            made
	 */
	private void userInterface(InspectExpenses expenses) {
		loop: while (true) {
			displayScreen();
			Scanner kbd = new Scanner(System.in);
			try {
				int input = kbd.nextInt();
				kbd.nextLine();
				kbd.reset();
				switch (input) {
				case 1:
					expenses.print();
					System.out.println();
					break;
				case 2:
					expenses.highestExpenseMonths();
					System.out.println();
					break;
				case 3:
					System.out
							.println("Enter the month and year of the suggestions you would like format MM YYYY eg: 01 2012");
					String tokens[] = kbd.nextLine().split(" ");
					expenses.suggestionsForMonth(tokens);
					break;
				case 4:

					System.out
							.println("Would you like detailed transactions which you could have avoided? press 1 if you do or 2 "
									+ "if you do not want them ");
					int in = kbd.nextInt();
					if (in == 1)
						expenses.suggestionsForMonth(true);
					else
						expenses.suggestionsForMonth(false);
					break;
				case 5:
					break loop;
				default:
					usage(2);
					break;
				}
				// System.out.println();
			} catch (Exception e) {
				usage(2);
			}
		}
	}

	/**
	 * This method display to the users the various options available.
	 */
	private void displayScreen() {
		System.out
				.println("Press 1 to get expenditure of all months categorically");
		System.out
				.println("Press 2 to get the months where your expenditure was above average and various categories");
		System.out
				.println("Press 3 to get suggestions on savings for a particular month");
		System.out
				.println("Press 4 to get suggestions on savings for all months");
		System.out.println("Press 5 to quit");
	}

	/**
	 * This method handles exceptions.
	 * 
	 * @param exceptionCode
	 *            The exception number
	 */
	private static void usage(int exceptionCode) {
		switch (exceptionCode) {
		case 1:
			System.out
					.println("Illegal arguments: file not selected loading an existing csv file (transactions-person-A.csv)");

		case 2:
			System.out
					.println("Illegal Input: The input can only be numeric between 1-4");
			break;
		case 3:
			System.out.println("IOException");
			break;
		case 4:
			System.out.println("File Not found Exception");
			break;
		}

	}

}
