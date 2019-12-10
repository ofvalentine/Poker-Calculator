import java.util.ArrayList;

public class Board {
	final static int generalBoardSize = 5;
	ArrayList<Card> board = new ArrayList<Card>();
	
	public Board() {
		Deck deck = new Deck();
		for (int i = 0; i < generalBoardSize; i++) {
			this.board.add(deck.takeCard());
		}
	}
	
	public Board(int size) {
		Deck deck = new Deck();
		for (int i = 0; i < size; i++) {
			this.board.add(deck.takeCard());
		}
	}
	
	public Board(Deck deck) {
		for (int i = 0; i < generalBoardSize; i++) {
			this.board.add(deck.takeCard());
		}
	}
	
	public ArrayList<Integer> getRanks() {
		ArrayList<Integer> rankArray = new ArrayList<Integer>();
		for (int i = 0; i < board.size(); i++)
			rankArray.add(board.get(i).rank.getValue());
		return rankArray;
	}
	
	public ArrayList<Suit> getSuits() {
		ArrayList<Suit> suitArray = new ArrayList<Suit>();
		for (int i = 0; i < board.size(); i++)
			suitArray.add(board.get(i).suit);
		return suitArray;
	}
	
	public Card getHighestCard() {
		this.board.sort(Card.rankComparator);
		if (this.board.get(0).rank.getValue() == 1)
			return this.board.get(0);
		else
			return this.board.get(this.board.size() - 1);
	}
	
	public ArrayList<Card> getSameSuit(Suit suit) {
		ArrayList<Card> sameSuitArray = new ArrayList<Card>();
		for (Card card : this.board) {
			if (card.suit.equals(suit))
				sameSuitArray.add(card);
		}
		return sameSuitArray;
	}
	
	public void add(Card card) {
		this.board.add(card);
	}
	
	public String toString() {
		String returnString = super.toString() + "\n";
		for (Card cardToPrint : board) {
			returnString += cardToPrint.toString() + "\n";
		}
		
		return returnString;
	}
}
