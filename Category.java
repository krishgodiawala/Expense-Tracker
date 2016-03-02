import java.util.ArrayList;
import java.util.List;

/**
 * This class represents categories and its various subcategories
 * 
 * @author Krish Godiawala
 *
 */
public class Category {

	String name;
	List<String> subCategory;

	@Override
	public String toString() {
		return "Category [name=" + name + ", subCategory=" + subCategory + "]";
	}

	// constructor
	public Category(String[] tokens) {
		this.name = tokens[0];
		subCategory = new ArrayList<String>();
		for (int i = 1; i < tokens.length; i++) {
			subCategory.add(tokens[i]);
		}
	}

	/**
	 * This method returns whether or not its a member of the category.
	 */
	public boolean membership(String description) {
		if (subCategory != null) {
			for (int i = 0; i < subCategory.size(); i++) {
				if (description.toLowerCase().contains(
						subCategory.get(i).toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

}
