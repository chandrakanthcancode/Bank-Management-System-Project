import java.util.ArrayList;
import java.util.Scanner;

// ========================= ACCOUNT CLASS =========================
class Account {
    private int accountNumber;
    private String name;
    private double balance;

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        balance += amount;
        System.out.println("Deposit successful!");
    }

    public void withdraw(double amount) {
        if (amount > balance || amount <= 0) {
            System.out.println("Insufficient balance or invalid amount!");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawal successful!");
    }

    public String toString() {
        return "Account No: " + accountNumber + " | Name: " + name + " | Balance: " + balance;
    }
}

// =========================== BANK CLASS ==========================
class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();
    private int nextAccountNumber = 1001;

    public void createAccount(String name, double initialDeposit) {
        Account acc = new Account(nextAccountNumber, name, initialDeposit);
        accounts.add(acc);
        System.out.println("Account Created Successfully!");
        System.out.println(acc);
        nextAccountNumber++;
    }

    public Account findAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo)
                return acc;
        }
        return null;
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }
        System.out.println("\n===== ALL ACCOUNTS =====");
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }
}

// ========================== MAIN CLASS ==========================
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do {
            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    double initial = sc.nextDouble();
                    bank.createAccount(name, initial);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int dAcc = sc.nextInt();
                    Account depAcc = bank.findAccount(dAcc);
                    if (depAcc != null) {
                        System.out.print("Enter Amount: ");
                        depAcc.deposit(sc.nextDouble());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int wAcc = sc.nextInt();
                    Account widAcc = bank.findAccount(wAcc);
                    if (widAcc != null) {
                        System.out.print("Enter Amount: ");
                        widAcc.withdraw(sc.nextDouble());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    int bAcc = sc.nextInt();
                    Account balAcc = bank.findAccount(bAcc);
                    if (balAcc != null) {
                        System.out.println("Current Balance: " + balAcc.getBalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 5:
                    bank.showAllAccounts();
                    break;

                case 6:
                    System.out.println("Thank you! Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
