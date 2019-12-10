import java.util.Comparator;
import java.util.Random;

public class Card {
	public final Rank rank;
	public final Suit suit;
	public static final Comparator<Card> suitComparator;
	public static final Comparator<Card> rankComparator;
	
    static {
    	suitComparator = new Comparator<Card>() {
            public int compare(Card card1, Card card2) {
                return card1.suit.hashCode() - card2.suit.hashCode();
            }
        };
        rankComparator = new Comparator<Card>() {
        	public int compare(Card card1, Card card2) {
        		return card1.rank.getValue() - card2.rank.getValue();
        	}
        };
    }

	public Card(Rank rank, Suit suit) {
	    this.rank = rank;
	    this.suit = suit;
	}
	
	public static Card random() {
		Random generator = new Random();
		Rank rank = Rank.values()[generator.nextInt(Rank.values().length)];
		Suit suit = Suit.values()[generator.nextInt(Suit.values().length)];
		return new Card(rank, suit);
	}
	
	public String toString() {
		return "Rank: " + this.rank.getDisplayName() + " Suit: " + this.suit.getDisplayName();
	}
}
