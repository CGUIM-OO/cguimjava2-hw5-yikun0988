import java.util.ArrayList;

public class Table {
	static final int MAXPLAYER = 4;
	private Deck deck;
	private Dealer dealer;
	private Player[] Players;
	private int[] pos_betArray = new int[MAXPLAYER];
	private int nDeck;
	private ArrayList<ArrayList<Card>> player_Cards = new ArrayList<ArrayList<Card>>();
	private ArrayList<Card> dealer_Cards = new ArrayList<Card>();

	public Table(int nDeck) {
		this.nDeck = nDeck;
		this.deck = new Deck(nDeck);
		this.Players = new Player[MAXPLAYER]; 

		player_Cards.clear();
		for (int i = 0; i < Players.length; i++) {
			player_Cards.add(new ArrayList<Card>());
		}
	}

	public void set_player(int pos, Player p) {
		Players[pos] = p;
	}

	public void set_dealer(Dealer d) {
		this.dealer = d; 
	}

	public Player[] get_player() {
		return Players;
	}

	public Card get_face_up_card_of_dealer() {
		return dealer_Cards.get(1);
	}

	public int[] get_players_bet() {
		return pos_betArray;
	}

	private void ask_each_player_about_bets() {
		for (int i = 0; i < Players.length; i++) {
			Players[i].sayHello();
			Players[i].makeBet();
			pos_betArray[i] = Players[i].makeBet();
		}
	}

	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < Players.length; i++) {
			player_Cards.get(i).add(deck.getOneCard(true));
			player_Cards.get(i).add(deck.getOneCard(true));
			Players[i].setOneRoundCard(player_Cards.get(i));
		}
		dealer_Cards.add(deck.getOneCard(false));
		dealer_Cards.add(deck.getOneCard(true));
		dealer.setOneRoundCard(dealer_Cards);
		System.out.print("Dealer's face up card is: ");
		get_face_up_card_of_dealer().printCard();
	}
	
	private void ask_each_player_about_hits() {
		for (int i = 0; i < Players.length; i++) {
			if (Players[i].getTotalValue() <= 21) {
				boolean hit = false;
				System.out.print(Players[i].getName() + "'s Cards now:");
				for (Card c : player_Cards.get(i)) {
					c.printCard();
				} 
				do {
					hit = Players[i].hit_me(this);
					if (hit) {
						player_Cards.get(i).add(deck.getOneCard(true));
						Players[i].setOneRoundCard(player_Cards.get(i));
						System.out.print("Hit! "+Players[i].getName() + "'s Cards now:");
						for (Card c : player_Cards.get(i)) {
							c.printCard();
						} 
					} else {
						System.out.println("Pass hit!");
					} 
				} while (hit);
			}
			System.out.println(Players[i].getName() + "'s hit is over!");
		}
	}

	private void ask_dealer_about_hits() {
		Boolean hit = dealer.hit_me(this);
		if (hit) {
			do {
				hit = dealer.hit_me(this); // this
				if (hit) {
					dealer_Cards.add(deck.getOneCard(true));
					dealer.setOneRoundCard(dealer_Cards);
				} 
			} while (hit);		
		} 
		System.out.println("Dealer's hit is over!");
	}

	private void calculate_chips() {
		System.out.print("Dealer's card value is " + dealer.getTotalValue() + " , Cards:");
		dealer.printAllCard();
		
		for (int i = 0; i < Players.length; i++) {
			System.out.print(Players[i].getName() + ",Cards:");
			Players[i].printAllCard();
			if (dealer.getTotalValue() > 21) {
				if (Players[i].getTotalValue() <= 21) {
					Players[i].increaseChips(pos_betArray[i]);
					System.out.println(Players[i].getName() + "'s card value is " + Players[i].getTotalValue() + ", Get " + pos_betArray[i] + " Chips, the Chips now is:"
							+ Players[i].getCurrentChips());
				} 
				else if (Players[i].getTotalValue() > 21) {
					System.out.println(Players[i].getName() + "'s card value is " + Players[i].getTotalValue() + ",chips have no change! The Chips now is: " + Players[i].getCurrentChips());
				}
			} else if (dealer.getTotalValue() <= 21) {
				if (Players[i].getTotalValue() <= 21) {
					if (Players[i].getTotalValue() > dealer.getTotalValue()) {
						Players[i].increaseChips(pos_betArray[i]);
						System.out.println(Players[i].getName() + "'s card value is " + Players[i].getTotalValue() + ", Get " + pos_betArray[i]
								+ " Chips, the Chips now is: " + Players[i].getCurrentChips());
					} 
					else if (Players[i].getTotalValue() < dealer.getTotalValue()) {
						Players[i].increaseChips(-pos_betArray[i]);
						System.out.println(Players[i].getName() + "'s card value is " + Players[i].getTotalValue() + ", Loss " + pos_betArray[i]
								+ " Chips, the Chips now is: " + Players[i].getCurrentChips());
					} 
					else if (Players[i].getTotalValue() == dealer.getTotalValue()) {
						System.out.println(Players[i].getName() + "'s card value is " + Players[i].getTotalValue() + ",chips have no change! The Chips now is: " + Players[i].getCurrentChips());
					} 
				} else if (Players[i].getTotalValue() > 21) {
					Players[i].increaseChips(-pos_betArray[i]);
					System.out.println(Players[i].getName() + "'s card value is " + Players[i].getTotalValue() + ", Loss " + pos_betArray[i]
							+ " Chips, the Chips now is: " + Players[i].getCurrentChips());
				} 
			}
		}
	}

	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}