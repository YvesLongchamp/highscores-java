package score;

import java.util.Comparator;

public class BestPlayer implements Comparable<BestPlayer> {
	int score;
	String player;
	
	public BestPlayer(int score, String player) {
		this.score = score;
		this.player = player;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getPlayer() {
		return this.player;
	}
	
	public int compareTo(BestPlayer p) {
		if(p.getScore() < this.getScore()) {
			return -1;
		} else if(p.getScore() > this.getScore()) {
			return 1;
		} else {
			return 0;
		}
	}
}
