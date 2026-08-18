import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {

    int accountNumber;
    String accountHolderName;
    double balance;

    public BankAccount(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
}

public class BankingSystem{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        int choice;

        do {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1: {

                    System.out.print("Enter Account Number: ");
                    int accountNumber = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String accountHolderName = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    accounts.add(new BankAccount(accountNumber, accountHolderName, balance));

                    System.out.println("Account Created Successfully!");
                    break;
                }

                case 2: {

                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    System.out.print("Enter Deposit Amount: ");
                    double deposit = sc.nextDouble();

                    boolean found = false;

                    for (BankAccount account : accounts) {

                        if (account.accountNumber == accNo) {

                            account.balance += deposit;
                            System.out.println("Deposit Successful!");
                            System.out.println("Current Balance: ₹" + account.balance);

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Account Not Found!");
                    }

                    break;
                }

                case 3: {

                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    System.out.print("Enter Withdraw Amount: ");
                    double withdraw = sc.nextDouble();

                    boolean found = false;

                    for (BankAccount account : accounts) {

                        if (account.accountNumber == accNo) {

                            if (withdraw <= account.balance) {

                                account.balance -= withdraw;

                                System.out.println("Withdrawal Successful!");
                                System.out.println("Remaining Balance: ₹" + account.balance);

                            } else {

                                System.out.println("Insufficient Balance!");
                            }

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Account Not Found!");
                    }

                    break;
                }

                case 4: {

                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    boolean found = false;

                    for (BankAccount account : accounts) {

                        if (account.accountNumber == accNo) {

                            System.out.println("\nAccount Number : " + account.accountNumber);
                            System.out.println("Account Holder : " + account.accountHolderName);
                            System.out.println("Current Balance: ₹" + account.balance);

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Account Not Found!");
                    }

                    break;
                }

                case 5: {

                    if (accounts.isEmpty()) {

                        System.out.println("No Accounts Found!");

                    } else {
                        System.out.println("Acc No\tName\t\tBalance");
                        for (BankAccount account : accounts) {
                            System.out.println(account.accountNumber + "\t"
                                    + account.accountHolderName + "\t\t₹"
                                    + account.balance);
                        }
                    }

                    break;
                }

                case 6:

                    System.out.println("Thank You for Using Banking System!");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
