import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains the main algorithm to inspect the expenses and return
 * results
 * 
 * @author krishgodiawala
 *
 */

public class InspectExpenses {

	ArrayList<MonthlyExpense> monthWiseExpenditure;

	public InspectExpenses() {
		monthWiseExpenditure = new ArrayList<MonthlyExpense>();
	}

	/**
	 * This class stores the expenses into the MonthlyExpense object which is
	 * used by the algorithm to make recommendations.
	 * 
	 * @param transaction
	 *            the transactions to store
	 */
	public void yearlyMonthlyExpenses(ArrayList<Transaction> transaction) {

		for (int i = 0; i < transaction.size(); i++) {
			int index = monthWiseExpenditure
					.indexOf(new MonthlyExpense(transaction.get(i).getMonth(),
							transaction.get(i).getYear()));
			if (index >= 0) {
				MonthlyExpense monthlyExpense = monthWiseExpenditure.get(index);
				monthlyExpense.updateExpense(transaction.get(i));
			} else {
				monthWiseExpenditure
						.add(new MonthlyExpense(transaction.get(i)));
			}
		}
		Collections.sort(monthWiseExpenditure);

	}

	/**
	 * This method makes recommendations for a particular month
	 * 
	 * @param tokens
	 *            the months to make recommendation for
	 */
	public void suggestionsForMonth(String tokens[]) {
		String month = tokens[0], year = tokens[1];
		int index = monthWiseExpenditure
				.indexOf(new MonthlyExpense(month, year));
		if (index >= 0) {
			MonthlyExpense monthExpense = monthWiseExpenditure.get(index);
			suggestionsForMonth(monthExpense, true);
		} else
			System.out
					.println("Either such a transaction does not exist or incorrect format give input as 'month year' (11 2012)");
	}

	/**
	 * This method makes suggestions for all months
	 */
	public void suggestionsForMonth(Boolean bool) {
		for (int i = 0; i < monthWiseExpenditure.size(); i++) {
			suggestionsForMonth(monthWiseExpenditure.get(i), bool);
			System.out
					.println("****---------------*****---------------*****---------------*****---------------*****---------------*****");
		}
	}

	/**
	 * This is method performs the various checks so that suggestions for months
	 * can be made
	 * 
	 * @param monthExpense
	 *            The month to make recommendation for
	 * @param bool
	 *            Whether or not give detailed recommendation
	 */
	private void suggestionsForMonth(MonthlyExpense monthExpense, boolean bool) {
		System.out.println("Month = " + monthExpense.month + " Year = "
				+ monthExpense.year);
		double savings = 0;
		savings += taxiCheck(monthExpense, bool);
		savings += onlineEntertainment(monthExpense, bool);
		savings += dinning(monthExpense, bool);
		savings += late(monthExpense, bool);
		savings += shopping(monthExpense, bool);
		printMessage(
				"You could have totally saved $" + Helper.round(savings, 2)
						+ " this month", null);
		System.out.println();
	}

	/**
	 * Check to save on shopping expenses
	 * 
	 * @param monthExpense
	 *            The month to make recommendation for
	 * @param bool
	 *            Whether or not give detailed recommendation
	 */
	private double shopping(MonthlyExpense monthExpense, boolean bool) {
		int index = monthExpense.getCategoricalIndex(monthExpense,
				Keywords.shopping);

		if (index >= 0) {
			String message = "You could have avoided the total of $"
					+ Helper.round(
							monthExpense.categoricalSpending.get(index).totalAmount,
							2) + " if you did not shop in the month.";
			if (bool == true)
				printMessage(message,
						monthExpense.categoricalSpending.get(index).transaction);
			else
				printMessage(message, null);
			return monthExpense.categoricalSpending.get(index).totalAmount;
		}
		return 0;
	}

	/**
	 * Check to save on late payment expenses
	 * 
	 * @param monthExpense
	 *            The month to make recommendation for
	 * @param bool
	 *            Whether or not give detailed recommendation
	 */
	private double late(MonthlyExpense monthExpense, boolean bool) {
		int index = monthExpense.getCategoricalIndex(monthExpense,
				Keywords.late);
		if (index >= 0) {
			String message = "You could have avoided the total of $"
					+ Helper.round(
							monthExpense.categoricalSpending.get(index).totalAmount,
							2) + " if you paid your bills on time.";
			if (bool == true)
				printMessage(message,
						monthExpense.categoricalSpending.get(index).transaction);
			else
				printMessage(message, null);
			return monthExpense.categoricalSpending.get(index).totalAmount;
		}
		return 0;
	}

	/**
	 * Check to save on dining expenses
	 * 
	 * @param monthExpense
	 *            The month to make recommendation for
	 * 
	 * @param bool
	 *            Whether or not give detailed recommendation
	 */
	private double dinning(MonthlyExpense monthExpense, boolean bool) {
		int index = monthExpense.getCategoricalIndex(monthExpense,
				Keywords.dinning);
		if (index >= 0) {
			String message = "You could have avoided the total of $"
					+ Helper.round(
							monthExpense.categoricalSpending.get(index).totalAmount,
							2) + " of dining outside.";
			if (bool == true)
				printMessage(message,
						monthExpense.categoricalSpending.get(index).transaction);
			else
				printMessage(message, null);
			return monthExpense.categoricalSpending.get(index).totalAmount;
		}
		return 0;
	}

	/**
	 * Prints the message
	 * 
	 * @param message
	 *            the message to be printed
	 * @param transaction
	 *            the detailed transactions to be printed
	 */
	private void printMessage(String message, ArrayList<String> transaction) {
		System.out.println(message);

		if (transaction != null) {
			System.out.println("detailed transactions are as follows");
			for (int i = 0; i < transaction.size(); i++)
				System.out.println(transaction.get(i));
			System.out.println();
		}
	}

	/**
	 * Check to save on online entertainment expenses
	 * 
	 * @param monthExpense
	 *            The month to make recommendation for
	 * @param bool
	 *            Whether or not give detailed recommendation
	 */
	private double onlineEntertainment(MonthlyExpense monthExpense, boolean bool) {
		int index = monthExpense.getCategoricalIndex(monthExpense,
				Keywords.onlineEntertainment);
		if (index >= 0) {
			String message = "You could have avoided the total of $"
					+ Helper.round(
							monthExpense.categoricalSpending.get(index).totalAmount,
							2)
					+ " of online entertainment such as spotify Netflix etc.";
			if (bool == true)
				printMessage(message,
						monthExpense.categoricalSpending.get(index).transaction);
			else
				printMessage(message, null);
			return monthExpense.categoricalSpending.get(index).totalAmount;
		}
		return 0;
	}

	/**
	 * Check to save on taxi expenses
	 * 
	 * @param monthExpense
	 *            The month to make recommendation for
	 * @param bool
	 *            Whether or not give detailed recommendation
	 */
	private double taxiCheck(MonthlyExpense monthExpense, boolean bool) {
		for (int i = 0; i < monthWiseExpenditure.size(); i++)
			if (monthWiseExpenditure.get(i).categoricalSpending
					.contains(new CategoryAmount(Keywords.gas, 0))
					&& monthExpense.categoricalSpending
							.contains(new CategoryAmount(Keywords.taxi, 0))) {
				int index = monthExpense.getCategoricalIndex(monthExpense,
						Keywords.taxi);
				String message = "You made use of taxi inspite of having a car, you could have avoided spending $"
						+ Helper.round(
								monthExpense.categoricalSpending.get(index).totalAmount,
								2) + ".";
				if (bool == true)
					printMessage(
							message,
							monthExpense.categoricalSpending.get(index).transaction);
				else
					printMessage(message, null);
				return monthExpense.categoricalSpending.get(index).totalAmount;
			}
		return 0;
	}

	/**
	 * This method prints the most expensive months
	 */
	public void highestExpenseMonths() {
		double avg = 0;
		for (int i = 0; i < monthWiseExpenditure.size(); i++) {
			avg += monthWiseExpenditure.get(i).total;
		}
		avg = avg / monthWiseExpenditure.size();
		System.out.println("The most expensive months are as follows");
		printTemplate();
		for (int i = 0; i < monthWiseExpenditure.size(); i++) {
			if (monthWiseExpenditure.get(i).total > (avg + getThreshold(avg))) {
				printMonthlyExpenditure(monthWiseExpenditure.get(i));
			}
		}
	}

	/**
	 * Get the threshold of how much variance to allow.
	 * 
	 * @param avg
	 *            the average to check against
	 * @return the allowed variance
	 */
	private int getThreshold(double avg) {
		return (int) Math.floor(avg * 0.08);
	}

	// Template to print
	private void printTemplate() {
		System.out.println("Month " + "Year " + "Amount " + "Categories");
	}

	/**
	 * Print method
	 * 
	 * @param monthwiseExpenditure
	 *            The expenditure to print
	 */
	private void printMonthlyExpenditure(MonthlyExpense monthwiseExpenditure) {
		System.out.println(monthwiseExpenditure);
	}

	// Another print method
	public void print() {
		System.out.println();
		System.out
				.println("Monthwise Expenditure for all the transactions are as follows");
		printTemplate();
		for (int i = 0; i < monthWiseExpenditure.size(); i++) {
			printMonthlyExpenditure(monthWiseExpenditure.get(i));
		}
	}
}
