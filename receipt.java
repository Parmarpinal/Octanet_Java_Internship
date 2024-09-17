import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

class GujaratiFoodStall{
    private HashMap<String, Integer> map = new HashMap<>();
    private HashMap<String, Integer> ordered = new HashMap<>();
    private double cost;
    private double totalAmt;

    //Items with price
    public GujaratiFoodStall() {
        map.put("Dhokla", 100);
        map.put("Gathiya", 130);
        map.put("Khichdi", 100);
        map.put("Thepla", 120);
        map.put("Khandvi", 150);
        map.put("Jalebi Fafda", 200);
        map.put("Khaman", 130);
        map.put("Handvo", 130);
        System.out.println("================== Welcome to Gujarati Food Stall ===================");
    }
    
    //Return cost of ordered items
    private double getCost(String name,int count){
        cost += (map.get(name) * count);
        totalAmt = cost;
        return cost;
    }

    //this will add 5% tax
    private void addTax(){
        totalAmt = totalAmt + (totalAmt * 0.05);
    }

    //user who have discount coupon it give them discount in current cost
    private double addDiscount(double discount){
        return totalAmt = cost - (cost * discount/100);
    } 
    
    //display food menu
    private void foodMenu(){
        System.out.println("\nSelect food items what you want to eat:");
        System.out.println("1. Dhokla -> Rs.100");
        System.out.println("2. Gathiya -> Rs.130");
        System.out.println("3. Khichdi -> Rs.100");
        System.out.println("4. Thepla -> Rs.120");
        System.out.println("5. Khandvi -> Rs.150");
        System.out.println("6. Jalebi Fafda -> Rs.200");
        System.out.println("7. Khaman -> Rs.130");
        System.out.println("8. Handvo -> Rs.130");
        System.out.println("Enter item no and no. of food packet(how many packets do you want?) :");
        Scanner sc = new Scanner(System.in);
        int item = sc.nextInt();
        int count = sc.nextInt();
        String name = "";
        switch (item) {
            case 1:
                name = "Dhokla";
                break;
            case 2:
                name = "Gathiya";
                break;
            case 3:
                name = "Khichdi";
                break;
            case 4:
                name = "Thepla";
                break;
            case 5:
                name = "Khandvi";
                break;
            case 6:
                name = "Jalebi Fafda";
                break;
            case 7:
                name = "Khaman";
                break;
            case 8:
                name = "Handvo";
                break;
            default:
                System.out.println("========================= Please enter valid food item no =================");
                return;
        }
        makeOrder(name, count);
        getCost(name, count);
    }

    //update ordered items
    private void makeOrder(String name, int count){
        if(ordered.containsKey(name)){
            ordered.put(name, ordered.get(name)+count);
        }else{
            ordered.put(name, count);
        }
    }

    //return discount
    private double getDiscount(){
        Scanner sc=new Scanner(System.in);
        if(ordered.isEmpty()){
            System.out.println("\n=========== You have not ordered anything yet========\n");
            return -1;
        }
        System.out.println("Do you have discount coupon?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int ch = sc.nextInt();
        double discount=0;
        if(ch == 1){
            System.out.println("What is the discount in percentage?");
            discount = sc.nextDouble();
            if(discount>30 || discount<0){
                System.out.println("================ Sorry, the discount is out of range =============");
                getDiscount();
            }
        }
        return discount;
    }

    //print receipt
    public void makeReceipt(double discount){
        if(discount == -1){
            return;
        }
        System.out.println("\n=================== Your ordered food dishes ==================");
        System.out.println(" Items  =>  Item Price  =>  Quantity ");
        for (Map.Entry<String, Integer> set :
             ordered.entrySet()) {
 
            System.out.println(set.getKey() + "  =>  " + "Rs. " + map.get(set.getKey()) + "  =>  "
                               + set.getValue());
        }
        System.out.println("\nAmount = "+cost);
        totalAmt = cost;
        if(discount == 0){
            System.out.println("Discount = 0%");
        }
        if(discount != 0 && discount<=30){
            System.out.println("After discount of "+discount+"% the amount = "+addDiscount(discount));
        }
        System.out.println("Tax 5% of the amount  = "+(totalAmt * 0.05));
        addTax();
        System.out.println("================= Total amount = "+totalAmt+" Rupees ===================");
    }

    //downloadReceipt
    public void downloadReceipt(double discount){
        if(discount == -1){
            return;
        }
        String fileName = "Receipt.txt"; // Name of the output file

        // Save the current System.out (console) to restore it later
        PrintStream consoleOut = System.out;  

        try {
            // Step 1: Create a PrintStream that writes to the specified file
            PrintStream fileOut = new PrintStream(new FileOutputStream(fileName));

            // Step 2: Redirect System.out to the file
            System.setOut(fileOut);

            // Now all System.out.println() outputs will be written to the file
            makeReceipt(discount);

            // Close the PrintStream to finish writing to the file
            fileOut.close();

            // Step 3: Restore System.out back to the console
            System.setOut(consoleOut);

            // Now you can print messages to the console again
            System.out.println("=============== File is successfully created ==============");
            System.out.println("Output redirected to file: " + fileName);
            menu();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    
    //display menu
    public void menu(){
        System.out.println("\nEnter your choice :");
        System.out.println("1. Order food");
        System.out.println("2. Show bill receipt");
        System.out.println("3. Download receipt as text file");
        System.out.println("4. Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        double discount = 0;
        switch (choice) {
            case 1:
                foodMenu();
                menu();
                break;
            case 2:
                discount = getDiscount();
                makeReceipt(discount);
                menu();
                break;
            case 3:
                discount = getDiscount();
                downloadReceipt(discount);
                menu();
                break;
            case 4:
                break;
            default:
                System.out.println("=============== Please enter valid choice =================\n");
                menu();
                break;
        }
    }
}

public class receipt{
    public static void main(String[] args) {
        GujaratiFoodStall s1 = new GujaratiFoodStall();
        s1.menu();
    }
}