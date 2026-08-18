import java.util.HashMap;
import java.util.Map;

class Account {
    int accountNumber;
    String name;
    double balance;

    Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
    }

    boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    void display() {
        System.out.println("Account No : " + accountNumber);
        System.out.println("Name       : " + name);
        System.out.println("Balance    : " + balance);
    }
}

public class BankingSystem2{

    static HashMap<Integer, Account> accounts = new HashMap<>();

    // Create Account
    static void createAccount(int accountNumber, String name, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return;
        }

        Account account = new Account(accountNumber, name, initialBalance);
        accounts.put(accountNumber, account);

        System.out.println("Account created successfully!");
    }

    // Deposit
    static void deposit(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        account.deposit(amount);
        System.out.println("Amount deposited successfully!");
        System.out.println("Balance: " + account.balance);
    }

    // Withdraw
    static void withdraw(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        if (account.withdraw(amount)) {
            System.out.println("Amount withdrawn successfully!");
            System.out.println("Balance: " + account.balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Transfer
    static void transfer(int fromAccount, int toAccount, double amount) {

        Account sender = accounts.get(fromAccount);
        Account receiver = accounts.get(toAccount);

        if (sender == null || receiver == null) {
            System.out.println("Account not found!");
            return;
        }

        if (sender.withdraw(amount)) {
            receiver.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Display Account
    static void displayAccount(int accountNumber) {
        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        account.display();
    }

    // Display All Accounts
    static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            entry.getValue().display();
            System.out.println("-------------------");
        }
    }

    public static void main(String[] args) {

        createAccount(101, "Jebina", 5000);
        createAccount(102, "John", 3000);

        System.out.println();

        deposit(101, 2000);

        System.out.println();

        withdraw(102, 1000);

        System.out.println();

        transfer(101, 102, 1500);

        System.out.println();

        displayAccount(101);

        System.out.println();

        displayAllAccounts();
    }
}