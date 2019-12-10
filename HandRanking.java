import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HandRanking {
	public static int NO_LOW = 99999;
	public static int WORST_LOW = 87654;
	
	public static int rank(Hand hand, Board board, GameType gameType) {
		if (gameType == GameType.TEXAS_HOLDELM) {
			return rankTexasHoldem(hand, board);
		}
		
		return 0;
	}
	
	public static int rankFiveCardsLow(ArrayList<Card> cards) {
		int lowHandRank = 0;
		
		cards.sort(Card.rankComparator);
		
		int positionMultiplier = 1;
		int previousCardRank = 0;
		for (Card card : cards) {
			int cardRank = card.rank.value;
			if (previousCardRank == cardRank) {
				return NO_LOW;
			}
			lowHandRank += card.rank.value * positionMultiplier;
			positionMultiplier *= 10;
			previousCardRank = cardRank;
		}
		
		if (lowHandRank > WORST_LOW) {
			lowHandRank = NO_LOW;
		}
		
		return lowHandRank;
	}
	
	public static double rankFiveCardsHigh(ArrayList<Card> cards) {
		HashMap<Rank, Integer> ranksMap = new HashMap<Rank, Integer>();
		for (Card card : cards) {
			if (ranksMap.containsKey(card.rank)) {
				ranksMap.replace(card.rank, ranksMap.get(card.rank)+ 1);
			} else
				ranksMap.put(card.rank, 1);
		}
		
		
		
		HashMap<Integer, LinkedList<Rank>> frequencyToRanks = new HashMap<Integer, LinkedList<Rank>>();
		for(Map.Entry<Rank, Integer> entry : ranksMap.entrySet()){
			if (frequencyToRanks.containsKey(entry.getValue())) {
				frequencyToRanks.get(entry.getValue()).add(entry.getKey());
			} else {
				LinkedList<Rank> list = new LinkedList<Rank>();
				list.add(entry.getKey());
				frequencyToRanks.put(entry.getValue(), list);
			}
		}
		
		cards.sort(Card.rankComparator);
		cards.sort(Card.suitComparator);
		
		// Royal and Straight Flush
		if (cards.get(0).suit == cards.get(4).suit) { 
			cards.sort(Card.rankComparator);
			if (cards.get(0).rank.value == 1 && cards.get(1).rank.value == 10 && cards.get(2).rank.value == 11
					&& cards.get(3).rank.value == 12 && cards.get(4).rank.value == 13)
				return 9 + (14 / 100.0);
			if (cards.get(0).rank.value + 1 == cards.get(1).rank.value && cards.get(1).rank.value + 1 == cards.get(2).rank.value &&
					cards.get(2).rank.value + 1 == cards.get(3).rank.value && cards.get(3).rank.value + 1 == cards.get(4).rank.value) {
				cards.sort(Card.rankComparator.reversed());
				return 9 + (cards.get(4).rank.value / 100.00);
			}
		}
		
		// Four of a Kind
		if (frequencyToRanks.containsKey(4)) {
			return 8 + (frequencyToRanks.get(4).getFirst().getStrength() / 100.0);
		}
		
		// Full House
		if (frequencyToRanks.containsKey(3) && frequencyToRanks.containsKey(2)) {
			return 7 + (frequencyToRanks.get(3).getFirst().getStrength() / 100.0) + (frequencyToRanks.get(2).getFirst().getStrength() / 10000.0);
		}
		
		// Flush
		if (cards.get(0).suit == cards.get(4).suit) {
			cards.sort(Card.rankComparator);
			if (cards.get(0).rank.value == 1) {
				return 6 + (14 / 100.0) + (cards.get(4).rank.value / 10000.00) + (cards.get(3).rank.value / 1000000.00)
						 + (cards.get(2).rank.value / 100000000.00) + (cards.get(1).rank.value / 10000000000.00);
			}
			return 6 + (cards.get(4).rank.value / 100.00) + (cards.get(3).rank.value / 10000.00) + (cards.get(2).rank.value / 1000000.00)
			 + (cards.get(1).rank.value / 100000000.00) + (cards.get(0).rank.value / 10000000000.00);
		}
		
		// Straight
		
		cards.sort(Card.rankComparator);
		if (cards.get(0).rank.value + 1 == cards.get(1).rank.value && cards.get(1).rank.value + 1 == cards.get(2).rank.value
			&& cards.get(2).rank.value + 1 == cards.get(3).rank.value && cards.get(3).rank.value + 1 == cards.get(4).rank.value) {
		return 5 + (cards.get(4).rank.value / 100.00);
		}
		
		// Royal Straight
		 if (cards.get(0).rank.value == 1 && cards.get(1).rank.value == 10 && cards.get(2).rank.value == 11 
					&& cards.get(3).rank.value == 12 && cards.get(4).rank.value == 13) {
			return 5 + (14 / 100.0);
		}
		
		// Three of a Kind
		 if (frequencyToRanks.containsKey(3)) {
			if (frequencyToRanks.get(1).getFirst().getStrength() > frequencyToRanks.get(1).getLast().getStrength()) {
				return 4 + (frequencyToRanks.get(3).getFirst().getStrength() / 100.0) + (frequencyToRanks.get(3).getFirst().getStrength() / 10000.0)
						 + (frequencyToRanks.get(3).getFirst().getStrength() / 1000000.0) + (frequencyToRanks.get(1).getFirst().getStrength() / 100000000.0)
						 + (frequencyToRanks.get(1).getLast().getStrength() / 10000000000.0);
			}
			return 4 + (frequencyToRanks.get(3).getFirst().getStrength() / 100.0) + (frequencyToRanks.get(3).getFirst().getStrength() / 10000.0)
					 + (frequencyToRanks.get(3).getFirst().getStrength() / 1000000.0) + (frequencyToRanks.get(1).getLast().getStrength() / 100000000.0)
					 + (frequencyToRanks.get(1).getFirst().getStrength() / 10000000000.0);
		}
		
		// Two Pair
		 if (frequencyToRanks.containsKey(2) && frequencyToRanks.get(2).size() > 1) {
			if (frequencyToRanks.get(2).get(0).getStrength() > frequencyToRanks.get(2).get(1).getStrength())
				return 3 + (frequencyToRanks.get(2).get(0).getStrength() / 100.0) + (frequencyToRanks.get(2).get(0).getStrength() / 10000.0)
						 + (frequencyToRanks.get(2).get(1).getStrength() / 1000000.0) + (frequencyToRanks.get(2).get(1).getStrength() / 100000000.0)
						 + (frequencyToRanks.get(1).getFirst().getStrength() / 10000000000.0);
			return 3 + (frequencyToRanks.get(2).get(1).getStrength() / 100.0) + (frequencyToRanks.get(2).get(1).getStrength() / 10000.0)
					 + (frequencyToRanks.get(2).get(0).getStrength() / 1000000.0) + (frequencyToRanks.get(2).get(0).getStrength() / 100000000.0)
					 + (frequencyToRanks.get(1).getFirst().getStrength() / 10000000000.0);
		} 
		
		// Pair
		 if (frequencyToRanks.containsKey(2)) {
			Collections.sort(frequencyToRanks.get(1));
			if (frequencyToRanks.get(1).getFirst().getValue() == 1)
				return 2 + (frequencyToRanks.get(2).getFirst().getStrength() / 100.0) + (frequencyToRanks.get(2).getFirst().getStrength() / 10000.0)
						 + (14 / 1000000.0) + (frequencyToRanks.get(1).get(2).getStrength() / 100000000.0)
						 + (frequencyToRanks.get(1).get(1).getStrength() / 10000000000.0);
			return 2 + (frequencyToRanks.get(2).getFirst().getStrength() / 100.0) + (frequencyToRanks.get(2).getFirst().getStrength() / 10000.0)
					 + (frequencyToRanks.get(1).get(2).getStrength() / 1000000.0) + (frequencyToRanks.get(1).get(1).getStrength() / 100000000.0)
					 + (frequencyToRanks.get(1).get(0).getStrength() / 10000000000.0);
		}
		
		cards.sort(Card.rankComparator);
		
		// High Card
		return 1 + (cards.get(4).rank.value / 100.00) + (cards.get(3).rank.value / 10000.00) + (cards.get(2).rank.value / 1000000.00)
				 + (cards.get(1).rank.value / 100000000.00) + (cards.get(0).rank.value / 10000000000.00);
	}
	
	
	public static int rankTexasHoldem(Hand hand, Board board) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (Card card : hand.hand) {
			cards.add(card);
		}
		for (Card card : board.board) {
			cards.add(card);
		}
		
		HashMap<Rank, Integer> ranksMap = new HashMap<Rank, Integer>();
		for (Card card : cards) {
			if (ranksMap.containsKey(card.rank)) {
				ranksMap.replace(card.rank, ranksMap.get(card.rank)+ 1);
			} else {
				ranksMap.put(card.rank, 1);
			}
		}
 		
		HashMap<Integer, LinkedList<Rank>> frequencyToRanks = new HashMap<Integer, LinkedList<Rank>>();
		for(Map.Entry<Rank, Integer> entry : ranksMap.entrySet()){
			if (frequencyToRanks.containsKey(entry.getValue())) {
				frequencyToRanks.get(entry.getValue()).add(entry.getKey());
			} else {
				LinkedList<Rank> list = new LinkedList<Rank>();
				list.add(entry.getKey());
				frequencyToRanks.put(entry.getValue(), list);
			}
		}
		
		cards.sort(Card.rankComparator);
		cards.sort(Card.suitComparator);
		for (int offset = 0; offset <= 2; offset++) {
			if (cards.get(offset).suit == cards.get(offset + 4).suit) {
				if (cards.get(offset).rank.value + 1 == cards.get(offset + 1).rank.value && cards.get(offset + 1).rank.value + 1 == cards.get(offset + 2).rank.value && cards.get(offset + 2).rank.value + 1 == cards.get(offset + 3).rank.value && cards.get(offset + 3).rank.value + 1 == cards.get(offset + 4).rank.value) {
					System.out.println("STRAIGHT FLUSH");
					return 9;
				}
			}
		}
		
		if (frequencyToRanks.containsKey(4)) {
			System.out.println("FOUR OF A KIND");
			return 8;
		}
		
		if (frequencyToRanks.containsKey(3) && frequencyToRanks.containsKey(2)) {
			System.out.println("FULL HOUSE");
			return 7;
		}
		
		for (int offset = 0; offset <= 2; offset++) {
			if (cards.get(offset).suit == cards.get(offset + 4).suit) {
				System.out.println("FLUSH");
				return 6;
			}
		}
		
		cards.sort(Card.rankComparator); // TODO not complete
		System.out.println(cards);
		for (int offset = 0; offset <= 2; offset++) {
			if (cards.get(offset).rank.value == 1) {
				System.out.println("STRAIGHT");
				return 5;
			}
		}
		
		if (frequencyToRanks.containsKey(3)) {
			System.out.println("THREE OF A KIND");
			return 4;
		}
		
		if (frequencyToRanks.containsKey(2) && frequencyToRanks.get(2).size() > 1) {
			System.out.println("TWO PAIR");
			return 3;
		} 
		
		if (frequencyToRanks.containsKey(2)) {
			System.out.println("PAIR");
			return 2;
		}
		
		System.out.println("HIGH CARD");
		return 1;
	}
}
