import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Deck {
	ArrayList<Card> usedCard = new ArrayList<Card>();
	private int nUsed=0;
	private ArrayList<Card> cards;
	private ArrayList<Card> openCard = new ArrayList<Card>();

	// TODO: Please implement the constructor (30 points)
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		// 1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		// Hint: Use new Card(x,y) and 3 for loops to add card into deck
		// Sample code start
		// Card card=new Card(1,1); ->means new card as clubs ace
		// cards.add(card);
		// Sample code end
		for (int n = 0; n < nDeck; n++) {
			for (Card.Suit s : Card.Suit.values()) {
				for (int r = 1; r <= 13; r++) {
					Card card = new Card(s, r);
					cards.add(card);
				}
			}
		}
		shuffle();
	}

	// TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck() {
		// Hint: print all items in ArrayList<Card> cards,
		// TODO: please implement and reuse printCard method in Card class (5 points)
		int NumberOfDeck = 1;
		for (int a = 0; a < cards.size(); a++) {
			if (a % 52 == 0) {
				System.out.print("\n第" + NumberOfDeck + "組牌:");
				NumberOfDeck++;
			}
			cards.get(a).printCard();
		}
	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}
	public void shuffle() {
		cards.addAll(usedCard);
		Random rnd = new Random(); 
		//內建洗牌
		Collections.shuffle(cards);
		//老師要的洗牌
		for(int i=0;i<52;i++) {
			int j = rnd.nextInt(i+1);
            cards.add(cards.get(j));
            cards.remove(cards.get(j));
		}
		usedCard.clear();
		nUsed=0;
		openCard.clear();
	}
	public Card getOneCard(boolean isOpened) {
		if(nUsed==52) {
			shuffle();
			nUsed=0;
		}
		if(isOpened) {
			openCard.add(cards.get(0));
		}
		Card deal=cards.get(0);
		cards.remove(0);
		nUsed++;
		usedCard.add(deal);
		return deal;
	}
	public ArrayList<Card> getOpenedCard() {
		return openCard;
	}
}