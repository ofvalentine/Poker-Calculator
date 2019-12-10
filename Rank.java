public enum Rank {
    ACE(1, "Ace"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"),
    EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"), JACK(11, "Jack"), QUEEN(12, "Queen"), KING(13, "King");
    public final int value;
    public final String displayName;

    Rank(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Rank of(int value) {
        return java.util.Arrays.stream(Rank.values())
                .filter(rank -> rank.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No card with value " + value + " exists."));
    }
    
    public int getStrength() {
    	if (this.value == 1)
    		return 14;
    	return this.value;
    }
}
