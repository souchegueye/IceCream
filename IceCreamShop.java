package project1;

import java.util.Scanner;

public class IceCreamShop {

	private static final String[] SCOOPS_MENU = { "Vanilla ($2)", "Dark Chocolate ($3)", "Pistachio ($4)" };
	private static final double[] SCOOPS_PRICES = { 2.0, 3.0, 4.0 };
	private static final String[] EXQUISITE_MENU = { "Mango ($5)", "Dark Chocolate ($6)" };
	private static final double[] EXQUISITE_PRICES = { 5.0, 6.0 };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome, what is your name? ");
		String name = scanner.nextLine();

		System.out.println("Hello " + name + "! How are you today?");
		String answer = scanner.nextLine();

		double totalCost = 0.0; // Variable to store the total cost
		StringBuilder receipt = new StringBuilder(); // String builder to store the receipt

		int itemNumber = 1; // Variable to track the item number

		boolean continueOrdering = true;

		while (continueOrdering) {
			System.out.println("Would you like to explore the menu? (1) Scoops Menu (2) Exquisite Menu");
			int menuChoice = scanner.nextInt();

			double totalPrice = 0.0; // Variable to store the total cost
			String selectedItem = ""; // Variable to store the selected item name

			if (menuChoice == 1) {
				int flavorChoice = displayMenuOptions(scanner, "Scoops Menu", SCOOPS_MENU);
				if (flavorChoice < 1 || flavorChoice > SCOOPS_MENU.length) {
					System.out.println("Invalid choice. Please try again.");
					continue;
				}
				selectedItem = SCOOPS_MENU[flavorChoice - 1];
				totalPrice = SCOOPS_PRICES[flavorChoice - 1];
			} else if (menuChoice == 2) {
				int dessertChoice = displayMenuOptions(scanner, "Exquisite Menu", EXQUISITE_MENU);
				if (dessertChoice < 1 || dessertChoice > EXQUISITE_MENU.length) {
					System.out.println("Invalid choice. Please try again.");
					continue;
				}
				selectedItem = EXQUISITE_MENU[dessertChoice - 1];
				totalPrice = EXQUISITE_PRICES[dessertChoice - 1];
			} else {
				System.out.println("Invalid menu choice. Please try again.");
				continue;
			}

			if (totalPrice > 0) {
				System.out.println("How many would you like?");
				int quantity = scanner.nextInt();

				double itemCost = totalPrice * quantity; // Calculate the cost for the current item
				totalCost += itemCost; // Add the current item cost to the total cost

				// Append the item details to the receipt string builder in columns
				receipt.append(String.format("%-5d", itemNumber)).append(String.format("%-20s", selectedItem))
						.append(String.format("%-10.2f", totalPrice)).append(String.format("%-8d", quantity))
						.append(String.format("%-10.2f", itemCost)).append("\n");

				itemNumber++; // Increment the item number

				System.out.println("Do you want to continue ordering? (yes/no)");
				String continueChoice = scanner.next();
				continueOrdering = continueChoice.equalsIgnoreCase("yes");
			}
		}

		if (totalCost > 0) {
			System.out.println("\n*************** Receipt ***************");
			System.out.println("Item  Flavor               Price     Quantity  Amount");
			System.out.println("---------------------------------------");
			System.out.print(receipt.toString()); // Print the receipt
			System.out.println("---------------------------------------");
			System.out.printf("Your total amount is: $%.2f\n", totalCost);
			System.out.println("***************************************");
		}
	}

	private static int displayMenuOptions(Scanner scanner, String menuName, String[] options) {
		System.out.println(menuName);
		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ". " + options[i]);
		}
		return scanner.nextInt();
	}
}
