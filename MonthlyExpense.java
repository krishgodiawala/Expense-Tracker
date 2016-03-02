import java.util.ArrayList;

/**
 * This class represents an object to store the transactions for each month of
 * the year. This is the object used by the algorithm while processing the data
 * and making recommendations.
 * 
 * @author Krish Godiawala
 *
 */
public class MonthlyExpense implements Comparable<MonthlyExpense> {
	String month;
	String year;
	double total;
	ArrayList<CategoryAmount> categoricalSpending;

	public MonthlyExpense(Transaction transaction) {
		this.month = transaction.getMonth();
		this.year = transaction.getYear();
		categoricalSpending = new ArrayList<CategoryAmount>();
		this.total = 0;
		updateExpense(transaction);
	}

	// Used for the contains method to check if equal
	public MonthlyExpense(String month, String year) {
		categoricalSpending = new ArrayList<CategoryAmount>();
		this.month = month;
		this.year = year;
	}

	/**
	 * So that the transactions are sorted such that an older date appears
	 * first.
	 * 
	 * @param monthlyExpense
	 *            the object to compare against
	 */
	public int compareTo(MonthlyExpense monthlyExpenses) {
		if (this.year.compareTo(monthlyExpenses.year) != 0)
			return this.year.compareTo(monthlyExpenses.year);
		else
			return this.month.compareTo(monthlyExpenses.month);

	}

	/**
	 * To update instance variables
	 * 
	 * @param transaction
	 *            The transaction to add
	 */
	public void updateExpense(Transaction transaction) {
		this.total += transaction.getCharge();
		int index = categoricalSpending.indexOf(new CategoryAmount(transaction
				.getCategory(), 0));
		if (index >= 0)
			categoricalSpending.get(index).updateCategory(transaction);
		else
			categoricalSpending.add(new CategoryAmount(transaction));
		// categoricalSpending.add(new CategoryAmount(transaction
		// .getCategory(), transaction.getCharge()));
	}

	/**
	 * Returns hashcode dependent only on month and year
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	/**
	 * checks if the dates are equal
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonthlyExpense other = (MonthlyExpense) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	/**
	 * This method returns category wise expenditure
	 *
	 * @return category wise expenditure.
	 */
	public int getCategoricalIndex(MonthlyExpense monthExpense, String category) {
		return monthExpense.categoricalSpending.indexOf(new CategoryAmount(
				category, 0));
	}

	@Override
	public String toString() {
		return month + "   " + year + " " + Helper.round(total, 2) + " "
				+ categoricalSpending;
	}

}
