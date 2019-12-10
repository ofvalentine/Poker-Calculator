public class TexasHoldem {
	public static void main (String[] args) {
		randomizedTests();
		mockTests();
		
	}
	
	public static void randomizedTests() {
		System.out.println("Testing Texas Holdem using randomized tests");
		
		
	}
	
	public static void mockTests() {
		System.out.println("Testing Texas Holdem using mock tests");
		
		System.out.println("Test 1");
		Deck deck1 = new Deck();
		Hand hand1 = new Hand(deck1, 2);
		Board board1 = new Board(deck1);
		System.out.println(hand1);
		System.out.println(board1);
		System.out.println(deck1);
		
		System.out.println("Test 2");
		Hand holeCards2 = new Hand(0);
		holeCards2.add(new Card(Rank.NINE, Suit.SPADES));
		holeCards2.add(new Card(Rank.EIGHT, Suit.CLUBS));
		Board board2 = new Board(0);
		board2.add(new Card(Rank.TEN, Suit.CLUBS));
		board2.add(new Card(Rank.SEVEN, Suit.DIAMONDS));
		board2.add(new Card(Rank.SEVEN, Suit.DIAMONDS));
		board2.add(new Card(Rank.SIX, Suit.HEARTS));
		board2.add(new Card(Rank.SEVEN, Suit.CLUBS));
		System.out.println(holeCards2);
		System.out.println(board2);
		System.out.println(HandRanking.rank(holeCards2, board2, GameType.TEXAS_HOLDELM));
		System.out.println();
		
		System.out.println("Test 3");
		Board board3 = new Board(0);
		board3.add(new Card(Rank.ACE, Suit.CLUBS));
		board3.add(new Card(Rank.TEN, Suit.CLUBS));
		board3.add(new Card(Rank.QUEEN, Suit.CLUBS));
		board3.add(new Card(Rank.KING, Suit.CLUBS));
		board3.add(new Card(Rank.JACK, Suit.CLUBS));
		System.out.println(HandRanking.rankFiveCardsHigh(board3.board));
	}	
}
