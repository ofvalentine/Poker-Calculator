import java.util.ArrayList;
import java.util.HashMap;
public class BigO {
	public static void main(String[] args) {
		lowTests();
		highTests();
	}
	
	public static void lowTests() {
		System.out.println("Testing BigO Low");
		HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
		for (int i = 0; i < 1000; i++) {
			int result = analysisLow();
			if (frequency.containsKey(result)) {
				frequency.put(result, frequency.get(result) + 1);
			} else {
				frequency.put(result, 1);
			}
		}
		System.out.println(frequency);
	}
	
	public static void highTests() {
		System.out.println("Testing BigO High");
		HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
		for (int i = 0; i < 1000; i++) {
			int result = analysisHigh();
			if (frequency.containsKey(result)) {
				frequency.put(result, frequency.get(result) + 1);
			} else {
				frequency.put(result, 1);
			}
		}
		System.out.println(frequency);
	}
	
	public static int analysisLow() {
		int numberOfHands = 8;
		Deck deck = new Deck();
		ArrayList<Hand> hands = new ArrayList<Hand>();
		for (int i = 0; i < numberOfHands; i++) {
			hands.add(new Hand(deck, 5));
		}
		
		return (int) lowWinner(hands, new Board(deck));
	}
	
	public static int analysisHigh() {
		int numberOfHands = 8;
		Deck deck = new Deck();
		ArrayList<Hand> hands = new ArrayList<Hand>();
		for (int i = 0; i < numberOfHands; i++) {
			hands.add(new Hand(deck, 5));
		}
		
		return (int) highWinner(hands, new Board(deck));
	}
	
	public static int lowHandRank(Hand hand, Board board) {
		int ranking = HandRanking.NO_LOW;
		ArrayList<Card> playedCards = new ArrayList<Card>();
		for (int i = 0; i < hand.hand.size(); i++) {
			for (int j = i + 1; j < hand.hand.size(); j++) {
				for (int k = 0; k < board.board.size(); k++) {
					for (int m = k + 1; m < board.board.size(); m++) {
						for (int n = m + 1; n < board.board.size(); n++) {
							playedCards.removeAll(playedCards);
							playedCards.add(hand.hand.get(i));
							playedCards.add(hand.hand.get(j));
							playedCards.add(board.board.get(k));
							playedCards.add(board.board.get(m));
							playedCards.add(board.board.get(n)); 
							int thisCombinationRank = HandRanking.rankFiveCardsLow(playedCards);
							if (ranking > thisCombinationRank) {
								ranking = thisCombinationRank;
							}
						}
					}
				}
			}
		}
		return ranking;
	}
	
	public static double highHandRank(Hand hand, Board board) {
		double ranking = 0;
		ArrayList<Card> playedCards = new ArrayList<Card>();
		for (int i = 0; i < hand.hand.size(); i++) {
			for (int j = i + 1; j < hand.hand.size(); j++) {
				for (int k = 0; k < board.board.size(); k++) {
					for (int m = k + 1; m < board.board.size(); m++) {
						for (int n = m + 1; n < board.board.size(); n++) {
							playedCards.removeAll(playedCards);
							playedCards.add(hand.hand.get(i));
							playedCards.add(hand.hand.get(j));
							playedCards.add(board.board.get(k));
							playedCards.add(board.board.get(m));
							playedCards.add(board.board.get(n)); 
							double thisCombinationRank = HandRanking.rankFiveCardsHigh(playedCards);
							if (ranking < thisCombinationRank) {
								ranking = thisCombinationRank;
							}
						}
					}
				}
			}
		}
		return ranking;
	}
	
	public static double lowWinner(ArrayList<Hand> hands, Board board) {
		ArrayList<Integer> rankScores = new ArrayList<Integer>();
		for (Hand hand : hands) {
			rankScores.add(lowHandRank(hand, board));
		}
		
		int lowestScore = HandRanking.NO_LOW;
		int winningHand = 0;
		int i = 0;
		for (Integer rankScore : rankScores) {
			// System.out.println("Hand no. " + (i + 1) + " has a score of: " + highScore);
			if (lowestScore > rankScore) {
				lowestScore = rankScore;
				winningHand = i + 1;
			}
			i++;
		}
		// System.out.println("The high winner is hand no. " + winningHand + ", with a score of: " + highestScore);
		
		return lowestScore;
	}
	
	public static double highWinner(ArrayList<Hand> hands, Board board) {
		ArrayList<Double> rankScores = new ArrayList<Double>();
		for (Hand hand : hands) {
			rankScores.add(highHandRank(hand, board));
		}
		
		double highestScore = 0;
		int winningHand = 0;
		int i = 0;
		for (Double rankScore : rankScores) {
			// System.out.println("Hand no. " + (i + 1) + " has a score of: " + highScore);
			if (highestScore < rankScore) {
				highestScore = rankScore;
				winningHand = i + 1;
			}
			i++;
		}
		// System.out.println("The high winner is hand no. " + winningHand + ", with a score of: " + highestScore);
		
		return highestScore;
	}
}
