import java.util.ArrayList;
import java.util.Random;

public class Deck {
	static final ArrayList<Card> generalDeck = new ArrayList<Card>();
	ArrayList<Card> deck = new ArrayList<Card>();
	
	static {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				generalDeck.add(new Card(Rank.values()[i], Suit.values()[j]));
			}
		}
	}
	
	public Deck() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add(new Card(Rank.values()[i], Suit.values()[j]));
			}
		}
	}
	
	public static Card random() {
		Random random = new Random();
		int index = random.nextInt(generalDeck.size());
        return generalDeck.get(index);
	}
	
	public Card takeCard() {
		Random random = new Random();
		int index = random.nextInt(this.deck.size());
		Card returnCard = this.deck.get(index);
		this.deck.remove(index);
		return returnCard;
	}
	
	public String toString() {
		String returnString = super.toString() + "\n";
		for (Card cardToPrint : deck) {
			returnString += cardToPrint.toString() + "\n";
		}
		
		return returnString;
	}
}
