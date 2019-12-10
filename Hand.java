import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();

	public Hand(int handSize) {
		this(new Deck(), handSize);
	}

	public Hand(Deck deck, int handSize) {
		for (int i = 0; i < handSize; i++ ) {
			hand.add(deck.takeCard());
		}
	}
	
	public ArrayList<Integer> getRanks() {
		ArrayList<Integer> rankArray = new ArrayList<Integer>();
		for (int i = 0; i < hand.size(); i++)
			rankArray.add(hand.get(i).rank.getValue());
		return rankArray;
	}
	
	public ArrayList<Suit> getSuits() {
		ArrayList<Suit> suitArray = new ArrayList<Suit>();
		for (int i = 0; i < hand.size(); i++)
			suitArray.add(hand.get(i).suit);
		return suitArray;
	}
	
	public Card getHighestCard() {
		this.hand.sort(Card.rankComparator);
		if (this.hand.get(0).rank.getValue() == 1)
			return this.hand.get(0);
		else
			return this.hand.get(this.hand.size() - 1);
	}
	
	public ArrayList<Card> getSameSuit(Suit suit) {
		ArrayList<Card> sameSuitArray = new ArrayList<Card>();
		for (Card card : this.hand) {
			if (card.suit.equals(suit))
				sameSuitArray.add(card);
		}
		return sameSuitArray;
	}
	
	public void add(Card card) {
		this.hand.add(card);
	}

	public String toString() {
		String returnString = super.toString() + "\n";
		for (Card cardToPrint : hand) {
			returnString += cardToPrint.toString() + "\n";
		}
		return returnString;
	}
}
