public class Card {
	public enum Suit {Club, Diamond, Heart, Spade}
	private Suit suit; // Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; // 1~13

	/**
	 * @param s
	 *            suit
	 * @param r
	 *            rank
	 */
	public Card(Suit s, int value) {
		suit = s;
		rank = value;
	}

	// TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10
	// for rank)
	public void printCard() {
		// Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as
		// Clubs Ace
		switch(rank) {
		case 1:
			System.out.println(suit+ " Ace");
			break;
		case 11:
			System.out.println(suit+ " Jack" );
			break;
		case 12:
			System.out.println(suit+ " Queen");
			break;
		case 13:
			System.out.println(suit+ " King");
			break;
		default:
			System.out.println(suit+ " "+ rank);
			break;
		}
	}

	public Suit getSuit() {
		return suit;
	}

	public int getRank() {
		if(rank==1)
			return 11;
		else if(rank==11||rank==12||rank==13)
			return 10;
		else
			return rank;
	}

}
