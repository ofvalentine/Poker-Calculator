public enum GameType {
	TEXAS_HOLDELM("Texas Holdem"), OMAHA_HIGH("Omaha"), OMAHA_HIGH_LOW("Omaha High Low"), BIG_O("Big O");
	
	private String displayName;
	
	GameType(String displayName) {
		this.displayName = displayName;
	}
}
