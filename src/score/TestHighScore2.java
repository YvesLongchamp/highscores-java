package score;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Yves Longchamp and David Ringayen
 * 
 *         A class to operate, and test our highscores.
 *
 */
public class TestHighScore2 {

	static final String PATH_SCORES = "scoreSamples.txt";

	/**
	 * 
	 * The main method, where we can print on the console the previous scores,
	 * and a score of a player who enter his name.
	 * 
	 * @param args
	 *            is useless here.
	 * 
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String name = "";
		String score = "";

		Path path = Paths.get(PATH_SCORES);
		
		HighScore2 highscore = new HighScore2();
		highscore.main(args);
		BestPlayer[] top10 = new BestPlayer[10];
		try {

			if (sc.hasNext()) {
				name = sc.next();
			}

			List<String> scores = Files.readAllLines(path);
			top10 = new BestPlayer[10];
			top10 = highscore.tenBestScores(scores);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		for(int j = 0 ; j < top10.length ; j++) {
			System.out.println("The player : " + top10[j].getPlayer() + " has the score of : "
					+ top10[j].getScore());
		}

		sc.close();
	}

}
