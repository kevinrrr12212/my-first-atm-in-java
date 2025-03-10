import java.util.Scanner;

public class ATMSystem {
    private static double balance = 3000.00;
    private static String currentPin = "0101"; // Configurable PIN
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM system.");

        if (!verifyPin()) {
            System.out.println("Incorrect PIN. Access denied.");
            return;
        }

        int option;
        do {
            showMenu();
            option = readInteger("Select an option: ");
            executeOption(option);
        } while (option != 5);

        System.out.println("Thank you for using our service. Have a great day!");
        scanner.close();
    }

    private static boolean verifyPin() {
        System.out.print("Please enter your PIN: ");
        String enteredPin = scanner.nextLine();
        return enteredPin.equals(currentPin);
    }

    private static void showMenu() {
        System.out.println("\n------ MAIN MENU ------");
        System.out.println("1) Check balance");
        System.out.println("2) Deposit money");
        System.out.println("3) Withdraw money");
        System.out.println("4) Change PIN");
        System.out.println("5) Exit");
    }

    private static void executeOption(int option) {
        switch (option) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                changePin();
                break;
            case 5:
                System.out.println("Exiting the system...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    private static void withdrawMoney() {
        double amount = readDouble("Enter the amount to withdraw: ");
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Available balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    private static void depositMoney() {
        double amount = readDouble("Enter the amount to deposit: ");
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Your new balance is: $" + balance);
        } else {
            System.out.println("The deposit amount must be greater than zero.");
        }
    }

    private static void changePin() {
        System.out.print("Enter your new PIN (must be at least 4 numeric digits): ");
        String newPin = scanner.nextLine();

        if (newPin.length() >= 4 && newPin.matches("\\d+")) {
            currentPin = newPin;
            System.out.println("PIN has been successfully updated.");
        } else {
            System.out.println("Invalid PIN. It must contain at least 4 numbers.");
        }
    }

    private static int readInteger(String message) {
        int number;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                return number;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Clear incorrect input
            }
        }
    }

    private static double readDouble(String message) {
        double number;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                number = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                return number;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear incorrect input
            }
        }
    }
}
