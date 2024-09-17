
import java.util.Scanner;

public class AtmMachine{        
    public static void main(String[] args){
        ATM account1 = new ATM();
    }
}
class ATM{
    private float _balance;
    private int _pin = 2004;
    private String _lastActivity = "None";
    private float _lastAmount = 0;

    public ATM(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter PIN :");
        int pinNo = sc.nextInt();
        if(pinNo == _pin){
            menu();
        }else{
            System.out.println("============== Please enter valid PIN =============");
        }
    }
    public void menu(){
        System.err.println("");
        System.out.println("Enter your choice no :");
        System.out.println("1. Show balance");
        System.out.println("2. Withdraw money");
        System.out.println("3. Deposit money");
        System.out.println("4. Show transaction history");
        System.out.println("5. Exit");
        Scanner sc = new Scanner(System.in);
        int ch=sc.nextInt();

        switch (ch) {
            case 1:
                showBalance();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                lastHistory();
                break;
            case 5:
                break;
            default:
                System.out.println("================ Enter valid choice ===============");
                break;
        }
    }
    public void showBalance(){
        System.out.println("Balance = "+_balance);
        menu();
    }
    public void withdraw(){
        if(_balance == 0){
            System.err.println("Sorry, You have not money. Your account is empty.");
            menu();
            return;
        }
        System.err.println("Enter amount which you want to withdraw:");
        Scanner sc=new Scanner(System.in);
        float amt = sc.nextFloat();
        if(amt > _balance){
            System.err.println("================ Insufficient balance ==============");
        }else{
            _balance -= amt;
            System.err.println("Money withdrawl successfully."); 
            _lastActivity = "Withdraw";
            _lastAmount = amt;
        }
        menu();
    }
    public void deposit(){
        System.err.println("Enter amount which you want to withdraw:");
        Scanner sc=new Scanner(System.in);
        float amt = sc.nextFloat();
        _balance += amt;
        System.err.println("Money deposited successfully."); 
        _lastActivity = "Deposited";
        _lastAmount = amt;
        menu();
    }
    public void lastHistory(){
        System.out.println("Money "+ _lastAmount +" => "+ _lastActivity );
        menu();
    }
}
