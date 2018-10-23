package debugging;

import java.util.Random;

public class CoffeeCupProgram {

	private CoffeeCup cup;
	private Random r;
	
	public void init(){
		cup = new CoffeeCup();
		r = new Random(123456789L);
	}
	
	public void run(){
		part1();
		part2();
	}
	
	private void part1(){
		cup.increaseCupSize(40.0); //sets capacity 40, current volume 0
		cup.fillCoffee(20.5); //current volume 20.5/40
		cup.drinkCoffee(Math.floor(r.nextDouble()*20.5)); //current volume 7.5/40
		cup.fillCoffee(32.5); //current volume 40/40
		cup.drinkCoffee(Math.ceil(r.nextDouble()*38.9));//current volume 22.0/40
		cup.drinkCoffee(Math.ceil(r.nextDouble()*42));//current volume 5.0/40
		cup.increaseCupSize(17);//sets capacity to 57
		//cup.drinkCoffee(40); // there is not enough coffee in the cup to drink 40
		//cup.drinkCoffee(Math.ceil(r.nextDouble()*42));
		//cup.drinkCoffee(Math.floor(r.nextDouble()*20.5));
		cup.fillCoffee(32.5);	
		cup.drinkCoffee(Math.ceil(r.nextDouble()*38.9));
		//cup.drinkCoffee(Math.ceil(r.nextDouble()*42));
		cup.increaseCupSize(17);
	}
	
	private void part2(){
		cup = new CoffeeCup(40.0, 20.5); //capacity set to 40, current volume 20.5
		r = new Random(987654321L);
		cup.drinkCoffee(Math.floor(r.nextDouble()*20.5)); //current volume is now 14.5 
		cup.fillCoffee(Math.floor(r.nextDouble()*30)); //current volume is now 38.5
		cup.drinkCoffee(Math.ceil(r.nextDouble()*38.9)); //current volume is now 36.5
		cup.drinkCoffee(Math.ceil(r.nextDouble()*42)); //current volume is now 6.5
		cup.increaseCupSize(Math.floor(r.nextDouble()*26)); //increases cup size
		//cup.fillCoffee(Math.ceil(r.nextDouble()*59)); //Spills coffee on the floor
		//cup.drinkCoffee(Math.ceil(r.nextDouble()*42));
		cup.increaseCupSize(Math.floor(r.nextDouble()*35));
		cup.fillCoffee(Math.floor(r.nextDouble()*30));
		cup.increaseCupSize(Math.floor(r.nextDouble()*26));
	}
	
	
	public static void main(String[] args) {
		CoffeeCupProgram program = new CoffeeCupProgram();
		program.init();
		program.run();
	}

}
