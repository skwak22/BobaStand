import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
public class BobaStand {
   
    public static Scanner keyboard = new Scanner (System.in);
    

    public static void main (String[] args) {

	System.out.println("------------------------------------------");
	Money money = new Money(100.0,4.0);
	Inventory recipe = new Inventory (1,1,1);
	Inventory current = new Inventory (0,0,0);
        int day = 1;
	
	while(day<=14){
            System.out.println("It is currently day " + day+"!");
	    System.out.println();
            printMenu();
          
 	    String s = keyboard.nextLine();
	    System.out.println("------------------------------------------");
           
	    if (s.equals("r")) {
		System.out.println("Are you sure you want to end the game?");
		System.out.println("y: Yes");
		System.out.println("n: No");
		String z = keyboard.nextLine();
		if (z.equals("y")){
		    printEnd(money);
		    System.out.println("Thank you for playing!");
		    break;
		}
            }
	    else if (s.equals("d")){
		if (current.pearls < recipe.pearls || current.tea < recipe.tea || current.ice<recipe.ice){
		    System.out.println();
		    System.out.println();
		    System.out.println("You do not have enough inventory to start the day!!!");
		}
		else{
		    startCycle(current, money, recipe, day); 
		    ++day;
		}
	    }
	    else if (s.equals("w"))
		weatherReport(day);
	    else if (s.equals("b"))
		checkBudget(money.budget);
	    else if (s.equals("s"))
	    	goToShop(money,current);
	    else if (s.equals("a"))
	    	adjust(money,recipe);
	    else if (s.equals("i"))
		checkInventory(current);
	    else if (s.equals("?"))
		System.out.println("recipe is "+recipe.pearls+recipe.ice+recipe.tea);
	    
            else
                System.out.println("Please enter a valid letter.");
            System.out.println();
	}
	printEnd(money);
	System.out.println("Thank you for playing!");
    }
    
    
    public static void printEnd(Money money) {
	System.out.println("You have reached the end of the game!");
	double profit = money.budget-100.0;
	System.out.println("Your total profits were "+ profit +".");
    }
    public static void weatherReport(int day){
	if (day%3 == 1)
	    System.out.println("It's nice and sunny outside today!");
	else if (day%3 == 2)
	    System.out.println("It's a bit chilly today...");
	else 
	    System.out.println("Nothing out of the ordinary in the weather report today.");
    }
    public static void checkBudget(double b){

	System.out.println("You currently have $"+b+" in your budget.");
    }

    public static void printMenu(){
        System.out.println("Enter a letter to perform its action");
        System.out.println("d: Start the day");
        System.out.println("s: Go to the shop");
	System.out.println("w: Check the weather report for the day");
        System.out.println("b: Check current budget");
	System.out.println("i: Check inventory");
        System.out.println("a: Adjust recipe and price");
        System.out.println("r: Retire");
        System.out.println();
    }
    
    public static int timeSink(int n) {
	if (n==1)
    	    return 1;
    	else 
    	    return timeSink(n-1) + timeSink(n-1);
    }

    public static void startCycle(Inventory current, Money money, Inventory recipe, int day) {
	int sellCount = 0;
	double profitToday = 0;
	for (int i=0;i<30;++i){
	    int o = timeSink(27);
	    if (current.pearls < recipe.pearls){
		System.out.println("You do not have enough pearls!");
		break;
	    }
	    else if (current.tea < recipe.tea){
		System.out.println("You do not have enough tea!");
		break;
	    }
	    else if (current.ice < recipe.ice) {
		System.out.println("You do not have enough ice!");
		break;
	    }
	    
	    else {

		if (sellChance(money,recipe,day)){ //use money and recipe to determine how many people will want to buy boba (out of 25).
		    
		    System.out.println("You have sold boba!");
		    money.budget += money.price;
		    current.pearls -= recipe.pearls;
		    current.ice -= recipe.ice;
		    current.tea -= recipe.tea;
		    sellCount++;
		    profitToday += money.price;
		}
	    }
	    
	}
	
	System.out.println("You have sold "+sellCount+" boba and made $"+profitToday+" in profit today!");

    }

    public static boolean sellChance(Money money, Inventory recipe, int day) {
	//pearls will correspond to 2 prob. ice to 1. tea to 3.
	//
	//if the price random outcome is less than the recipe outcome, the boba sells.
	//
	double weatherMultiplier = 0;
	if (day%3 == 1)
	    weatherMultiplier = 1.2;
        else if (day%3 == 2)
	    weatherMultiplier = .85;
	else
	    weatherMultiplier = 1.00;

	double recipeProb = (recipe.pearls*2 + recipe.ice + recipe.tea*3)/3;
	if (Math.random()*recipeProb*weatherMultiplier > (money.price-3))
	    return true;
	else
	    return false;
	
	
    }

			   
    public static void checkInventory(Inventory current) {
	System.out.println("You currently have " + current.pearls + " pearls, "+current.ice + " ice, and "+current.tea+" tea.");

   
    }     

    public static void goToShop(Money money, Inventory current) {



	System.out.println("You currently have $"+ money.budget +" in your budget.");
	System.out.println("How many pearls do you want to buy? $.50 per pearls.");
	try{
	    int quantityPearls = keyboard.nextInt();
	    System.out.println("How much ice do you want to buy? $.25 per ice.");
	    int quantityIce = keyboard.nextInt();
	    System.out.println("How much tea do you want to buy? $.75 per tea");
	    int quantityTea = keyboard.nextInt(); 
	    keyboard.nextLine();


	    double  pricePearls = .50;
	    double priceIce = .25;
	    double priceTea = .75;

	    double cost = (pricePearls*quantityPearls) + (priceIce*quantityIce) + (priceTea*quantityTea);
	    System.out.println("This will cost $"+cost+". Are you sure you want to buy these items? y or n");
	    String l = keyboard.nextLine();
	    if(l.equals("y")){
		if(cost>money.budget)
		    System.out.println("You do not have enough money in your budget!");
		else{
		    money.budget -= cost;
		    current.pearls += quantityPearls;
		    current.ice += quantityIce;
		    current.tea += quantityTea;	  
		    System.out.println("Successfully bought!");
		} 
	    }
	}
	catch (Exception e) {
	    keyboard.nextLine();
	    System.out.println("Invalid input!");
	    return;
	}
    }
    
	
   
    public static void adjust(Money money, Inventory recipe) {




	try{
	    //set a limit to amount of ingredients per cup to 4 per cup
	    //set a bound for price from $4 - $9 per cup
	    System.out.println("Your current price per cup is $"+money.price+".");
	    System.out.println("Your current recipe is "+recipe.pearls+" pearl(s), "+recipe.ice + " ice, and "+recipe.tea+" tea.");
	    System.out.println("Adjust your price per cup in range $4-$9: ");
	    double newPrice = keyboard.nextDouble();
	    keyboard.nextLine();
	    if(newPrice < 4 || newPrice > 9){
		System.out.println("Please set a price between $4 and $9!");
		return;
	    }
	    System.out.println("Adjust the amount of pearls per cup: ");
	    int newPearls = keyboard.nextInt();
	    System.out.println("Adjust the amount of ice per cup: ");
	    int newIce = keyboard.nextInt();
	    System.out.println("Adjust the amount of tea per cup: ");
	    int newTea = keyboard.nextInt();

	    keyboard.nextLine();

	    //sets amount of ingredients to 4 max per cup
	

	
	    if((newPearls+newIce+newTea)> 10)
		System.out.println("You can only have a total of ten ingredients in a recipe!");
	    else{
		recipe.pearls = newPearls;
		recipe.ice = newIce;
		recipe.tea = newTea;
	    }	


	    //sets a price bound from $3 to $8

	    money.price = newPrice;
	}
	catch (Exception e) {
	    keyboard.nextLine();
	    System.out.println("Invalid input!");
	    return;
	}


      }

}
      

	    

	    

