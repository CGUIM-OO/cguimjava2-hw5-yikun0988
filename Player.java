import java.util.ArrayList;

public class Player extends Person{
	private String name;
	private int chips; 
	private int bet;
	//private ArrayList<Card> oneRoundCard = new ArrayList();
	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}
	public String getName() {
		return name;
	}
	public int makeBet() {
		if(chips!=0) {
			bet = 1;
			return bet;
		}
		else {
			bet = 0;
			System.out.println("沒錢就回家吃奶，別在這丟人現眼!");
		}
		return bet;
	}
	public boolean hit_me(Table tbl) {
		if(getTotalValue() <=16) {
			return true;
		}
		return false;
	} 
	public int getCurrentChips() {
		return chips;		
	}
	public void increaseChips (int diff) {
		chips+=diff;
	}
	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}
