import java.util.ArrayList;

/**
 * This class represents an object of a particular category and an amount. Used
 * to display the expense category wise.
 * 
 * @author Krish Godiawala
 *
 */
public class CategoryAmount {
	String category;
	double totalAmount;
	ArrayList<String> transaction;

	public CategoryAmount(String category, double amount) {
		this.category = category;
		this.totalAmount = amount;
	}

	public CategoryAmount(Transaction transaction) {
		this.category = transaction.getCategory();
		this.totalAmount = transaction.getCharge();
		this.transaction = new ArrayList<String>();
		this.transaction.add(transaction.getTransaction());
	}

	/**
	 * This method updates total amount for the category and even store the
	 * transaction
	 */
	public void updateCategory(Transaction transaction) {
		this.totalAmount += transaction.getCharge();
		this.transaction.add(transaction.getTransaction());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryAmount other = (CategoryAmount) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "category= " + category + ", totalAmountSpent="
				+ Helper.round(totalAmount, 2) + " ";
	}

}
