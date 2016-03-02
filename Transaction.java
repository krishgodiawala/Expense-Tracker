/**
 * This class stores a transaction
 * 
 * @author krishgodiawala
 *
 */
public class Transaction {

	// Instance variables
	private String year;
	private String month;
	private String description;
	private double charge;
	private String category;
	private String day;

	/**
	 * @return returns the day of the transaction
	 */
	public String getDay() {
		return day;
	}

	/**
	 * This method sets the day
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return returns the category of the transaction
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * This method sets the category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	// Constructor
	public Transaction(String date, String description, double charge,
			String category) {
		assignMonthAndYear(date);
		this.description = description;
		this.charge = charge;
		this.category = category;
	}

	/**
	 *  Method returns the description of the expense
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *  Method returns the charge on the expense
	 */
	public double getCharge() {
		return charge;
	}

	/**
	 * @return returns the year of the transaction
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return returns the month of the transaction
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * This methos splits the date into month day year
	 * 
	 * @param date
	 */
	private void assignMonthAndYear(String date) {
		String tokens[] = date.split("-");
		this.year = tokens[0];
		this.month = tokens[1];
		this.day = tokens[2];
	}

	/**
	 * @return This method returns the entire transaction
	 */
	public String getTransaction() {
		return this.getYear() + "-" + this.getMonth() + "-" + this.getDay()
				+ " " + this.getDescription() + " " + this.getCharge();
	}

	public String toString() {
		return "Transaction [year=" + year + ", month=" + month
				+ ", description=" + description + ", charge=" + charge
				+ ", category=" + category + "]";
	}

}
