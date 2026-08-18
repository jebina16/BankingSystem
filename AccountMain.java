import java.util.Scanner;
import java.util.ArrayList;
public class Account{
    int accno;
    double balance;
    String name;
    public Account(int accno,double balance,String name){
        this.accno=accno;
        this.balance=balance;
        this.name=name;
    }

}
public class AccountMain{
     static Scanner sc=new Scanner(System.in);
      static ArrayList<Account> accounts=new ArrayList<Account>();
    public static void main(String[]args){
        
        ArrayList<Account> accounts=new ArrayList<Account>();
        while(true){
            System.out.println("1.create account");
            System.out.println("2.deposit");
            System.out.println("3.withdraw");
            System.out.println("4.check balance");
            System.out.println("5.display account");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    displayAccount();
                    break;
            }

        }
    }
    void createAccount(){
        System.out.println("Enter account number:");
        int accno=sc.nextInt();
        System.out.println("Enter name:");
        String name=sc.next();
        System.out.println("Enter initial balance:");
        double balance=sc.nextDouble();
        accounts.add(new Account(accno, balance, name));
    }
}